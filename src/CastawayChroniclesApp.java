import Grids.Grid;
import Grids.GridGenerator;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class CastawayChroniclesApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    // Override start method to initialize view
    @Override
    public void start(Stage mainStage) throws IOException {
        GridGenerator.gridSize = 10;
        GridGenerator.mainStage = mainStage;
        GridGenerator.moveToGrid(0, 0);
        //GridGenerator.startGameTempMoveTest(); //TODO REMOVE


    }
}
