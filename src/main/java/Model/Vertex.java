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
    private Color color = Color.NONE;
    private HashSet<Integer> neighbors;

    public Vertex(int number) {
        super();
        this.number = number;
        this.neighbors = new HashSet<Integer>();
    }

    public Vertex(int number, HashSet<Integer> neighbors) {
        super();
        this.number = number;
        if(neighbors == null) {
            this.neighbors = new HashSet<Integer>();
        }
        else {
            this.neighbors = new HashSet<Integer>(neighbors);
        }
    }

    public Vertex(Vertex v) {
        super();
        this.number = v.getNumber();
        this.color = v.getColor();
        if (v.getNeighbors() == null) {
            this.neighbors = new HashSet<Integer>();
        }
        else {
            this.neighbors = v.getNeighbors();
        }
    }

    /**
     *
     * @return Return the number
     */
    public int getNumber() {
        return number;
    }

    /**
     *
     * @param number Replace current number by this one
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     *
     * @return Return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     *
     * @param color Replace the current color by this one
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     *
     * @return Return the neighbors list
     */
    public HashSet<Integer> getNeighbors() {
        return neighbors;
    }

    /**
     *
     * @param neighbors Replace the neighbors list by this one
     */
    public void setNeighbors(HashSet<Integer> neighbors) {
        this.neighbors = new HashSet<Integer>(neighbors);
    }

    /**
     * Add a new neighbor to the current vertex
     * @param neighbor The neighbor reference to add
     * @return Return TRUE if the neighbor is successfully added, FALSE if it already exists
     */
    public boolean registerNeighbor(int neighbor) {
        if(this.neighbors.contains(neighbor)) {
            return false;
        }
        else {
            this.neighbors.add(neighbor);
            return true;
        }
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder("n:" + this.number + " c:" + this.color + " nei:");

        if (this.neighbors.size() > 0) {
            for (Integer i : this.neighbors) {
                ret.append(i);
                ret.append(" ");
            }
        }
        else {
            ret.append("void");
        }

        return ret.toString();
    }

}
