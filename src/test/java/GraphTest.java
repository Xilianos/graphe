import Model.Graph;
import Model.Vertex;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.HashSet;

public class GraphTest extends TestCase {

    private Graph graph;

    public GraphTest(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();

        HashSet<Integer> nbOf1 = new HashSet<Integer>();
        HashSet<Integer> nbOf2 = new HashSet<Integer>();
        nbOf1.add(2);
        nbOf1.add(3);
        nbOf2.add(1);
        nbOf2.add(3);

        this.graph = new Graph();
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
        assertTrue(this.graph.getVertices().containsKey(3));
        assertEquals(3, this.graph.getVertices().size());
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
        HashSet<Integer> nbOf3 = new HashSet<Integer>();

        assertFalse(this.graph.addVertex(new Vertex(1, null)));
        assertTrue(this.graph.addVertex(new Vertex(4, null)));

        nbOf3.add(1);
        nbOf3.add(2);
        this.graph.addVertex(new Vertex(3, nbOf3));
        // To be continue...
    }

}