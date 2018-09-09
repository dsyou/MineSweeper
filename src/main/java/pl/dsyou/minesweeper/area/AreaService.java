package pl.dsyou.minesweeper.area;

import pl.dsyou.minesweeper.area.model.GameArea;
import pl.dsyou.minesweeper.area.model.field.Field;


/**
 * @author Dawid Janik
 * @since 09.09.2018
 */
public interface AreaService {

    GameArea getGameArea();

    /**
     * Checking if current {@link Field} contains mine.
     *
     * @param field given current game field.
     * @return true if field contains mine, false if opposite.
     */
    boolean checkIsMine(Field field);

    /**
     * Update current field
     *
     * @param field given current game field.
     */
    void updateField(Field field);

    void updateFields(Field[][] field);

    void createGameArea(int height, int width);

}
