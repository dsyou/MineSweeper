package pl.dsyou.minesweeper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

/**
 * @author Dawid Janik
 * @since 09.09.2018
 */
@Slf4j
@EntityScan()
@ComponentScan()
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> getBeanNames(ctx);
    }

    private void getBeanNames(ApplicationContext ctx) {
        log.info("Let's inspect the beans provided by Spring Boot:");
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            log.info(beanName);
        }
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
