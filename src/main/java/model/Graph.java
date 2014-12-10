package model;

import java.util.*;

/**
 * @author cedric
 *
 */
public class Graph {

    private HashMap<Integer, Vertex> vertices;

    public static final Vertex.Color colors[] = {
                    Vertex.Color.RED, Vertex.Color.GREEN,
                    Vertex.Color.BLUE, Vertex.Color.YELLOW,
                    Vertex.Color.PINK, Vertex.Color.NONE
            };

    /**
     * The default constructor create an empty graph
     * @param capacity Number of future vertices
     */
    public Graph(int capacity) {
        this.vertices = new HashMap<Integer, Vertex>(capacity);
    }

    /**
     * JUnit tests
     * @return Return the vertices list
     */
    public HashMap<Integer, Vertex> getVertices() {
        return vertices;
    }

    /**
     * JUnit tests
     * @param vertices Replace the Vertices list by this one
     */
    public void setVertices(HashMap<Integer, Vertex> vertices) {
        this.vertices = new HashMap<Integer, Vertex>(vertices);
    }

    /**
     * JUnit tests
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
     * JUnit tests
     * Add a vertex to the current graph with its dependencies if exists.
     * Be careful, if the vertex already exist, it won't be added to the current graph.
     * @param number The number of the vertex to add
     * @param neighbors The integer list of neighbors the vertex has
     * @return Return TRUE if the vertex is successfully added, FALSE if it already exists into the current graph.
     */
    public boolean addVertex(int number, ArrayList<Integer> neighbors) {
        return this.addVertex(new Vertex(number, neighbors));
    }

    /**
     * JUnit tests
     * Find all neighbors  reference of a vertex into a linked map
     * Temps : O(nbVoisins)
     * @param vertex Vertex for which find all neighbors reference
     * @return Return the neighbors linked map references of the vertex number given
     */
    public HashMap<Integer, Vertex> getNeighborsMap(Vertex vertex) {
        HashMap<Integer, Vertex> neighbors = new HashMap<Integer, Vertex>();

        for (Integer i : vertex.getNeighbors()) {
            neighbors.put(i, this.vertices.get(i));
        }

        return neighbors;
    }

    /**
     * JUnit tests
     * Find and set a color to the vertex given
     * May change other vertex color
     * @param vertex Vertex number for which find and set a color
     */
    public void setColorToVertex(int vertex) {
        this.setColorToVertex(this.vertices.get(vertex));
    }

    /**
     * Find and set a color to the given vertex
     * May change other vertex color
     * Temps : O(n)
     * Mémoire : 5 + 1 + 3*12 + 2 * 5n = O(n)
     * @param vertex Vertex for which find and set a color
     */
    public void setColorToVertex(Vertex vertex) {
        ArrayList<Integer> nei; // Mémoire : 5
        Vertex v; // Mémoire : 1
        // Trouver la couleur la plus prioritaire
        Vertex.Color color = this.getPossibleColor(vertex); // Mémoire : 12
        // L'affecter au sommet
        if (Vertex.Color.NONE != color) {
            vertex.setColor(color);
        }
        // Si pas de couleur trouvée alors brique 6
        else {
            nei = this.getConnectedNeighbors(vertex);
            v = this.vertices.get(nei.get(0));
            this.permuteColors(v, v.getColor(), this.vertices.get(nei.get(2)).getColor()); // Mémoire : 5n
            color = this.getPossibleColor(vertex); // Mémoire : 12
            if(color == Vertex.Color.NONE) {
                v = this.vertices.get(nei.get(1));
                this.permuteColors(v, v.getColor(), this.vertices.get(nei.get(3)).getColor()); // Mémoire : 5n
                vertex.setColor(this.getPossibleColor(vertex)); // Mémoire : 12
            }
            else {
                vertex.setColor(color);
            }
        }
    }

    /**
     * Permute two colors recursively into all the connected component
     * Temps : O(nbVoisins <= 5) * O(n) = O(n)
     * Mémoire : 5n
     * @param vertex Vertex in which replace the color 1 by the color 2
     * @param c1 Current color to remember for the recursion
     * @param c2 Color to set
     */
    public void permuteColors(Vertex vertex, Vertex.Color c1, Vertex.Color c2) {
        ArrayList<Vertex> nei = this.getArrayConnectedNeighbors(vertex); // Mémoire : 5
        vertex.setColor(c2);
        for(Vertex v : nei) {
            if (v.getColor() == c2) {
                permuteColors(v, c2, c1); // Au pire n appels
            }
        }
    }

    /**
     * List the connected neighbors without the ones disconnected
     * Temps : O(nbVoisins) + O(n?)
     * @param vertex Vertex on which make the list
     * @return Return the list of connected neighbors
     */
    public ArrayList<Integer> getConnectedNeighbors(Vertex vertex) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        ArrayList<Integer> nei = vertex.getNeighbors();

        for(int i : nei) {
            if(this.getVertices().get(i).isConnected()) {
                result.add(i);
            }
        }

        return result;
    }

    /**
     * List the connected neighbors without the ones disconnected
     * Temps : O(nbVoisins)
     * @param vertex Vertex on which make the list
     * @return Return the list of connected neighbors
     */
    public ArrayList<Vertex> getArrayConnectedNeighbors(Vertex vertex) {
        ArrayList<Vertex> result = new ArrayList<Vertex>();
        ArrayList<Integer> nei = vertex.getNeighbors();

        for(int i : nei) {
            if(this.getVertices().get(i).isConnected()) {
                result.add(this.getVertices().get(i));
            }
        }

        return result;
    }

    /**
     * Find the first priority color to set for the given vertex
     * Mémoire : 5 + 6 + 1 = 12
     * @param vertex Vertex for which find a color
     * @return Return the color found
     */
    public Vertex.Color getPossibleColor(Vertex vertex) {
        ArrayList<Integer> nei = this.getConnectedNeighbors(vertex); // Mémoire : 5
        boolean colorsUsed[] = {false, false, false, false, false, false}; // Mémoire : 6
        Vertex.Color color; // Mémoire : 1

        // Pour tous les voisins du sommet donné
        for (Integer i : nei) {
            // On récupère la couleur du voisin parcouru
            color = this.vertices.get(i).getColor();

            // S'il est rouge alors on bloque la couleur rouge
            if (colors[0] == color) {
                colorsUsed[0] = true;
            }
            // Sinon s'il est vert alors on bloque la couleur verte
            else if (colors[1] == color) {
                colorsUsed[1] = true;
            }
            // Sinon s'il est bleu alors on bloque la couleur bleue
            else if (colors[2] == color) {
                colorsUsed[2] = true;
            }
            // Sinon s'il est jaune alors on bloque la couleur jaune
            else if (colors[3] == color) {
                colorsUsed[3] = true;
            }
            // Sinon s'il est rose alors on bloque la couleur rose
            else if (colors[4] == color) {
                colorsUsed[4] = true;
            }
        }

        // Pour le nombre de couleur existantes (5 couleurs)
        for (int i = 0; i < 5; ++i) {
            // Si la couleur n'est pas marquée comme utilisée
            if (!colorsUsed[i]) {
                // Alors on retourne cette couleur
                return colors[i];
            }
        }

        // Si on arrive ici, alors aucune couleur n'est possible
        // On retourne la couleur NONE
        return colors[5];
    }

    /**
     * JUnit tests
     * Find the first priority color to set for the given vertex
     * @param vertex Vertex number for which find a color
     * @return Return the color found
     */
    public Vertex.Color getPossibleColor(int vertex) {
        return this.getPossibleColor(this.vertices.get(vertex));
    }

    /**
     * Verify if the current graph is correctly colored
     * Temps : O(n²) (si chaque sommet a comme voisins tous les autres sommets du graphe)
     * @return Return true if current graph is correctly colored else false
     */
    public boolean isCorrectlyColored() {
        Collection<Vertex> vertices = this.vertices.values();
        ArrayList<Integer> neighbors;

        // Pour chaque sommets existants dans le graphe
        for(Vertex vertex : vertices) {
            // On récupère les voisins du sommet parcouru
            neighbors = this.vertices.get(vertex.getNumber()).getNeighbors();
            // Pour tous les voisins du sommet parcouru
            for(int nei : neighbors) {
                // Si un voisin a la même couleur
                // ou si le sommet parcouru n'a pas de couleur attribuée
                if(vertex.getColor() == this.vertices.get(nei).getColor()
                        || vertex.getColor() == Vertex.Color.NONE) {
                    System.out.println(vertex);
                    System.out.println(this.vertices.get(nei));
                    // Alors le graphe n'est pas correctement coloré
                    return false;
                }
            }
        }

        // Si on arrive ici, alors aucune erreur de couleur n'a été détectée
        return true;
    }

    /**
     * Temps : n * (n + n) = O(n²)
     * Mémoire : n * (n + 1 + O(n))
     *          = n * O(n) = O(n²)
     */
    public void recursiveColoration() {
        Collection<Vertex> vertices = this.vertices.values(); // Mémoire : n
        Vertex vertex = null; // Mémoire : 1

        for(Vertex v : vertices) {
            if(v.isConnected() && this.getConnectedNeighbors(v).size() <= 5) {
                vertex = v;
                break;
            }
        }

        if(vertex != null) {
            vertex.disconnectVertex();
            this.recursiveColoration(); // n appels
            vertex.reconnectVertex();
            this.setColorToVertex(vertex); // Mémoire : O(n)
        }
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
