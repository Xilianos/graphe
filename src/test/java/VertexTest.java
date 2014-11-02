import Model.Vertex;
import junit.framework.TestCase;

import java.util.HashSet;

public class VertexTest extends TestCase {

    private Vertex vertex;

    public VertexTest(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
        HashSet<Integer> neighbors = new HashSet<Integer>();
        neighbors.add(2);
        neighbors.add(3);
        this.vertex = new Vertex(1, neighbors);
    }

    public void tearDown() throws Exception {
        super.tearDown();
        vertex = null;
    }

    public void testGetNumber() throws Exception {
        assertEquals(1, vertex.getNumber());
    }

    public void testSetNumber() throws Exception {
        vertex.setNumber(4);
        assertEquals(4, vertex.getNumber());
    }

    public void testGetColor() throws Exception {
        assertEquals(Vertex.Color.NONE, vertex.getColor());
    }

    public void testSetColor() throws Exception {
        vertex.setColor(Vertex.Color.BLUE);
        assertEquals(Vertex.Color.BLUE, vertex.getColor());
    }

    public void testGetNeighbors() throws Exception {
        assertNotNull(vertex.getNeighbors());
        assertTrue(vertex.getNeighbors().contains(2));
        assertTrue(vertex.getNeighbors().contains(3));
        assertEquals(2, vertex.getNeighbors().size());
    }

    public void testSetNeighbors() throws Exception {
        HashSet<Integer> neighbors = new HashSet<Integer>();
        neighbors.add(4);
        neighbors.add(5);
        neighbors.add(6);
        vertex.setNeighbors(neighbors);

        assertNotNull(vertex.getNeighbors());
        assertTrue(vertex.getNeighbors().contains(4));
        assertTrue(vertex.getNeighbors().contains(5));
        assertTrue(vertex.getNeighbors().contains(6));
        assertEquals(3, vertex.getNeighbors().size());
        assertFalse(vertex.getNeighbors() == neighbors);
    }
}