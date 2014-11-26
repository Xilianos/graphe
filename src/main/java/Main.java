import model.GraphBuilder;

/**
 * @author Cedric
 *
 */

public class Main {
    public static void main(String[] args) {
        GraphBuilder gb = new GraphBuilder("/home/cedric/graphe.graph");
        gb.getGraph().setColoration();
        System.out.println(gb.toString());
    }
}

