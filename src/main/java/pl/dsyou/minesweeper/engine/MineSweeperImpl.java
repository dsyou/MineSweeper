package pl.dsyou.minesweeper.engine;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pl.dsyou.minesweeper.area.AreaService;
import pl.dsyou.minesweeper.area.model.field.Field;
import pl.dsyou.minesweeper.engine.exception.MineFiledFormattedException;
import pl.dsyou.minesweeper.engine.exception.MineFiledInitialisedException;

import java.util.List;

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

        log.info(" Create game pl.minesweeper.fields[n x m] with pl.minesweeper.fields.Field");
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
    public String getHintField() throws MineFiledInitialisedException {

//        if (area == null) {
//            throw new IllegalArgumentException("Method setMineFiled() has not been called");
//        }
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                List<Field> neighbors = getNeighbors(i, j); //Get neighbors surrounding current pl.minesweeper.fields.Field
//                //System.out.println("L.size()"+l.size()); //Show me list.size() of incoming neighbors
//                if (neighbors == null) {
//                    log.error("Error Null Neighbors list"); // This situation should never happen
//                    System.exit(-1); // Exit immediately
//                }
//
//                if (neighbors.isEmpty()) { //Check list size: 0-mines, 8-no mine field
//                    for (int n = 0; n < 8; n++) {
//
//                        if (neighbors.get(n).isMine) { // If the neighbor is Mine increment current field.hintValue
//                            //System.out.println("Add +1");
//                            this.area[i][j].hintValue = this.area[i][j].hintValue + 1;//++ ;
//                        }
//
//                    }
//                }
//            }
//        }
//
//        return showOutput();
        return null;
    }

    /**
     * This method is used to get 8 neighbors of current field.
     * The return value is a list of neighbors.
     * It is Important to check size of list- mine_Cell is skipped.
     *
     * @param i current row value
     * @param j current column value
     * @return list of neighbors
     */
    private List<Field> getNeighbors(int i, int j) {

//        log.info("I=" + i + " J=" + j);
//        List<Field> neighbors = new ArrayList<>();
//
//        if (this.area[i][j].isMine) {
//            // Mine field return 0-elements list
//            log.info("Left pl.minesweeper.fields.Field with Mine " + "I=" + i + " J=" + j);
//        }
//        if (!this.area[i][j].isMine) { // If not mine check neighbors
//
//            //Corresponding schema to take all 8 neighbors
//            int left_x = j - 1;
//            int right_x = j + 1;
//            int top_y = i - 1;
//            int bottom_y = i + 1;
//
//
//            /**
//             * Each field has 8 neighbors on game pl.minesweeper.fields.
//             * If the field is on the edge (e.g [0,0]), and want take neighbor on the illegal position
//             * Throw Exception, replace this neighbor with new pl.minesweeper.fields.Field (not-mine).
//             * Non-periodic bound condition
//             *
//             * Position are:
//             * 0 - left top; 1- central top; 2- right top; 3-right center;
//             * 4- right bottom; 5-central bottom; 6-left bottom; 7-left bottom
//             *
//             * In polish:
//             * 0 - lewa gorna; 1 - centralna gorna; 2 - prawa gorna; 3 - prawa
//             * srodkowa 4 - prawa dolna; 5 - centralna dolna; 6 - lewa dolna;
//             * 7 - lewa srodkowa
//             */
//            for (int ii = 0; ii < 8; ii++) {
//                switch (ii) {
//                    case 0:
//                        try {
//                            neighbors.add(new Field(this.area[top_y][left_x]));
//                        } catch (ArrayIndexOutOfBoundsException e) {
////                            System.out.println("pl.minesweeper.fields.Field 0");
//                            neighbors.add(new Field());
//                        }
//                        break;
//                    case 1:
//                        try {
//                            neighbors.add(new Field(this.area[top_y][j]));
//                        } catch (ArrayIndexOutOfBoundsException e) {
////                            System.out.println("pl.minesweeper.fields.Field 1");
//                            neighbors.add(new Field());
//                        }
//                        break;
//                    case 2:
//                        try {
//                            neighbors.add(new Field(this.area[top_y][right_x]));
//                        } catch (ArrayIndexOutOfBoundsException e) {
////                            System.out.println("pl.minesweeper.fields.Field 2");
//                            neighbors.add(new Field());
//                        }
//                        break;
//                    case 3:
//                        try {
//                            neighbors.add(new Field(this.area[i][right_x]));
//                        } catch (ArrayIndexOutOfBoundsException e) {
////                            System.out.println("pl.minesweeper.fields.Field 3");
//                            neighbors.add(new Field());
//                        }
//                        break;
//                    case 4:
//                        try {
//                            neighbors.add(new Field(this.area[bottom_y][right_x]));
//                        } catch (ArrayIndexOutOfBoundsException e) {
////                            System.out.println("pl.minesweeper.fields.Field 4");
//                            neighbors.add(new Field());
//                        }
//                        break;
//                    case 5:
//                        try {
//                            neighbors.add(new Field(this.area[bottom_y][j]));
//                        } catch (ArrayIndexOutOfBoundsException e) {
////                            System.out.println("pl.minesweeper.fields.Field 5");
//                            neighbors.add(new Field());
//                        }
//                        break;
//                    case 6:
//                        try {
//                            neighbors.add(new Field(this.area[bottom_y][left_x]));
//                        } catch (ArrayIndexOutOfBoundsException e) {
////                            System.out.println("pl.minesweeper.fields.Field 6");
//                            neighbors.add(new Field());
//                        }
//                        break;
//                    case 7:
//                        try {
//                            neighbors.add(new Field(this.area[i][left_x]));
//                        } catch (ArrayIndexOutOfBoundsException e) {
////                            System.out.println("pl.minesweeper.fields.Field 7");
//                            neighbors.add(new Field());
//                        }
//                        break;
//                }
//            }
//        }
//        return (neighbors.size() > 8) ? null : neighbors; // If something go very wrong in size's return null
        return null;
    }

}
