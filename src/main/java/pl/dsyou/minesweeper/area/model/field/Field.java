package pl.dsyou.minesweeper.area.model.field;

import lombok.*;
import pl.dsyou.minesweeper.area.FieldType;

/**
 * This class is simply object structure
 * of smallest part of field "called field".
 * This field's are representing state of mine-filed.
 *
 * @author Dawid Janik
 * @since 20.03.2018
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Field {
    private FieldType fieldType;
    private int hintValue;

    private boolean active;
}
