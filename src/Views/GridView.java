package Views;

import Grids.Grid;
import Grids.GridGenerator;
import Pirates.Pirate;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;

public class GridView {
    private Stage stage; // The stage to render the grid on
    private GridPane gridPane = new GridPane(); // The grid pane to position elements on
    public Grid grid; // The grid which this visualization is based on
    private TilePane tilePane = new TilePane(); // The tilePane that holds all of the individual grid cells

    // Class constructor accepts stage
    public GridView(Stage stage, Grid grid) {
        this.stage = stage;
        this.grid = grid;
        initializeUI();
    }

    public void initializeUI() {

        // Setup Stage
        this.stage.setTitle("Castaway Chronicles");
        this.stage.setFullScreen(true);
        this.stage.setResizable(true);

        // Setup Grid pane basics
        gridPane.setBackground(new Background(new BackgroundFill(
                Color.valueOf("#1AA7EC"),
                new CornerRadii(0),
                new Insets(0)
        )));

        // TODO DEBUG LINES: REMOVE
        gridPane.setGridLinesVisible(true);

        // Setup Grid pane grid
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(15);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(70);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(15);
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(15);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(70);
        RowConstraints row3 = new RowConstraints();
        row3.setPercentHeight(15);

        // Create rows and columns in grid pane
        gridPane.getColumnConstraints().addAll(column1, column2, column3);
        gridPane.getRowConstraints().addAll(row1, row2, row3);

        // Set up tile pane for holding the grid cells
        tilePane.setPrefTileWidth(80);
        tilePane.setPrefTileHeight(80);
        tilePane.setPrefRows(10);
        tilePane.setPrefColumns(10);
        tilePane.setMinSize(800, 800);
        tilePane.setMaxSize(800, 800);

        // Add tilePane to gridPane
        gridPane.add(tilePane, 1, 1);

        // Align the tile pane in center of center pane
        GridPane.setHalignment(tilePane, HPos.CENTER);
        GridPane.setValignment(tilePane, VPos.CENTER);

        // Build tile pane with initial grid
        buildTilePane();

        // Setup Scene
        Scene scene = new Scene(gridPane);
        this.stage.setScene(scene);


        // Display window
        this.stage.show();

        //todo remove/replace in a more appropriate place if needed
        addTextHandlingEvent();

    }


    /**
     * Reconstructs the tile pane for the current grid
     */
    public void buildTilePane() {

        // Clear existing tile pane
        tilePane.getChildren().clear();

        // Add tiles that represent the game's gridCells to tilePane
        int tilePaneIndex = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                // Stack pane for stacking elements in tile
                StackPane stackPane = new StackPane();

                // Background of cell
                Rectangle gridCellVisual = new Rectangle(0, 0, 80, 80);
                gridCellVisual.setFill(Color.CORNFLOWERBLUE);
                gridCellVisual.setStroke(Color.NAVY);

                // Add background of cell to stack pane
                stackPane.getChildren().add(gridCellVisual);

                // Add stack pane to tile pane
                tilePane.getChildren().add(tilePaneIndex, stackPane);
                tilePaneIndex++;

                // Check if island should be added to cell
                if(grid.getGridCell(j, i).hasIsland()) {
                    addToCellVisual(j, i, "Island.png");
                }

            }
        }

        // Draw pirates on grid
        drawAllPirates();
    }

    /**
     * Draw all the pirates for this grid. (To be used for initialization)
     */
    private void drawAllPirates() {
        for (Pirate pirate : grid.getPirateManager().getPirates()) {
            addToCellVisual(pirate.getLocation().getX(), pirate.getLocation().getY(), "Pirate.png");
        }
    }

    /**
     * Takes in keyboard input from the player and calls the movePlayer
     * function as needed.
     */
    public void addTextHandlingEvent() {
        stage.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.W)){
                GridGenerator.movePlayer("NORTH");
            }
            else if (keyEvent.getCode() == KeyCode.A){
                GridGenerator.movePlayer("WEST");
            }
            else if (keyEvent.getCode().equals(KeyCode.S)){
                GridGenerator.movePlayer("SOUTH");
            }
            else if (keyEvent.getCode() == KeyCode.D){
                GridGenerator.movePlayer("EAST");
            }
        }   );
    }


    /**
     * Gets the cell stack pane at the specified coordinates
     * @param x The x coordinate of the desired grid cell.
     * @param y The y coordinate of the desired grid cell.
     * @return The associated stackPane with the given coordinates.
     */
    private StackPane getCellStackPane(int x, int y) {
        return (StackPane) tilePane.getChildren().get(x + y * grid.getGridSize());
    }


    /**
     * Clears the overlapping image from the specified grid cell.
     * @param x The x coordinate of the desired grid cell.
     * @param y The y coordinate of the desired grid cell.
     */
    public void popCellVisual(int x, int y) {
        // Get the stack pane
        StackPane stackPane = getCellStackPane(x, y);

        // Check if stack pane has more than 1 element (means there is an element overlapping)
        if (stackPane.getChildren().size() > 1) {

            // Remove top most layer from cell visual
            stackPane.getChildren().remove(stackPane.getChildren().size()-1);
        }
    }

    /**
     * Adds the specified image onto the specified grid cells visual display.
     * @param x The x coordinate of the desired grid cell
     * @param y The y coordinate of the desired grid cell
     * @param imageName The name of the image -> Example: "apple.png"
     */
    public void addToCellVisual(int x, int y, String imageName) {
        // Get the stack pane
        StackPane stackPane = getCellStackPane(x, y);

        // Get the image to apply
        Image image = new Image("./Images/" + imageName);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(80);
        imageView.setFitHeight(80);

        // Add image onto stack pane
        stackPane.getChildren().add(imageView);

    }

}
