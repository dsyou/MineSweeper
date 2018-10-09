package pl.dsyou.minesweeper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.dsyou.minesweeper.area.AreaService;
import pl.dsyou.minesweeper.area.model.field.Field;

import static org.junit.Assert.assertNotEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AreaCreateTest {

    private static int height = 10;
    private static int width = 10;

    @Autowired
    private AreaService areaService;

    @Test
    public void stage0_createArea() {
        areaService.createGameArea(height, width);
        Field[][] fields = areaService.getGameArea().getFields();

        assertNotEquals(0, fields.length);
    }
}
