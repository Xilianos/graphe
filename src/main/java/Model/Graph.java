package Model;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author cedric
 *
 */
public class Graph {

    private HashMap<Integer, Vertex> vertices;

    /**
     * The default constructor create an empty graph
     */
    public Graph() {
        this.vertices = new HashMap<Integer, Vertex>();
    }

    /**
     *
     * @return Return the neighbors list
     */
    public HashMap<Integer, Vertex> getVertices() {
        return vertices;
    }

    /**
     *
     * @param vertices Replace the Vertices list by this one
     */
    public void setVertices(HashMap<Integer, Vertex> vertices) {
        this.vertices = new HashMap<Integer, Vertex>(vertices);
    }

    /**
     * Add a vertex to the current graph with its dependencies if exists.
     * Be careful, if the vertex already exist, it won't be added to the current graph.
     * @param v Vertex to add
     * @return Return TRUE if vertex is successfully added, FALSE if it already exist into the current graph.
     */
    public boolean addVertex(Vertex v) {
        if (this.vertices.containsKey(v.getNumber())) {
            return false;
        }
        else {
            this.vertices.put(v.getNumber(), new Vertex(v));
            return true;
        }
    }

    /**
     * Add a vertex to the current graph with its dependencies if exists.
     * Be careful, if the vertex already exist, it won't be added to the current graph.
     * @param number The number of the vertex to add
     * @param neighbors The integer list of neighbors the vertex has
     * @return Return TRUE if the vertex is successfully added, FALSE if it already exists into the current graph.
     */
    public boolean addVertex(int number, HashSet<Integer> neighbors) {
        return this.addVertex(new Vertex(number, neighbors));
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
