package pl.minesweeper.core;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import pl.minesweeper.core.service.MineSweeper;

/**
 * @author Dawid Janik
 * @since 23.03.2018
 */
@NoArgsConstructor
@RequiredArgsConstructor
public class Game {

    private final MineSweeper mineSweeper;

    public void startGame(String field) {
        mineSweeper.setMineField(field);
    }

}
