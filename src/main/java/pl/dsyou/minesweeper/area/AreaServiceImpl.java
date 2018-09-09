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
        area.setFields(new Field[height][width]);
    }

    private GameArea getGameAreaInstance() {
        return GameArea.getInstance();
    }
}
