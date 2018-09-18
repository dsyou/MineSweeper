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

    @Override
    public void showGameArea() {
        final GameArea gameArea = getGameAreaInstance();
        final int column = gameArea.getColumn();
        final int row = gameArea.getRow();

        Field[][] fields = gameArea.getFields();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                boolean active = fields[i][j].isActive();
                if (active) {
                    log.info(" " + FieldType.MINE.toString());
                } else {
                    log.info(" " + FieldType.NORAML.toString());
                }
            }
        }
    }

    @Override
    public boolean checkIsMine(Field field) {
        return field.isActive();
    }

    @Override
    public void updateField(Field field) {

    }

    @Override
    public void updateFields(Field[][] fields) {
        GameArea area = getGameAreaInstance();
        area.setFields(fields);
    }

    @Override
    public void createGameArea(int height, int width) {
        GameArea area = getGameAreaInstance();
        area.setColumn(width);
        area.setRow(height);
        area.setFields(new Field[height][width]);
    }

    private GameArea getGameAreaInstance() {
        return GameArea.getInstance();
    }


}
