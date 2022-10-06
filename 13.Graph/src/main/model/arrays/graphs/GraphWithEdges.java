package main.model.arrays.graphs;

import main.model.arrays.arrays.SpaceArray;

public class GraphWithEdges extends AbstractGraph{

    private SpaceArray<Edge> edgeArray;
    private int maxVertexNumber;

    public GraphWithEdges(int[][] matrix) {
        edgeArray = new SpaceArray<>();
        generateGraphFromEdgeArray(matrix);
    }


    public void addEdge(int[] edge) {
        int weight = 0;
        if(edge.length == 3) {
            weight = edge[2];
        }
        if (edge[0] > maxVertexNumber) {
            maxVertexNumber = edge[0];
        }
        if (edge[1] > maxVertexNumber) {
            maxVertexNumber = edge[1];
        }
        edgeArray.add(new Edge(edge[0], edge[1], weight));

    }
    public boolean isAdjacent(int vertexNumber, int vertexToCheck) {
        for(int i = 0; i < edgeArray.size(); i++) {
            Edge edge = edgeArray.get(i);
            if(edge.getVertexFrom() == vertexNumber) {
                if(edge.getVertexDestination() == vertexToCheck) {
                    return true;
                }
            }
            if(edge.getVertexDestination() == vertexNumber) {
                if(edge.getVertexFrom() == vertexToCheck) {
                    return true;
                }
            }
        }
        return false;
    }

    public int[] getAdjacentVertexes(int vertexNumber) {
        SpaceArray<Integer> array = new SpaceArray<>();
        for(int i = 0; i < edgeArray.size(); i++) {
            Edge edge = edgeArray.get(i);
            if (edge.getVertexFrom() == vertexNumber) {
                array.add(edge.getVertexDestination());
            }
            if (edge.getVertexDestination() == vertexNumber) {
                array.add(edge.getVertexFrom());
            }
        }
        int[] result = new int[array.size()];
        for(int i = 0; i < array.size(); i++) {
            result[i] = array.get(i);
        }
        return result;
    }

    public int getDegree(int vertexNumber) {
        int result = 0;
        for(int i = 0; i < edgeArray.size(); i++) {
            if (edgeArray.get(i).getVertexFrom() == vertexNumber) {
                result++;
            }

        }
        return result;
    }
    public int getSize() {
        return maxVertexNumber + 1;
    }

    public SpaceArray<Edge> getEdgeArray() {
        return edgeArray;
    }
}
