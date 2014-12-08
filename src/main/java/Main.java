import controller.GraphBuilder;
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
            System.out.println("Coloration correcte");
        }
        else {
            System.out.println("Erreur dans la coloration");
        }
        System.exit(0);
    }
}

