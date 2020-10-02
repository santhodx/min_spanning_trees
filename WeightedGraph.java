/**
 * The weighted graph interface.
 */
public interface WeightedGraph extends Graph {

    /**
     * Sets the edge weight for edge ij. 
     */
    public void setEdgeWeight(int i, int j, float weight);

    /**
     * @return The edge weight of edge ij, or Float.POSITIVE_INFINITY if no edge exists.
     */
    public float getEdgeWeight(int i, int j);

}
