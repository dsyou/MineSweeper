package pl.dsyou.minesweeper.engine;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pl.dsyou.minesweeper.area.AreaService;
import pl.dsyou.minesweeper.area.FieldType;
import pl.dsyou.minesweeper.area.model.GameArea;
import pl.dsyou.minesweeper.area.model.field.Field;
import pl.dsyou.minesweeper.engine.exception.MineFiledFormattedException;
import pl.dsyou.minesweeper.engine.exception.MineFiledInitialisedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Dawid Janik
 * @since 20.03.2018
 */
@Slf4j
@Service
@AllArgsConstructor
public class MineSweeperImpl implements MineSweeper {

    private final AreaService areaService;

    /**
     * Implementation of interface method used to generate game pl.minesweeper.fields/field
     *
     * @param mineField string containing the mines
     * @throws IllegalArgumentException if mineField is not properly formatted
     */
    @Override
    public void setMineField(String mineField) {
        // Split Input string at \n character
        String[] lines = StringUtils.split(mineField, System.lineSeparator());
        checkMineFieldStructure(lines);

        int height = lines.length;       //  row
        int width = lines[0].length();  // column
        log.info("Square size n x m: " + height + " x " + width);

        log.info(" Create game pl.minesweeper.fields[n x m] with  Field");
        areaService.createGameArea(height, width);

        Field[][] fields = areaService.getGameArea().getFields();

        // Fill game pl.minesweeper.fields with proper value taken from user input String
        for (int i = 0; i < height; i++) { // row
            for (int j = 0; j < width; j++) { // column
                char charAt = lines[i].charAt(j);
                if (String.valueOf(charAt).equals("*")) {
                    fields[i][j].setActive(true); // e.g. * . . . \n
                }
                if (String.valueOf(charAt).equals(".")) {
                    fields[i][j].setActive(false);
                }
            }
        }
        areaService.updateFields(fields);
    }

    /**
     * This method is checking properly formatted mineField input.
     * Accepted input must have only two characters:
     * "*" - mine field
     * "." - not mine field
     * If any else occur throw exception.
     * However this method also check that following input is empty,
     * or short/to long.
     *
     * @param lines array divided mineField input string
     * @throws IllegalArgumentException with proper Message
     */
    private void checkMineFieldStructure(String[] lines) {

        if (lines.length <= 0) {
            throw new MineFiledFormattedException("User input is invalid");
        }

        for (String line : lines) {
            if (line.length() != lines[0].length()) {
                throw new MineFiledFormattedException("Width characters size is not proper");
            }
        }

        for (String line : lines) {
            if (!StringUtils.containsOnly(line, '*', '.')) {
                throw new MineFiledInitialisedException("User input contains invalid value");
            }
        }
    }


    /**
     * Implementation of method taken form pl.minesweeper.engine.MineSweeper interface
     *
     * @return a string representation of the hint-field
     * @throws IllegalStateException if the mine-field has not been initialised (i.e. setMineField() has not been called)
     */
    @Override
    public void getHintField() {
        final GameArea gameArea = areaService.getGameArea();
        if (gameArea == null) {
            throw new MineFiledInitialisedException("Method setMineFiled() has not been called");
        }
        final Field[][] fields = gameArea.getFields();
        final int row = gameArea.getRow();
        final int column = gameArea.getColumn();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                final Field currentField = fields[i][j];
                List<Field> neighbors = getNeighbors(currentField, i, j).orElseThrow(RuntimeException::new);
                if (neighbors.isEmpty()) { // Check list size: 0-mines, 8-no mine currentField
                    for (int n = 0; n < 8; n++) {
                        if (neighbors.get(n).isActive()) { // If the neighbor is Mine increment current currentField.hintValue
                            currentField.setHintValue(currentField.getHintValue() + 1);
                        }
                    }
                }
            }
        }
      areaService.getGameAreaHints();
    }

    /**
     * This method is used to get 8 neighbors of current field.
     * The return value is a list of neighbors.
     * It is Important to check size of list- mine_Cell is skipped.
     *
     * @param currentField
     * @param currentRow    value
     * @param currentColumn value
     * @return list of neighbors
     */
    private Optional<List<Field>> getNeighbors(Field currentField, int currentRow, int currentColumn) {

        log.info("Row:{} Column:{}", currentRow, currentColumn);
        List<Field> neighbors = new ArrayList<>();

        if (currentField.getFieldType() == FieldType.MINE) {
            log.info("Field is Mine");
            // Mine field return 0-elements list
        } else {
            /**
             * Each field has 8 neighbors on game
             * If the field is on the edge (e.g [0,0]), and want take neighbor on the illegal position
             * Throw Exception, replace this neighbor with new  Field (not-mine).
             * Non-periodic bound condition
             *
             * Position are:
             * 0 - left top; 1- central top; 2- right top; 3-right center;
             * 4- right bottom; 5-central bottom; 6-left bottom; 7-left bottom
             */
            int leftX = currentColumn - 1;
            int rightX = currentColumn + 1;
            int topY = currentRow - 1;
            int bottomY = currentRow + 1;
            for (int neighborId = 0; neighborId < 8; neighborId++) {
                switch (neighborId) {
                    case 0:
                        addNeighbors(neighbors, leftX, topY, "Field 0");
                        break;
                    case 1:
                        addNeighbors(neighbors, currentColumn, topY, "Field 1");
                        break;
                    case 2:
                        addNeighbors(neighbors, rightX, topY, "Field 2");
                        break;
                    case 3:
                        addNeighbors(neighbors, rightX, currentRow, "Field 3");
                        break;
                    case 4:
                        addNeighbors(neighbors, rightX, bottomY, "Field 4");
                        break;
                    case 5:
                        addNeighbors(neighbors, currentColumn, bottomY, "Field 5");
                        break;
                    case 6:
                        addNeighbors(neighbors, leftX, bottomY, "Field 6");
                        break;
                    case 7:
                        addNeighbors(neighbors, leftX, currentRow, "Field 7");
                        break;
                    default:
                        break;
                }
            }
        }
        return (neighbors.size() > 8) ? Optional.empty() : Optional.of(neighbors);
    }

    private List<Field> addNeighbors(List<Field> neighbors, int x, int y, String loggerMessage) {
        try {
            final Field field = areaService.getGameArea().getFields()[x][y];
            neighbors.add(Field.builder()
                    .hintValue(field.getHintValue())
                    .fieldType(field.getFieldType())
                    .active(field.isActive())
                    .build());
        } catch (ArrayIndexOutOfBoundsException e) {
            log.debug(loggerMessage);
            neighbors.add(new Field());
        }
        return neighbors;
    }

}
