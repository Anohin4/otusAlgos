package main.model.arrays.graphs;

import main.model.arrays.arrays.SpaceArray;

public class Vertex {
    private SpaceArray<Integer> connectedVertexes;
    private SpaceArray<Integer> weightOfEdges;

    public Vertex() {
        connectedVertexes = new SpaceArray<>();
        weightOfEdges = new SpaceArray<>(2);
    }
    public void addEdge(int vertexNumber) {
        connectedVertexes.add(vertexNumber);
    }

    public void addEdge(int vertexNumber, int weightOfEdge) {
        connectedVertexes.add(vertexNumber);
        weightOfEdges.add(weightOfEdge);
    }

    public int[] getAllConnectedVertexes() {
        int[] result = new int[connectedVertexes.size()];
        for ( int i = 0; i < connectedVertexes.size(); i++) {
            result[i] = connectedVertexes.get(i);
        }
        return result;
    }

    public int getWeightOfEdge(int numberOfConnectedVertexInArray) {
        return weightOfEdges.get(numberOfConnectedVertexInArray);
    }


}
