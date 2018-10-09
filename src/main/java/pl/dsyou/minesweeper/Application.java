package pl.dsyou.minesweeper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Dawid Janik
 * @since 09.09.2018
 */
@Slf4j
@EntityScan()
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    //        Game game = new Game();
    //
    //        if (args.length == 1) {
    //            String field = args[0]; // 1 Program arguments taken form user, represent field
    //            game.startGame(field);
    //        } else {
    //            throw new MineFiledFormattedException();
    //        }
    //
    //        field.showField();
    //        System.out.println(field.getHintField());
    ////      a.showHint(); //todo user should have option in game to see hints

}
