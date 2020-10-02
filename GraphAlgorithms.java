import java.util.*;

public class GraphAlgorithms {


	/**
	 * Helper class for storing weighted edges direction for getMinimumSpanningTree
	 */
	public static class WeightedEdge implements Comparable<WeightedEdge> {
		int i, j;
		float w;
		public WeightedEdge(int i, int j, float w) {
			this.i = i;
			this.j = j;
			this.w = w;
		}

		/**
		 * Compares this edge's weight to e's weight. This way you can use
		 * Collections.sort to sort a list of edges by weight
		 * @param e
		 * @return
		 */
		public int compareTo(WeightedEdge e) {
			return ((Float) w).compareTo(((Float) e.w));
		}
	}

	/**
	 * Implement Kruskal's algorithm to compute a minimum spanning tree.
	 *
	 * Note that the helper class WeightedEdge just lets you store a pair (i, j) as an
	 * edge so that you can put it in a list.
	 * @return A WeightedGraph representing the minimum spanning tree of g.
	 */
	public static AdjacencyListGraph getMinimumSpanningTree(WeightedGraph g) {
		AdjacencyListGraph graph = new AdjacencyListGraph(g.getVertexCount());

		LinkedList<WeightedEdge> edges = new LinkedList<>();
		for (int i = 0; i < graph.getVertexCount(); i ++) {
			for (int j = 0; j < graph.getVertexCount(); j ++) {
				WeightedEdge edge = new WeightedEdge(i, j, g.getEdgeWeight(i, j));
				edges.add(edge);
			}
		}
		Collections.sort(edges); // Sort in non-decreasing order by weights.
		DisjointSet set= new DisjointSet(g.getVertexCount());
		while (!edges.isEmpty()) {
			WeightedEdge edge = edges.removeFirst(); // Remove and store edge with minimum weight.
			if (set.differ(edge.j, edge.i)) {
				graph.addEdge(edge.i, edge.j);
				if (graph.getVertexCount() + 1 == g.getVertexCount()) {
					break;
				}
				set.union(edge.j, edge.i);
			}
		}

		return graph; 
	}


	/**
	 * @param tree A tree given as a Graph object.
	 * @param s 
	 * @param t
	 * @return A path in the graph g from the start vertex s to t as a sequence of vertices
	 */
	public static List<Integer> getPath(Graph tree, int s, int t) {
		List<Integer> list = new ArrayList<>();
		/*
		 * In this case we use Dijkstra algorithm to find the shortest
		 * path between s and t. Shortest path means the minimum
		 * number of vertices (not weights). As we know, Dijkstra 
		 * searches for all vertices, but we need to find
		 * path only between two vertices, so let's use auxiliary
		 * object Vert which stores the previous Vert, so at the end
		 * we can easily find the path from t iterating over all previous
		 * Vert object until s encountered.
		 */
		Vert start = new Vert(s);
		Vert targ = new Vert(t);
		Map<Vert, Boolean> visited = new HashMap<>(); // Visited vertices.
		Map<Vert, Integer> dists = new HashMap<>(); // Minimum distances (number of vertices from s).
		Map<Integer, Vert> verts = new HashMap<>(); // Stores vertex and corresponding Vert object to easy 
		// retrieve Vert object (since getAdjacentVertices method returns vertices[integers], we need
		// to convert them into Vert object).
		
		verts.put(s, start);
		verts.put(t, targ);
		visited.put(targ, false);
		dists.put(targ, Integer.MAX_VALUE);
		Queue<Vert> q = new LinkedList<>();
		for (int i = 0; i < tree.getVertexCount(); i ++) {		
			if (i != s && i != t) { // Iterate over all vertices except s and t.
				Vert vert = new Vert(i);
				visited.put(vert, false); // Assume that all not visited yet.
				dists.put(vert, Integer.MAX_VALUE); // And all have maximum distance.
				verts.put(i, vert);
			}
		}
		visited.put(start, true); // s has already visited.
		dists.put(start, 0); // s has 0 vertices between s.
		q.add(start);

		while (!q.isEmpty()) {
			Vert v = q.poll();
			visited.put(v, true);
			List<Integer> neighbours = tree.getAdjacentVertices(v.data);
			for (int n: neighbours) {
				int d = dists.get(v) + dist(s, n, tree); // Calculate new distance between s and neighbour.
				Vert vertN = verts.get(n); // Get Vert object from neighbour.
				if (d < dists.get(vertN)) { // If new distance is less than old one.
					dists.put(vertN, d); // Update old distance.
					vertN.prev = v; // Update previous Vert.
				}
				if (!visited.get(vertN)) {
					q.add(vertN);
				}
			}
		}

		for (Vert next = targ; next != null; next = next.prev) { // Iterate over previous Vert object
			// starting from t.
			
			list.add(next.data);
		}
		return list;
	}

	/*
	 * This method finds the number of vertices between
	 * v1 and v2 using Depth First Search algorithm.
	 */
	private static int dist (int v1, int v2, Graph tree) {
		int res = 0;
		Map<Integer, Boolean> visited = new HashMap<>();

		for (int i = 0; i < tree.getVertexCount(); i ++) {
			visited.put(i, false);
		}
		ArrayDeque<Integer> stack = new ArrayDeque<>();

		stack.push(v1);
		while (!stack.isEmpty()) {
			int v = stack.pop();
			res ++; // Just increment number of vertices.
			if (v == v2) {
				break;
			}
			if (!visited.get(v)) {
				visited.put(v, true);
				for (int n: tree.getAdjacentVertices(v)) {
					stack.push(n);
				}
			}
		}
		return res;
	}

	static class Vert {
		int data;
		Vert prev;
		Vert(int data) {
			this.data = data;
		}
	} // inner class

} // outer class
