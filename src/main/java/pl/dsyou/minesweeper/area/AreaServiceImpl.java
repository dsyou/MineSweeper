package pl.dsyou.minesweeper.area;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.dsyou.minesweeper.area.model.GameArea;
import pl.dsyou.minesweeper.area.model.field.Field;

/**
 * @author Dawid Janik
 * @since 09.09.2018
 */
@Slf4j
@Service
public class AreaServiceImpl implements AreaService {

    @Override
    public GameArea getGameArea() {
        return getGameAreaInstance();
    }

    private GameArea getGameAreaInstance() {
        return GameArea.getInstance();
    }

    @Override
    public void createGameArea(int height, int width) {
        GameArea area = getGameAreaInstance();
        area.setColumn(width);
        area.setRow(height);
        area.setFields(initFieldArray(height, width));
    }

    private Field[][] initFieldArray(int height, int width) {
        Field[][] fields = new Field[height][width];
        for (int row = 0; row < height; row++)
            for (int col = 0; col < width; col++)
                fields[row][col] = new Field();
        return fields;
    }

    @Override
    public void showGameArea() {
        final GameArea gameArea = getGameAreaInstance();
        final int column = gameArea.getColumn();
        final int row = gameArea.getRow();

        Field[][] fields = gameArea.getFields();
        for (int i = 0; i < row; i++) {
            StringBuilder result = new StringBuilder();
            for (int j = 0; j < column; j++) {
                boolean active = fields[i][j].isActive();
                if (active) {
                    result.append("*");
                } else {
                    result.append(" ");
                }
            }
            log.info(String.valueOf(result));
        }
    }

    @Override
    public void getGameAreaHints() {
        final GameArea gameArea = getGameAreaInstance();
        final int column = gameArea.getColumn();
        final int row = gameArea.getRow();

        Field[][] fields = gameArea.getFields();
        for (int i = 0; i < row; i++) {
            StringBuilder result = new StringBuilder();
            for (int j = 0; j < column; j++) {
                final int hintValue = fields[i][j].getHintValue();
                    result.append(hintValue);
            }
            log.info(String.valueOf(result));
        }
    }

    @Override
    public boolean checkIsMine(Field field) {
        return field.isActive();
    }

    @Override
    public void updateFields(Field[][] fields) {
        GameArea area = getGameAreaInstance();
        area.setFields(fields);
    }

}
