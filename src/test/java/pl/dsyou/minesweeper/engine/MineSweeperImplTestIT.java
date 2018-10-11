package pl.dsyou.minesweeper.engine;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.dsyou.minesweeper.area.AreaService;

/**
 * @author Dawid Janik
 * @since 09.10.2018
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // <-- !!
public class MineSweeperImplTestIT {

    private static final String MINEFIELD = "*...\n..*.\n....";

    private static final String BAD_MINEFIELD = "*...\n..*.\n....";

    @Autowired
    private MineSweeper mineSweeper;

    @Autowired
    private AreaService areaService;

    @Test
    public void stage0_setMineField() {
        mineSweeper.setMineField(MINEFIELD);

        // get the output
        areaService.showGameArea();

        // todo test with BAD_MINEFIELD
    }

    @Test
    public void stage1_getHintField() {
        mineSweeper.getHintField();
    }
}