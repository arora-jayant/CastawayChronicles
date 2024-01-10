package Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import Grids.*;


public class player {

    public String name;
    public int coins;
    public int lives;
    private Coordinates position;
    List<String> inventory = new ArrayList<>();
    public island currentIsland;
    /**
     * Initializes a new Player with the given stats.
     * @param name The name of the player.
     */
    public player (String name){
        this.name = name;
        this.lives = 3;
        this.coins = 0;
        this.position = new Coordinates(0,0);
    }
    /**
     * Moves the player to the input coordinates given
     * This function will soon take positions from grid clicks as input.
     * @param coordinates The coordinates of the grid cell the player is moving to.
     */
    public void movePlayer(Coordinates coordinates){
        this.position.setX(coordinates.getX());
        this.position.setY(coordinates.getY());
    }

    /**
     * Returns the player's current position.
     * @return Position of the player
     */
    public Coordinates getCoordinates(){return this.position;}

    public List getInventory() {
        return this.inventory;
    }

    public void saveGame() {
        saveGame();

        // finish
    }

    public void setName(String name) {
        this.name = name;
    }

    public String pickupItem(String item) {
        if (currentIsland.itemsAvailable.contains(item)) {
            this.inventory.add(item);
            currentIsland.itemsAvailable.remove(item);
            return "Item Picked Up!";
        } else {
            return "Item Not Found!";
        }
    }

    public String dropItem(String item) {
        if (this.inventory.contains(item)) {
            this.inventory.remove(item);
            return "Item Removed!";
        } else {
            return "Item Not Found";
        }
    }

}
