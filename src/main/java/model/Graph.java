package model;

import java.util.*;

/**
 * @author cedric
 *
 */
public class Graph {

    private HashMap<Integer, Vertex> vertices;;

    public static Vertex.Color colors[] = {
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
     * Constructor which copy the given graph
     * @param graph Graph to copy
     */
    public Graph(Graph graph) {
        this.vertices = new HashMap<Integer, Vertex>(graph.getVertices());
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
    public boolean addVertex(int number, ArrayList<Integer> neighbors) {
        return this.addVertex(new Vertex(number, neighbors));
    }

    /**
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
     * Set the coloration of all vertices
     * Temps : O(nbSommets) + O(setColorToVertex)
     */
    public void setColoration() {
        // Récupération de tous les sommets du graphe
        Collection<Vertex> vertices = this.vertices.values();
        // Pour chaque sommet du graphe
        for (Vertex v : vertices) {
            // Calculer la couleur du sommet parcouru
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
     * Find and set a color to the given vertex
     * May change other vertex color
     * Temps : O(getPossibleColor) + O(else)
     * @param vertex Vertex for which find and set a color
     */
    public void setColorToVertex(Vertex vertex) {
        ArrayList<Integer> nei;
        Vertex v;
        // Trouver la couleur la plus prioritaire
        Vertex.Color color = this.getPossibleColor(vertex);
        // L'affecter au sommet
        if (Vertex.Color.NONE != color) {
            vertex.setColor(color);
        }
        // Si pas de couleur trouvée alors brique 6
        else {
            nei = this.getConnectedNeighbors(vertex);
            v = this.vertices.get(nei.get(0));
            this.permuteColors(v, v.getColor(), this.vertices.get(nei.get(2)).getColor());
            color = this.getPossibleColor(vertex);
            if(color == Vertex.Color.NONE) {
                v = this.vertices.get(nei.get(1));
                this.permuteColors(v, v.getColor(), this.vertices.get(nei.get(3)).getColor());
                vertex.setColor(this.getPossibleColor(vertex));
            }
            else {
                vertex.setColor(color);
            }
        }
    }

    /**
     *
     * @param vertex
     * @param c1
     * @param c2
     */
    public void permuteColors(Vertex vertex, Vertex.Color c1, Vertex.Color c2) {
        ArrayList<Vertex> nei = this.getArrayConnectedNeighbors(vertex);
        vertex.setColor(c2);
        for(Vertex v : nei) {
            if (v.getColor() == c2) {
                permuteColors(v, c2, c1);
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
     * Temps : O(nbVoisins) + O(nbVoisinsconnectés) + O(5)
     * @param vertex Vertex for which find a color
     * @return Return the color found
     */
    public Vertex.Color getPossibleColor(Vertex vertex) {
        ArrayList<Integer> nei = this.getConnectedNeighbors(vertex);
        boolean colorsUsed[] = {false, false, false, false, false, false};
        Vertex.Color color;

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
     * Find the first priority color to set for the given vertex
     * @param vertex Vertex number for which find a color
     * @return Return the color found
     */
    public Vertex.Color getPossibleColor(int vertex) {
        return this.getPossibleColor(this.vertices.get(vertex));
    }

    /**
     * Get the enabled neighbors count of the given vertex number
     * @param vertex Vertex number for which count enabled neighbors
     * @return The count of enabled neighbors
     */
    public int getCountEnabledNeighbors(Vertex vertex) {
        int count = 0;
        HashMap<Integer, Vertex> nei = this.getNeighborsMap(vertex);

        // Pour tous les voisins du sommet donné
        for(Vertex v : nei.values()) {
            // Si le voisin parcouru est connecté
            if(v.isConnected()) {
                // Alors il compte parmi les voisins encore connectés au graphe
                count++;
            }
        }

        return count;
    }

    /**
     * Verify if the current graph is correctly colored
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
     *
     */
    public void recursiveColoration() {
        Collection<Vertex> vertices = this.vertices.values();
        Vertex vertex = null;

        for(Vertex v : vertices) {
            if(v.isConnected() && this.getConnectedNeighbors(v).size() <= 5) {
                vertex = v;
                break;
            }
        }

        if(vertex != null) {
            vertex.disconnectVertex();
            this.recursiveColoration();
            vertex.reconnectVertex();
            this.setColorToVertex(vertex);
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
