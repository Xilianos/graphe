package view;

import controller.GraphBuilder;

/**
 * @author cedric
 *         .
 */
public abstract class GraphView {
    protected GraphBuilder controller;

    public GraphView(GraphBuilder controller) {
        this.controller = controller;
    }

    public final GraphBuilder getController() {
        return this.controller;
    }

    public abstract String openFile();
    public abstract void display();
    public abstract void close();
}
