import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DisjointSetTest {
	
	private DisjointSet set = new DisjointSet(10);

	@Test
	public final void testFind() {
		assertEquals(6, set.find(6));
		assertEquals(2, set.find(2));
	}

	@Test
	public final void testUnion() {
		set.union(2, 5);
		assertEquals(5, set.find(2));
	}

	@Test
	public final void testDiffer() {
		assertTrue(set.differ(2, 5));
		set.union(2, 5);
		assertFalse(set.differ(2, 5));
	}

}
