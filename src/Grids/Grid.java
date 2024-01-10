package Grids;

import java.util.Random;

import Pirates.PirateManager;
import Player.island;
import Views.GridView;

public class Grid {

    // Attributes
    private final int gridSize; // The n x n size of the grid.
    private final Coordinates gridPosition; // The coordinates of the grid in the grid mapping.
    private GridCell[][] gridMap; // A two-dimensional array holding all the grid cells.
    private PirateManager pirateManager; // The pirate manager to control all pirates on this grid

    /**
     * Initialize an empty nxn grid.
     * @param gridSize The n size of the grid
     * @param gridPosition The position of the grid relative to other grids
     */
    public Grid(int gridSize, Coordinates gridPosition) {
        this.gridSize = gridSize;
        this.gridPosition = gridPosition;
        generateGrid();

        pirateManager = new PirateManager(this);
    }


    /**
     * Generates the grid cells and places them in gridMap according to the gridSize parameter.
     */
    private void generateGrid() {
        // Define the empty gridMap
        gridMap = new GridCell[gridSize][gridSize];

        // Generate all grid cells for the gridMap
        for(int row = 0; row < gridSize; row++) {
            for(int column = 0; column < gridSize; column++) {
                gridMap[row][column] = new GridCell(row, column);
            }
        }

        // Pick random amount of islands
        int islands = new Random().nextInt(1, 4);

        // Place islands on random cells
        while (islands > 0) {

            // Get random cell coordinates
            int randX = new Random().nextInt(0, gridSize);
            int randY = new Random().nextInt(0, gridSize);

            // Check if island already placed here
            if(gridMap[randX][randY].hasIsland()) {
                continue;
            }

            // Set island on cell
            island island = new island();
            gridMap[randX][randY].setCellIsland(island);

            islands--;
        }

    }


    // Getter Methods

    /**
     * Gets the grid cell at the specified coordinates.
     * @param x The x coordinate of the grid cell.
     * @param y The y coordinate of the grid cell.
     * @return The grid cell at the specified coordinates.
     */
    public GridCell getGridCell(int x, int y) {
        return gridMap[x][y];
    }


    /**
     * Gets the coordinates of this grid in the total grid mapping.
     * @return The grid position as coordinates.
     */
    public Coordinates getGridPosition() {
        return gridPosition;
    }

    /**
     * Gets the size of the grid.
     * @return The size of the grid.
     */
    public int getGridSize() { return gridSize; }


    /**
     * Gets the pirate manager associated with this grid
     * @return The pirate manager
     */
    public PirateManager getPirateManager() {
        return pirateManager;
    }

}
