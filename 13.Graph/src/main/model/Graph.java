package main.model;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import main.model.arrays.hashtable.MyHashTable;

public class Graph {
    String label;
    boolean isDirected;
    boolean edgesWithWeight;
    MyHashTable<Integer, Vertex> vertexMap;

    public Graph(int[][] array, boolean isDirected) {
        this.vertexMap = new MyHashTable<>();
        this.isDirected = isDirected;
        if(array[0].length == 3) {
            this.edgesWithWeight = true;
        }
        generateGraphFromEdgeArray(array);
    }

    public void addEdge(int[] edge) {
        Vertex vertexOne = vertexMap.get(edge[0]);
        Vertex vertexTwo = vertexMap.get(edge[1]);
        int weight = 0;
        if(edgesWithWeight) {
            weight = edge[2];
        }
        //проверяем наличие вершин в списке
        if(isNull(vertexOne)) {
            //такой вершины еще не было
            vertexOne = new Vertex();
            vertexMap.put(edge[0], vertexOne);
        }
        if(isNull(vertexTwo)) {
            //такой вершины еще не было
            vertexTwo = new Vertex();
            vertexMap.put(edge[1], vertexTwo);
        }
        //добавляем ребра
        if(edgesWithWeight) {
            vertexOne.addEdge(edge[1], weight);
            if (!isDirected) {
                vertexTwo.addEdge(edge[0], weight);
            }
        } else {
            vertexOne.addEdge(edge[1]);
            if (!isDirected) {
                vertexTwo.addEdge(edge[0]);
            }
        }
    }
    public boolean isAdjacent(int vertexNumber, int vertexToCheck) {
        Vertex vertex = vertexMap.get(vertexNumber);
        if(nonNull(vertex)) {
            int[] edges = vertex.getEdges();
            for( int i : edges) {
                if (i == vertexToCheck) {
                    return true;
                }
            }
        }
        return false;
    }

    public int[] getAdjacentVertexes(int vertexNumber) {
        Vertex vertex = vertexMap.get(vertexNumber);
        if(nonNull(vertex)) {
            return vertex.getEdges();
        }
        return null;
    }
    public int[][] getAdjacentyMatrix() {
        int size = vertexMap.getSize();
        int[][] result = new int[size][size];
        int i = 1;
        while (i <= size) {
            int[] array = result[i - 1];
            int[] edges = vertexMap.get(i).getEdges();
            for (int edge: edges) {
                array[edge - 1] = array[edge - 1] + 1;
            }
            i++;
        }
        return result;
    }
    public int getDegree(int vertexNumber) {
        int result = 0;
        Vertex vertex = vertexMap.get(vertexNumber);
        if(nonNull(vertex)) {
            for (int edge : vertex.getEdges()) {
                if(edge ==  vertexNumber) {
                    result += 2;
                } else {
                    result++;
                }
            }
        }
        return result;
    }
    private void generateGraphFromEdgeArray(int[][] arrayOfEdges) {
        for (int[] edge : arrayOfEdges) {
            addEdge(edge);
        }
    }

}
