package pl.dsyou.minesweeper.area.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import pl.dsyou.minesweeper.area.model.field.Field;

/**
 * <h1>Create game pl.minesweeper.fields/field</h1>
 * </p>
 * The pl.minesweeper.fields.model.GameArea Class implements an game field for this purpose
 * simply uses two-dimensional array of pl.minesweeper.fields.model.field.Field(the smallest unit of field)
 *
 * @author Dawid Janik
 * @since 20.03.2018
 */
@Slf4j
@Getter
@Setter
public class GameArea {

    private Field[][] fields;

    private int row; // n - lines of field
    private int column; // m - characters per one-n-line

    private static GameArea instance;

    private GameArea() {
    }

    /**
     * This method is showing game pl.minesweeper.fields/field on one square 2D
     * This method is used to visualization field.
     */
    public void showField() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                log.info(" " + fields[i][j].getFieldType());
            }
        }
    }

    public static GameArea getInstance() {
        if (instance == null) {
            instance = new GameArea();
        }
        return instance;
    }

}
