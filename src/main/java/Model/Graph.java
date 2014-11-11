package Model;

import java.util.*;

/**
 * @author cedric
 *
 */
public class Graph {

    private HashMap<Integer, Vertex> vertices;
    public static Vertex.Color colors[] = {
                    Vertex.Color.RED, Vertex.Color.GREEN,
                    Vertex.Color.BLUE, Vertex.Color.YELLOW,
                    Vertex.Color.PINK, Vertex.Color.NONE
            };

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

    /**
     * Find all neighbors  reference of a vertex into a linked map
     * @param vertex Vertex for which find all neighbors reference
     * @return Return the neighbors linked map references of the vertex number given
     */
    public LinkedHashMap<Integer, Vertex> getNeighborsMap(int vertex) {
        LinkedHashMap<Integer, Vertex> neighbors = new LinkedHashMap<Integer, Vertex>();
        Vertex v = this.vertices.get(vertex);

        for (Integer i : v.getNeighbors()) {
            neighbors.put(i, this.vertices.get(i));
        }

        return neighbors;
    }

    /**
     * Set the coloration of all vertices
     */
    public void setColoration() {
        Vertex v;
        for (Integer i : this.vertices.keySet()) {
            v = this.vertices.get(i);
            this.setColorToVertex(v);
        }
    }

    /**
     * Find and set a color to the vertex given
     * May change other vertex color
     * @param vertex Vertex number for which find and set a color
     */
    public void setColorToVertex(int vertex) {
        this.setColorToVertex(this.vertices.get(vertex));
    }

    /**
     * Find and set a color to the vertex given
     * May change other vertex color
     * @param vertex Vertex for which find and set a color
     */
    public void setColorToVertex(Vertex vertex) {
        // Trouver la couleur la plus prioritaire
        Vertex.Color color = this.getPossibleColor(vertex);
        // L'affecter au sommet
        if (Vertex.Color.NONE != color) {
            vertex.setColor(color);
        }
        // Si pas de couleur trouvée, laisser NONE
        // Cas à gérer plus tard...
        else {

        }
    }

    /**
     * Find the first priority color to set for the given vertex
     * @param vertex Vertex for which find a color
     * @return Return the color found
     */
    public Vertex.Color getPossibleColor(Vertex vertex) {
        boolean colorsUsed[] = {false, false, false, false, false, false};
        Vertex.Color color;

        for (Integer i : vertex.getNeighbors()) {
            color = this.vertices.get(i).getColor();

            if (colors[0] == color) {
                colorsUsed[0] = true;
            }
            else if (colors[1] == color) {
                colorsUsed[1] = true;
            }
            else if (colors[2] == color) {
                colorsUsed[2] = true;
            }
            else if (colors[3] == color) {
                colorsUsed[3] = true;
            }
            else if (colors[4] == color) {
                colorsUsed[4] = true;
            }
        }

        for (int i = 0; i < 5; ++i) {
            if (!colorsUsed[i]) {
                return colors[i];
            }
        }

        return colors[5];
    }

    /**
     * Find the first priority color to set for the given vertex
     * @param vertex Vertex number for which find a color
     * @return Return the color found
     */
    public Vertex.Color getPossibleColor(int vertex) {
        return this.getPossibleColor(this.vertices.get(vertex));

    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder("");

        for (Integer i : this.vertices.keySet()) {
            ret.append(this.vertices.get(i).toString());
            ret.append("\n");
        }

        return ret.toString();
    }

}
