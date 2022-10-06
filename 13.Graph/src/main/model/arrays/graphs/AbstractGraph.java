package main.model.arrays.graphs;

public abstract class AbstractGraph {
    protected boolean isDirected;
    protected boolean edgesWithWeight;
    abstract void addEdge(int[] edge);

    protected void generateGraphFromEdgeArray(int[][] arrayOfEdges) {
        for (int[] edge : arrayOfEdges) {
            addEdge(edge);
        }
    }
}
