package Grids;
import Player.island;

public class GridCell {

    // Attributes
    private Coordinates gridCoordinates;
    private island cellIsland;

    /**
     * Initializes a new grid cell at specified coordinates.
     * @param x The x coordinate of this grid cell.
     * @param y The y coordinate of this grid cell.
     */
    public GridCell(int x, int y) {
        gridCoordinates = new Coordinates(x, y);
    }

    /**
     * Set the cell island to the given island
     * @param island The island to assign to this cell
     */
    public void setCellIsland(island island) {
        this.cellIsland = island;
    }

    // Getter Methods
    /**
     * Returns the coordinates of this grid cell.
     * @return A coordinate object with the x and y coordinates.
     */
    public Coordinates getCoordinates() {
        return gridCoordinates;
    }

    /**
     * Returns whether or not this cell contains an island.
     * @return True if this cell contains an island, False otherwise.
     */
    public boolean hasIsland() { return cellIsland != null; }

    /**
     * Returns the island associated with this grid cell (if there is one)
     * @return The island object for this grid cell.
     */
    public island getIsland() { return cellIsland; }
}
