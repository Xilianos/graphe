import controller.GraphBuilder;
import view.GraphView;
import view.JGraphFrame;

/**
 * @author Cedric
 *
 */

public class Main {
    public static void main(String[] args) {
        GraphBuilder gb = new GraphBuilder();
        new JGraphFrame(gb);
        System.out.println(gb.toString());

        if (gb.getGraph().isCorrectlyColored()) {
            System.out.println("Halleluya !!!!!!!");
        }
        else {
            System.out.println("Pfffff... nul !");
        }
    }
}

