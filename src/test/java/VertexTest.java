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
        this.vertex = new Vertex(1);
        neighbors.add(2);
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
        assertTrue(this.vertex.getNeighbors().contains(2));
        assertEquals(1, this.vertex.getNeighbors().size());
    }

    public void testSetNeighbors() throws Exception {
        HashSet<Integer> neighbors = new HashSet<Integer>();
        neighbors.add(3);
        this.vertex.setNeighbors(neighbors);

        assertNotNull(this.vertex.getNeighbors());
        assertTrue(this.vertex.getNeighbors().contains(3));
        assertEquals(1, this.vertex.getNeighbors().size());
        assertFalse(this.vertex.getNeighbors() == neighbors);
    }

    public void testRegisterNeighbor() throws Exception {
        assertTrue(this.vertex.registerNeighbor(3));
        assertFalse(this.vertex.registerNeighbor(2));
    }
}