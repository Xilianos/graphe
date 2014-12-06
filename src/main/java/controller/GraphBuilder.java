package controller;

import model.Graph;
import view.GraphView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author cedric
 *
 */
public class GraphBuilder {

    private Graph graph;
    private GraphView view;

    public GraphBuilder() {

    }

    public void buildGraph(String filePath) {
        int nbVertices;
        // Ouvrir le fichier
        try{
            BufferedReader buff = new BufferedReader(new FileReader(filePath));

            try {
                String line;
                // Lecture du nombre de sommets
                if ((line = buff.readLine()) != null) {
                    nbVertices = Integer.parseInt(line);
                    this.graph = new Graph(nbVertices);
                }
                else {
                    throw new IOException();
                }

                // Parcours du fichier
                for (int i = 0; i < nbVertices; ++i) {
                    line = buff.readLine();
                    this.parseLine(line);
                }
            } finally {
                buff.close();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Parse the line given to add the corresponding vertex
     * @param line Line to parse
     */
    private void parseLine(String line) {
        StringBuilder integer = new StringBuilder("");
        int vertex = -1;
        ArrayList<Integer> neighbors = new ArrayList<Integer>();
        char c;

        for (int i = 0; i < line.length(); ++i) {
            c = line.charAt(i);

            if (c == ':') {
                // Traitement du nombre du sommet lu
                vertex = Integer.parseInt(integer.toString());
                // Vidage du buffer integer
                integer.delete(0, integer.length());
                i += 2;
            }
            else {
                if (c == ']') {
                    if (integer.length() > 0) {
                        // Traitement du dernier voisin lu
                        neighbors.add(Integer.parseInt(integer.toString()));
                        // Vidage du buffer integer
                        integer.delete(0, integer.length());
                    }
                    i = line.length();
                }
                else if (c == ',') {
                    // Traitement du nombre du voisin lu
                    neighbors.add(Integer.parseInt(integer.toString()));
                    // Vidage du buffer integer
                    integer.delete(0, integer.length());
                    i++;
                }
                else {
                    integer.append(c);
                }
            }
        }

        this.graph.addVertex(vertex, neighbors);
        // Vidage de la liste des voisins
        neighbors.clear();
    }

    /**
     * Get the current graph
     * @return Return the graph
     */
    public Graph getGraph() {
        return graph;
    }

    /**
     * Register the given view into the current controller
     * @param view View to add
     */
    public void registerView(GraphView view) {
        String filePath;
        this.view = view;
        filePath = this.view.openFile();

        if(filePath != null) {
            this.buildGraph(filePath);
            this.graph.recursiveColoration();
            // TODO: Dessiner le graphe dans la vue

            this.view.display();
        }
        else {
            this.unregisterView(this.view);
        }
    }

    /**
     * Unregister the given view reference from the current controller
     * @param view View to remove
     */
    public void unregisterView(GraphView view) {
        view.close();
        System.exit(0);
    }

    @Override
    public String toString() {
        return this.graph.toString();
    }

}