package Grids;

import java.util.Objects;

public class Coordinates {
    // Attributes
    private int x; // x coordinate
    private int y; // y coordinate

    /**
     * Initializes a set of coordinates
     * @param x The x coordinate
     * @param y The y coordinate
     */
    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Setter methods

    /**
     * Sets the x coordinate.
     * @param x The desired x coordinate.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the y coordinate.
     * @param y The desired y coordinate.
     */
    public void setY(int y) {
        this.y = y;
    }

    // Getter methods

    /**
     * Returns the x value of the coordinate.
     * @return The x value as an integer.
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y value of the coordinate.
     * @return The y value as an integer.
     */
    public int getY() {
        return y;
    }

    // String override
    @Override
    public String toString() {
        return x + ", " + y;
    }

    // Comparison overrides
    @Override
    public boolean equals(Object o) {
        Coordinates compare = (Coordinates) o;
        return this.x == compare.x && this.y == compare.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
