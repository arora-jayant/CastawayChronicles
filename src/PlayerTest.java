
import java.io.IOException;

import Grids.Coordinates;
import Grids.Grid;
import Grids.GridGenerator;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    void NavWithinGridTest() {
        GridGenerator.moveToGrid(0, 0);
        GridGenerator.movePlayer("SOUTH");
        GridGenerator.movePlayer("SOUTH");
        GridGenerator.movePlayer("SOUTH");
        GridGenerator.movePlayer("EAST");
        assertEquals(GridGenerator.p.getCoordinates().getX(), 1);
        assertEquals(GridGenerator.p.getCoordinates().getX(), 3);
    }
}
