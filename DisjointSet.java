/**
 * A disjoint set data-structure implementing the UnionFind algorithm. 
 * 
 * NOTES: You may implement path compression (though you should not 
 * do it recursive due to StackOverflow exceptions). You _should_ implement
 * union with rank.
 * 
 * @author Devdutt Santhosh
 */

public class DisjointSet {

    int[] parent;
    int[] rank;

    /**
     * Initializes a DisjointSet object with n initial disjoint sets. 
     * @param n The number of initial disjoint sets. 
     */
    public DisjointSet(int n) {
    	parent = new int[n];
    	rank = new int[n];
    	for (int i = 0; i < n; i ++) {
    		parent[i] = i;
    		rank[i] = 1;
    	}
    }

    /**
     * @return The representative index for the set containing i. 
     */
    public int find(int i) {
        if (i == parent[i]) {
    		return i;
    	}
        return parent[i] = find(parent[i]); 
    }

    /**
     * Unions the sets containing i and j. 
     * @param i An item in one of the sets. 
     * @param j An item in one of the sets. 
     */
    public void union(int i, int j) {
    	i = find(i);
    	j = find(j);
    	if (j == i) {
    		return;
    	}
    	if (rank[i] > rank[j]) {
    		int tmp = i;
    		i = j;
    		j = tmp;
    	}
    	parent[i] = j;
    	if (rank[i] == rank[j]) {
    		rank[j] ++;
    	}
    }

    /**
     * @param i A member of a set. 
     * @param j A member of a set. 
     * @return true if i and j are in different sets, false otherwise. 
     */
    public boolean differ(int i, int j) {
        return find(i) != find(j);
    }
}