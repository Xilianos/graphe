package Model;

import java.util.HashMap;

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
    private HashMap<Integer, Vertex> neighbors;
    private boolean complete = false;

    public Vertex(int number) {
        super();
        this.number = number;
        this.neighbors = new HashMap<Integer, Vertex>();
    }

    public Vertex(int number, HashMap<Integer, Vertex> neighbors) {
        super();
        this.number = number;
        this.complete = true;
        if(neighbors == null) {
            this.neighbors = new HashMap<Integer, Vertex>();
        }
        else {
            this.neighbors = new HashMap<Integer, Vertex>(neighbors);
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
    private void setNumber(int number) {
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
    public HashMap<Integer, Vertex> getNeighbors() {
        return neighbors;
    }

    /**
     *
     * @param neighbors Replace the neighbors list by this one
     */
    public void setNeighbors(HashMap<Integer, Vertex> neighbors) {
        this.neighbors = new HashMap<Integer, Vertex>(neighbors);
        this.complete = true;
    }

    /**
     *
     * @return Return if the vertex is completely build
     */
    public boolean isComplete() {
        return complete;
    }

    /**
     *
     * @param complete Replace the current complete status by this one
     */
    private void setComplete(boolean complete) {
        this.complete = complete;
    }


    /**
     * Add a new neighbor to the current vertex
     * @param neighbor The neighbor reference to add
     * @return Return TRUE if the neighbor is successfully added, FALSE if it already exists
     */
    public boolean registerNeighbor(Vertex neighbor) {
        if(this.neighbors.containsKey(neighbor.getNumber())) {
            return false;
        }
        else {
            this.neighbors.put(neighbor.getNumber(), neighbor);
            return true;
        }
    }

    @Override
    public String toString() {
        return "n:" + this.number + " c:" + this.color;
    }

}
