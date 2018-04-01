package pl.minesweeper.area.model;

import lombok.*;

import java.util.Arrays;

/**
 * <h1>Create game pl.minesweeper.area/field</h1>
 * </p>
 * The pl.minesweeper.area.model.Field Class implements an game field for this purpose
 * simply uses two-dimensional array of pl.minesweeper.area.model.Cell(the smallest unit of field)
 *
 * @author Dawid Janik
 * @since 20.03.2018
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Field {

    private Cell[][] area;

    private int n; // n - lines of field
    private int m; // m - characters per one-n-line

    /**
     * This method is showing game pl.minesweeper.area/field on one square 2D
     * This method is used to visualization field.
     */
    public void showField() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(" " + area[i][j].value);
            }
            System.out.println();
        }
    }

}
