import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class WeightedMatrixGraphTest {
	
	private WeightedMatrixGraph g = new WeightedMatrixGraph(10);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testHasEdge() {
		assertFalse(g.hasEdge(2, 6));
		g.setEdgeWeight(2, 6, 3.0f);
		assertTrue(g.hasEdge(2, 6));
	}

	@Test
	public final void testGetAdjacentVertices() {
		g.setEdgeWeight(2, 6, 3.0f);
		g.setEdgeWeight(2, 7, 3.0f);
		g.setEdgeWeight(2, 8, 3.0f);
		assertEquals(new ArrayList<>(Arrays.asList(6, 7, 8)), g.getAdjacentVertices(2));
	}

	@Test
	public final void testGetDegreeOf() {
		g.setEdgeWeight(2, 6, 3.0f);
		g.setEdgeWeight(2, 7, 3.0f);
		g.setEdgeWeight(2, 8, 3.0f);
		assertEquals(3, g.getDegreeOf(2));
	}

	@Test
	public final void testGetTotalDegree() {
		g.setEdgeWeight(2, 6, 3.0f);
		g.setEdgeWeight(2, 7, 3.0f);
		g.setEdgeWeight(2, 8, 3.0f);
		g.setEdgeWeight(4, 8, 3.0f);
		assertEquals(8, g.getTotalDegree());
	}

	@Test
	public final void testGetVertexCount() {
		g.setEdgeWeight(2, 6, 3.0f);
		g.setEdgeWeight(2, 7, 3.0f);
		g.setEdgeWeight(2, 8, 3.0f);
		g.setEdgeWeight(4, 8, 3.0f);
		assertEquals(10, g.getVertexCount());
	}

	@Test
	public final void testGetEdgeCount() {
		g.setEdgeWeight(2, 6, 3.0f);
		g.setEdgeWeight(2, 7, 3.0f);
		g.setEdgeWeight(2, 8, 3.0f);
		g.setEdgeWeight(4, 8, 3.0f);
		assertEquals(4, g.getEdgeCount());
	}

	@Test
	public final void testSetEdgeWeight() {
		g.setEdgeWeight(2, 6, 3.0f);
		assertTrue(g.hasEdge(2, 6));
		assertEquals(3.0f, g.getEdgeWeight(2, 6), 0.0);
	}

	@Test
	public final void testGetEdgeWeight() {
		g.setEdgeWeight(2, 6, 3.0f);
		assertEquals(3.0f, g.getEdgeWeight(2, 6), 0.0);
	}

}
