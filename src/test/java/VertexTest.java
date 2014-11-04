import Model.Vertex;
import junit.framework.TestCase;

import java.util.HashMap;

public class VertexTest extends TestCase {

    private Vertex vertex;

    public VertexTest(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
        HashMap<Integer, Vertex> neighbors = new HashMap<Integer, Vertex>();
        Vertex v2 = new Vertex(2);
        this.vertex = new Vertex(1);
        neighbors.put(v2.getNumber(), v2);
        this.vertex.setNeighbors(neighbors);
    }

    public void tearDown() throws Exception {
        super.tearDown();
        this.vertex = null;
    }

    public void testGetNumber() throws Exception {
        assertEquals(1, this.vertex.getNumber());
    }

    public void testGetColor() throws Exception {
        assertEquals(Vertex.Color.NONE, this.vertex.getColor());
    }

    public void testSetColor() throws Exception {
        vertex.setColor(Vertex.Color.BLUE);
        assertEquals(Vertex.Color.BLUE, this.vertex.getColor());
    }

    public void testGetNeighbors() throws Exception {
        assertNotNull(this.vertex.getNeighbors());
        assertTrue(this.vertex.getNeighbors().containsKey(2));
        assertEquals(1, this.vertex.getNeighbors().size());
    }

    public void testSetNeighbors() throws Exception {
        HashMap<Integer, Vertex> neighbors = new HashMap<Integer, Vertex>();
        Vertex v3 = new Vertex(3);
        neighbors.put(v3.getNumber(), v3);
        this.vertex.setNeighbors(neighbors);

        assertNotNull(this.vertex.getNeighbors());
        assertTrue(this.vertex.getNeighbors().containsKey(3));
        assertEquals(1, this.vertex.getNeighbors().size());
        assertFalse(this.vertex.getNeighbors() == neighbors);
    }

    public void testIsComplete() {
        assertTrue(this.vertex.isComplete());
        assertFalse(this.vertex.getNeighbors().get(2).isComplete());
    }

    public void testRegisterNeighbor() throws Exception {
        assertTrue(this.vertex.registerNeighbor(new Vertex(3)));
        assertFalse(this.vertex.registerNeighbor(new Vertex(2)));
    }
}