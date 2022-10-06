package main.model.arrays.graphs;

public class Edge {
    private int vertexFrom;
    private int vertexDestination;
    private int weight;

    public int getVertexFrom() {
        return vertexFrom;
    }

    public int getVertexDestination() {
        return vertexDestination;
    }

    public int getWeight() {
        return weight;
    }

    public Edge(int vertexSource, int vertexDestination, int weight) {
        this.vertexFrom = vertexSource;
        this.vertexDestination = vertexDestination;
        this.weight = weight;
    }

}
