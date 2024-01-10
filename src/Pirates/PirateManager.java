package Pirates;

import Grids.Coordinates;
import Grids.Grid;
import Grids.GridGenerator;

import java.util.ArrayList;
import java.util.Random;

public class PirateManager {

    private ArrayList<Pirate> pirates = new ArrayList<>();
    private Grid grid;

    public PirateManager(Grid grid) {

        this.grid = grid;

        // Create a random amount of pirates and place them on the board
        int pirates = new Random().nextInt(0, 3);
        createPirates(pirates);

    }

    private void createPirates(int amount) {

        // Iterate through amount
        for (int i = 0; i < amount; i++) {

            // Find valid starting location for pirate
            Coordinates position;
            do {
                // Random location
                int pirateX = new Random().nextInt(0, grid.getGridSize());
                int pirateY = new Random().nextInt(0, grid.getGridSize());

                // Position
                position = new Coordinates(pirateX, pirateY);

            } while(!validStartingLocation(position));

            // Define Pirate
            Pirate pirate = new Pirate(position.getX(), position.getY(), this);

            // Add pirate to pirate list
            pirates.add(pirate);

        }

    }

    /**
     * Verifies whether the starting location for a pirate is completely empty.
     * @param position The proposed position
     * @return A true or false for whether the location is valid or not
     */
    private boolean validStartingLocation(Coordinates position) {
        // Check if the proposed destination is within the bounds of the grid and does not have an island
        if (position.getX() < 0 || position.getX() >= grid.getGridSize()
                || position.getY() < 0 || position.getY() >= grid.getGridSize()
                || grid.getGridCell(position.getX(), position.getY()).hasIsland()) {

            return false;
        }

        // Check if other pirates are on the destination
        for (Pirate pirate : pirates) {
            if (position.equals(pirate.getLocation())) {
                return false;
            }
        }

        // Check if pirate is on player
        if (position.equals(GridGenerator.p.getCoordinates())) {
            return false;
        }

        // Starting location is empty
        return true;
    }

    /**
     * Move all pirates controlled by this manager to a nearby location
     */
    public void movePirates() {
        for (Pirate pirate : pirates) {
            pirate.moveNearby();
        }
    }

    // Getter Methods

    /**
     * Gets the pirates associated with this manager.
     * @return A list of pirates
     */
    public ArrayList<Pirate> getPirates() {
        return pirates;
    }


    /**
     * Gets the grid this manager is assigned too.
     * @return The associated grid
     */
    public Grid getGrid() {
        return grid;
    }

}
