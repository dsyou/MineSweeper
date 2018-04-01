package pl.minesweeper.area.model;

import lombok.*;

/**
 * This class is simply object structure
 * of smallest part of field "called cell".
 * This cell's are representing state of mine-filed.
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
public class Cell {

    //todo make enum of choices on the field
    protected String mine = "*";
    protected String noMine = "";

    // False- no mine, True - mine cell
    protected boolean active = false;

    protected String value = "";
    protected Integer hintValue = 0;

}
