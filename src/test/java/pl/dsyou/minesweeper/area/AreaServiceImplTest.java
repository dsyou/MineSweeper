package pl.dsyou.minesweeper.area;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.dsyou.minesweeper.area.model.GameArea;
import pl.dsyou.minesweeper.area.model.field.Field;

/**
 * @author Dawid Janik
 * @since 09.10.2018
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // <-- !!
public class AreaServiceImplTest {

    private final int HEIGHT = 3;
    private final int WIDTH = 4;

    @Autowired
    private AreaService areaService;

    @Test
    public void stage0_getGameArea() {
        GameArea gameArea = areaService.getGameArea();
        Assert.assertNotNull(gameArea);
    }

    @Test
    public void stage1_createGameArea() {
        areaService.createGameArea(HEIGHT, WIDTH);

        GameArea gameArea = areaService.getGameArea();
        Assert.assertNotNull(gameArea);

        Field[][] fields = gameArea.getFields();
        Assert.assertNotNull(fields);

        for (int row = 0; row < HEIGHT; row++)
            for (int col = 0; col < WIDTH; col++)
                Assert.assertNotNull(fields[row][col]);
    }

    @Test
    public void stage2_updateFields() {
        Field[][] fields = new Field[HEIGHT][WIDTH];
        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                fields[row][col] = new Field();
                fields[row][col].setActive(true);
            }
        }
        areaService.updateFields(fields);
    }

    @Test
    public void stage3_showGameArea() {
        GameArea gameArea = areaService.getGameArea();
        Assert.assertEquals(gameArea.getRow(), HEIGHT);
        Assert.assertEquals(gameArea.getColumn(), WIDTH);

        areaService.showGameArea();
    }

    @Test
    public void stage4_checkIsMine() {
        Field field = Field.builder()
                .active(true)
                .fieldType(FieldType.MINE)
                .hintValue(2)
                .build();
        final boolean containsMine = areaService.checkIsMine(field);
        Assert.assertTrue(containsMine);
    }

}