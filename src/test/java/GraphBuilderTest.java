import Model.GraphBuilder;
import junit.framework.TestCase;

public class GraphBuilderTest extends TestCase {

    private GraphBuilder graphBuilder;

    public void setUp() throws Exception {
        super.setUp();
        graphBuilder = new GraphBuilder(this.getClass().getResource("/sample.graph").toURI().toURL().getPath());
    }

    public void tearDown() throws Exception {
        super.tearDown();
        this.graphBuilder = null;
    }

    public void testGetGraph() throws Exception {
        assertNotNull(this.graphBuilder.getGraph());
    }
}