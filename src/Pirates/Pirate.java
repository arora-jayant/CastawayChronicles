package Pirates;
import Grids.Coordinates;
import Grids.GridGenerator;

import java.util.Random;

public class Pirate {
    private Coordinates position;
    private PirateManager manager;

    /**
     * Pirate constructor
     * @param x The initial x position of the pirate
     * @param y The initial y position of the pirate
     * @param manager The pirate manager controlling this pirate
     */
    public Pirate(int x, int y, PirateManager manager) {
        position = new Coordinates(x, y);
        this.manager = manager;
    }


    /**
     * Move the pirate to a nearby valid location within 1 cell randomly
     */
    public void moveNearby() {
        // Initialize proposed destination
        Coordinates proposedDest = new Coordinates(-1, -1);

        do {
            // Randomize direction for moving
            int proposeXMove = 0;
            int proposeYMove = 0;

            // Loop until an actual move happens (not 0, 0)
            while (proposeXMove == 0 && proposeYMove == 0) {
                proposeXMove = new Random().nextInt(-1, 2);
                proposeYMove = new Random().nextInt(-1, 2);
            }

            // Get the proposed destination
            proposedDest = new Coordinates(position.getX() + proposeXMove, position.getY() + proposeYMove);

        } while(!canMoveToCell(proposedDest)); // Loop until valid destination is found

        // Remove pirate visual from previous location
        GridGenerator.gridView.popCellVisual(position.getX(), position.getY());

        // Set the pirates position to the new position
        position = proposedDest;
        GridGenerator.gridView.addToCellVisual(position.getX(), position.getY(), "Pirate.png");

    }

    /**
     * Check whether the proposed destination is within the grid, without an island, and not overlapping other pirates
     * @param proposedDest The proposed destination of the pirate
     * @return Whether the cell is valid and can be moved to.
     */
    public boolean canMoveToCell(Coordinates proposedDest) {

        // Check if the proposed destination is within the bounds of the grid and does not have an island
        if (proposedDest.getX() < 0 || proposedDest.getX() >= manager.getGrid().getGridSize()
            || proposedDest.getY() < 0 || proposedDest.getY() >= manager.getGrid().getGridSize()
            || manager.getGrid().getGridCell(proposedDest.getX(), proposedDest.getY()).hasIsland()) {

            return false;
        }

        // Check if other pirates are on the destination
        for (Pirate pirate : manager.getPirates()) {
            if (proposedDest.equals(pirate.position)) {
                return false;
            }
        }

        // Cell is free of islands, within bounds, and has no other pirates
        return true;
    }

    // Getter Methods

    /**
     * Get the pirates location on the grid
     * @return The pirate's coordinates
     */
    public Coordinates getLocation() {
        return position;
    }

}
