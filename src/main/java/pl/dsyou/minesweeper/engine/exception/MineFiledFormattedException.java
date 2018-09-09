package pl.dsyou.minesweeper.engine.exception;

/**
 * @author Dawid Janik
 * @since 23.03.2018
 */
public class MineFiledFormattedException extends IllegalArgumentException {
    public MineFiledFormattedException(String message) {
        super(message);
    }
}
