package Grids;

import Views.GridView;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Objects;

import Player.player;

public class GridGenerator {

    // Attributes
    public static Grid currentGrid; // The currently focused grid.
    public static GridView gridView; // The gridView of the currently focused grid.
    private static HashMap<Coordinates, Grid> gridMap = new HashMap<>(); // A dynamic map of all generated grids.
    public static int gridSize; // The size of all grids generated.
    public static Stage mainStage; // The main stage of the application.
    public static player p = new player("Main Player"); //The player of the game



    /**
     * Generates a new grid at the specified *grid* coordinates (not to be confused with
     * inner grid coordinates).
     * @param x The x coordinate of the grid in the grid map.
     * @param y The y coordinate of the grid in the grid map.
     * @return The newly created grid.
     */
    private static Grid generateNewGrid(int x, int y) {
        // Generate the grid and set up coordinates
        Coordinates newGridPos = new Coordinates(x, y);
        Grid newGrid = new Grid(gridSize, newGridPos);

        // Add the newly generated grid to the grid mapping.
        gridMap.put(newGridPos, newGrid);

        // Return the newly made grid
        return newGrid;
    }


    /**
     * Switches the GridGenerators focus to the grid at the specified coordinates. If the grid does
     * not yet exist, it will be dynamically created.
     * @param x The x coordinate of the grid in the grid map.
     * @param y The y coordinate of the grid in the grid map.
     * @return The grid at the specified coordinates
     */
    public static Grid moveToGrid(int x, int y) {

        // Create coordinates out of passed parameters.
        Coordinates gridPos = new Coordinates(x, y);

        // Get the specified grid
        Grid moveGrid = getGrid(x, y);

        // Update currentGrid with the specified grid to move to
        currentGrid = moveGrid;

        // Visualize the new grid
        generateGridVisual();

        // Return the current grid
        return currentGrid;
    }

    /**
     * Takes the currently focused grid of the GridGenerator, creates a view for it, and displays it
     */
    private static void generateGridVisual() {
        if (gridView != null) {
            gridView.grid = currentGrid;
            gridView.buildTilePane();
        }
        else {
            gridView = new GridView(mainStage, currentGrid);
        }
    }

    // Getter methods

    /**
     * Obtain the grid at the specified grid map coordinates. If the grid does not yet exist, it will
     * be generated and then returned.
     * @param x The x coordinate of the grid in the grid map.
     * @param y The y coordinate of the grid in the grid map.
     * @return The grid at the specified coordinates.
     */
    public static Grid getGrid(int x, int y) {

        // Create coordinates out of passed parameters.
        Coordinates gridPos = new Coordinates(x, y);

        // Check if grid already exists
        if(gridMap.containsKey(gridPos)) return gridMap.get(gridPos);
        // Otherwise generate a new grid at the position and return that
        else return generateNewGrid(x, y);
    }

    /**
     * Gets the grid that the GridGenerator is currently focused on.
     * @return The currentGrid attribute.
     */
    public static Grid getCurrentGrid() {
        return currentGrid;
    }

    /**
     * Moves the player one position in the grid based on the given direction.
     * Updates the grid visually and clears the old cell it was on.
     * @param direction The direction the player is moving towards.
     */
    public static void movePlayer(String direction){
        gridView.popCellVisual(p.getCoordinates().getX(), p.getCoordinates().getY());
        if (Objects.equals(direction, "EAST")){ //If moving outside of grid, move to it
            if (p.getCoordinates().getX() == gridSize-1){
                moveToGrid(currentGrid.getGridPosition().getX() + 1, currentGrid.getGridPosition().getY());
                p.getCoordinates().setX(0);
            }else { // Otherwise, stay in the same grid and move player
                p.getCoordinates().setX(p.getCoordinates().getX() + 1);
            }

        } else if (Objects.equals(direction, "WEST")){
            if (p.getCoordinates().getX() == 0){
                moveToGrid(currentGrid.getGridPosition().getX() - 1, currentGrid.getGridPosition().getY());
                p.getCoordinates().setX(gridSize-1);
            }else {
                p.getCoordinates().setX(p.getCoordinates().getX() - 1);
            }

        } else if (Objects.equals(direction, "NORTH")){
            if (p.getCoordinates().getY() == 0){
                moveToGrid(currentGrid.getGridPosition().getX(), currentGrid.getGridPosition().getY() - 1);
                p.getCoordinates().setY(gridSize-1);
            }else {
                p.getCoordinates().setY(p.getCoordinates().getY() - 1);
            }

        } else if (Objects.equals(direction, "SOUTH")){
            if (p.getCoordinates().getY() == gridSize-1){
                moveToGrid(currentGrid.getGridPosition().getX(), currentGrid.getGridPosition().getY() + 1);
                p.getCoordinates().setY(0);
            }else {
                p.getCoordinates().setY(p.getCoordinates().getY() + 1);
            }        }

        //Update the location
        gridView.addToCellVisual(p.getCoordinates().getX(), p.getCoordinates().getY(), "SHIP_1.png");

        // Move pirates
        currentGrid.getPirateManager().movePirates();
    }

}
