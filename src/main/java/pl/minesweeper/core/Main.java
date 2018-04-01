package pl.minesweeper.core;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <h1>pl.minesweeper.core.service.MineSweeper Game<h1/>
 * The pl.minesweeper.core.service.MineSweeper implements an application that
 * simply generate field of N x M squares represented game field
 * for pl.minesweeper.core.service.MineSweeper game.
 * This application can also return hint-field.
 * <p>
 * Rules and input-output value are describe in the
 * pl.minesweeper.core.service.MineSweeper interface.
 * <p/>
 *
 * @author Dawid Janik
 * @since 20.03.2018
 */
@Slf4j
@AllArgsConstructor
public class Main {
    /**
     * This is the main method which makes use of setMineFiled and showFiled method
     * taken from pl.minesweeper.core.service.MineSweeper interface.
     *
     * @param args unused or used depends of method giving a initial value.
     * @return Nothing
     */
    public static void main(String[] args) {
        System.out.println("Running App pl.minesweeper.core.service.MineSweeper\n");

        Game game = new Game();

        if (args.length == 1) {
            String field = args[0]; // 1 Program arguments taken form user, represent field
            game.startGame(field);
        } else {
            throw new TooMuchArgumentsException();
        }

        field.showField();
        System.out.println(field.getHintField());
//      a.showHint(); //todo user should have option in game to see hints

    }


}
