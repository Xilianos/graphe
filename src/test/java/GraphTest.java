import model.Graph;
import model.Vertex;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.HashMap;

public class GraphTest extends TestCase {

    private Graph graph;

    public GraphTest(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();

        ArrayList<Integer> nbOf1 = new ArrayList<Integer>();
        ArrayList<Integer> nbOf2 = new ArrayList<Integer>();
        nbOf1.add(2);
        nbOf1.add(3);
        nbOf2.add(1);
        nbOf2.add(3);

        this.graph = new Graph(3);
        this.graph.addVertex(1, nbOf1);
        this.graph.addVertex(2, nbOf2);
    }

    public void tearDown() throws Exception {
        super.tearDown();
        this.graph = null;
    }

    public void testGetVertices() {
        assertNotNull(this.graph.getVertices());
        assertTrue(this.graph.getVertices().containsKey(1));
        assertTrue(this.graph.getVertices().containsKey(2));
        assertEquals(2, this.graph.getVertices().size());
    }

    public void testSetVertices() {
        HashMap<Integer, Vertex> vertices = new HashMap<Integer, Vertex>();
        vertices.put(4, new Vertex(4));
        this.graph.setVertices(vertices);

        assertNotNull(this.graph.getVertices());
        assertTrue(this.graph.getVertices().containsKey(4));
        assertEquals(1, this.graph.getVertices().size());
        assertFalse(this.graph.getVertices() == vertices);
    }

    public void testAddVertex() throws Exception {
        ArrayList<Integer> nbOf3 = new ArrayList<Integer>();

        assertFalse(this.graph.addVertex(new Vertex(1)));
        assertTrue(this.graph.addVertex(new Vertex(4)));
        assertFalse(this.graph.getVertices().containsKey(3));

        nbOf3.add(1);
        nbOf3.add(2);
        this.graph.addVertex(new Vertex(3, nbOf3));

        assertTrue(this.graph.getVertices().containsKey(3));
    }

    public void testGetNeighborsMap() {
        HashMap<Integer, Vertex> neighbors;
        ArrayList<Integer> nbOf3 = new ArrayList<Integer>();
        nbOf3.add(1);
        nbOf3.add(2);
        this.graph.addVertex(3, nbOf3);
        this.graph.addVertex(4, null);

        neighbors = this.graph.getNeighborsMap(this.graph.getVertices().get(1));

        assertEquals(2, neighbors.get(2).getNumber());
        assertEquals(3, neighbors.get(3).getNumber());

        neighbors = this.graph.getNeighborsMap(this.graph.getVertices().get(4));

        assertNotNull(neighbors);
        assertEquals(0, neighbors.size());
    }

//    public void testSetColoration() {
//        ArrayList<Integer> nbOf3 = new ArrayList<Integer>();
//        nbOf3.add(1);
//        nbOf3.add(2);
//        this.graph.addVertex(new Vertex(3, nbOf3));
//        this.graph.addVertex(4, null);
//
//        this.graph.setColoration();
//        assertTrue(this.graph.getVertices().get(1).getColor() != this.graph.getVertices().get(2).getColor());
//        assertTrue(this.graph.getVertices().get(2).getColor() != this.graph.getVertices().get(3).getColor());
//        assertTrue(this.graph.getVertices().get(3).getColor() != this.graph.getVertices().get(1).getColor());
//        assertEquals(Graph.colors[0], this.graph.getVertices().get(4).getColor());
//    }

    public void testSetColorToVertex() {
        ArrayList<Integer> nbOf3 = new ArrayList<Integer>();
        nbOf3.add(1);
        nbOf3.add(2);
        this.graph.addVertex(new Vertex(3, nbOf3));

        this.graph.setColorToVertex(1);
        assertEquals(Graph.colors[0], this.graph.getVertices().get(1).getColor());
        this.graph.setColorToVertex(2);
        assertEquals(Graph.colors[1], this.graph.getVertices().get(2).getColor());
        this.graph.setColorToVertex(3);
        assertEquals(Graph.colors[2], this.graph.getVertices().get(3).getColor());
    }

    public void testGetPossibleColor() {
        ArrayList<Integer> nbOf3 = new ArrayList<Integer>();
        nbOf3.add(1);
        nbOf3.add(2);
        this.graph.addVertex(new Vertex(3, nbOf3));
        this.graph.addVertex(4, null);

        assertEquals(Graph.colors[0], this.graph.getPossibleColor(4));

        this.graph.setColorToVertex(1);
        assertEquals(Graph.colors[1], this.graph.getPossibleColor(2));
    }

//    public void testGetCountEnabledNeighbors() {
//        ArrayList<Integer> nbOf3 = new ArrayList<Integer>();
//        nbOf3.add(1);
//        nbOf3.add(2);
//        this.graph.addVertex(new Vertex(3, nbOf3));
//
//        assertEquals(2, this.graph.getCountEnabledNeighbors(this.graph.getVertices().get(1)));
//        assertEquals(2, this.graph.getCountEnabledNeighbors(this.graph.getVertices().get(2)));
//
//        this.graph.getVertices().get(3).disconnectVertex();
//        assertEquals(1, this.graph.getCountEnabledNeighbors(this.graph.getVertices().get(1)));
//        assertEquals(1, this.graph.getCountEnabledNeighbors(this.graph.getVertices().get(2)));
//
//        this.graph.getVertices().get(2).disconnectVertex();
//        assertEquals(0, this.graph.getCountEnabledNeighbors(this.graph.getVertices().get(1)));
//    }

    public void testIsCorrectlyColored() {
        ArrayList<Integer> nbOf3 = new ArrayList<Integer>();
        nbOf3.add(1);
        nbOf3.add(2);
        this.graph.addVertex(new Vertex(3, nbOf3));

        assertFalse(this.graph.isCorrectlyColored());

        this.graph.recursiveColoration();
        assertTrue(this.graph.isCorrectlyColored());

        this.graph.getVertices().get(2).setColor(Vertex.Color.RED);
        assertFalse(this.graph.isCorrectlyColored());
    }
}