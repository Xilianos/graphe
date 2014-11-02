package Model;

import java.util.HashSet;

/**
 * @author cedric
 *
 */
public class Vertex {

    public static enum Color {
        NONE,
        RED,
        BLUE,
        GREEN,
        YELLOW,
        PINK
    }

    private int number;
    private Color color;
    private HashSet<Integer> neighbors;

    public Vertex(int number, HashSet<Integer> neighbors) {
        this.number = number;
        this.color = Color.NONE;
        this.neighbors = new HashSet<Integer>(neighbors);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public HashSet<Integer> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(HashSet<Integer> neighbors) {
        this.neighbors = new HashSet<Integer>(neighbors);
    }

}
