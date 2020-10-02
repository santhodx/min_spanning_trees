
import java.util.*;

/**
 *
 * This class is a weight-matrix implementation of a weighted, undirected graph. 
 */

public class WeightedMatrixGraph implements WeightedGraph {
    
	private float [][] weightMatrix;

    /**
    * Initializes a weighted graph with n vertices and no edges. This should set
    * all of the values in weightMatrix to Float.POSITIVE_INFINITY
    */
    public WeightedMatrixGraph(int n) {
        // TODO
    	weightMatrix = new float[n][n];
    	for (int i = 0; i < n; i ++) {
    		Arrays.fill(weightMatrix[i], Float.POSITIVE_INFINITY);
    	}
    }

	@Override
	public boolean hasEdge(int i, int j) {
		return weightMatrix[i][j] != Float.POSITIVE_INFINITY;
	}

	@Override
	public List<Integer> getAdjacentVertices(int i) {
		List<Integer> list = new ArrayList<>();
		for (int j = 0; j < weightMatrix.length; j ++) {
			if (hasEdge(i, j)) {
				list.add(j);
			}
		}
		return list;
	}

	@Override
	public int getDegreeOf(int i) {
		int degree = 0;
		for (int j = 0; j < weightMatrix.length; j ++) {
			if (hasEdge(i, j)) {
				degree ++;
			}
		}
		return degree;
	}

	@Override
	public int getTotalDegree() {
		int total = 0;
		for (int j = 0; j < weightMatrix.length; j ++) {
			total += getDegreeOf(j);
		}
		return total;
	}

	@Override
	public int getVertexCount() {
		return weightMatrix.length;
	}

	@Override
	public int getEdgeCount() {
		return getTotalDegree() / 2;
	}

	@Override
	public void setEdgeWeight(int i, int j, float weight) {
		weightMatrix[i][j] = weight;
		weightMatrix[j][i] = weight;
	}

	@Override
	public float getEdgeWeight(int i, int j) {
		return weightMatrix[i][j];
	}

}
