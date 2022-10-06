package main.minPath;

import main.model.arrays.arrays.SpaceArray;
import main.model.arrays.graphs.Edge;
import main.model.arrays.graphs.GraphWithVertex;
import main.model.arrays.graphs.Vertex;

public class Deykstra {

    int nextVertex;
    int nextEdgeWieight;
    private GraphWithVertex graph;
    private int[][] arrayOfReferences;
    private boolean[] finishedVertexes;

    //данная реализация для ориентированного графа
    public Edge[] getMinPath(int[][] matrix, int startEdge, int edgeToFind) {

        nextVertex = startEdge;
        nextEdgeWieight = Integer.MAX_VALUE;

        graph = new GraphWithVertex(matrix, true);
        finishedVertexes = new boolean[graph.getNumberOfVertexes()];
        //массив со ссылкамми элемент
        arrayOfReferences = new int[graph.getNumberOfVertexes()][2];
        for (int i = 0; i < arrayOfReferences.length; i++) {
            arrayOfReferences[i][0] = Integer.MAX_VALUE;
            arrayOfReferences[i][1] = -1;
        }
        //ставим стартовое значе
        arrayOfReferences[startEdge][0] = 0;
        while (true) {
            processVertex();
            if(nextVertex == edgeToFind) {
                break;
            }
            nextVertex = getNextVertex();
        }
        return getResult();
    }

    private void processVertex() {
        int currentVertex = nextVertex;
        Vertex vertex = graph.getVertex(currentVertex);
        int[] allConnectedVertexes = vertex.getAllConnectedVertexes();

        for (int i = 0; i < allConnectedVertexes.length; i++) {
            int destinationVertex = allConnectedVertexes[i];
            //нас интересуют только вершины, которые мы еще не трогали
            if (finishedVertexes[destinationVertex]) {
                continue;
            }
            int weightOfEdge = vertex.getWeightOfEdge(i);
            if (arrayOfReferences[destinationVertex][0] > weightOfEdge + arrayOfReferences[currentVertex][0]) {
                arrayOfReferences[destinationVertex][0] = weightOfEdge + arrayOfReferences[currentVertex][0];
                arrayOfReferences[destinationVertex][1] = i;
            }
        }

        //помечаем пройденную вершину как пройденную
        finishedVertexes[currentVertex] = true;

    }

    private int getNextVertex() {
        int minValue = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i< arrayOfReferences.length; i ++) {
            if(!finishedVertexes[i] && arrayOfReferences[i][0] < minValue) {
                minValue = arrayOfReferences[i][0];
                index = i;
            }
        }
        return index;
    }

    private Edge[] getResult(int startEdge, int edgeToFind) {
        SpaceArray<Edge> result = new SpaceArray<>();

    }

    private Edge getEdge(int reference) {

    }


}
