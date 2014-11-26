import model.GraphBuilder;

/**
 * @author Cedric
 *
 */

public class Main {
    public static void main(String[] args) {
        GraphBuilder gb = new GraphBuilder("/home/cedric/graphe.graph");
        gb.getGraph().recursiveColoration();
        System.out.println(gb.toString());

        if (gb.getGraph().isCorrectlyColored()) {
            System.out.println("Halleluya !!!!!!!");
        }
        else {
            System.out.println("Pfffff... nul !");
        }
    }
}

