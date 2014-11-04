import Model.Graph;
import Model.Vertex;
import junit.framework.TestCase;

import java.util.HashMap;

public class GraphTest extends TestCase {

    private Graph graph;

    public GraphTest(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();

        HashMap<Integer, Vertex> nbOf1 = new HashMap<Integer, Vertex>();
        HashMap<Integer, Vertex> nbOf2 = new HashMap<Integer, Vertex>();
        nbOf1.put(2, new Vertex(2));
        nbOf2.put(1, new Vertex(1));

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
        assertEquals(2, this.graph.getVertices().size());
    }

    public void testSetVertices() {
        HashMap<Integer, Vertex> vertices = new HashMap<Integer, Vertex>();
        vertices.put(3, new Vertex(3));
        this.graph.setVertices(vertices);

        assertNotNull(this.graph.getVertices());
        assertTrue(this.graph.getVertices().containsKey(3));
        assertEquals(1, this.graph.getVertices().size());
        assertFalse(this.graph.getVertices() == vertices);
    }

    public void testAddVertex() throws Exception {
        assertFalse(graph.addVertex(new Vertex(1, null)));
        assertTrue(graph.addVertex(new Vertex(3, null)));
        //assertNotNull(graph.);
    }

}