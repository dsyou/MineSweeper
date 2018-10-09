package pl.dsyou.minesweeper.area;

import pl.dsyou.minesweeper.area.model.GameArea;
import pl.dsyou.minesweeper.area.model.field.Field;


/**
 * @author Dawid Janik
 * @since 09.09.2018
 */
public interface AreaService {

    /**
     * todo dj
     * @return
     */
    GameArea getGameArea();

    /**
     * todo dj
     * @param height
     * @param width
     */
    void createGameArea(int height, int width);

    /**
     * This method is used to visualization field to UI console on one 2D square.
     */
    void showGameArea();

    /**
     * Checking if current {@link Field} contains mine.
     *
     * @param field given current game field.
     * @return true if field contains mine, false if opposite.
     */
    boolean checkIsMine(Field field);

    /**
     * todo dj
     * @param field
     */
    void updateFields(Field[][] field);

}
