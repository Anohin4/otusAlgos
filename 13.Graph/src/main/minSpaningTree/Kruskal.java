package main.minSpaningTree;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import main.model.arrays.arrays.SpaceArray;
import main.model.arrays.graphs.Edge;
import main.model.arrays.graphs.GraphWithEdges;

public class Kruskal {
    private static int counter = 0;

    public static Edge[] getMinSpanningTree (int[][] matrix){
        GraphWithEdges graph = new GraphWithEdges(matrix);
        SpaceArray<Edge> edgeArray = graph.getEdgeArray();
        Edge[] edges = sortEdgeArrayByWeight(edgeArray);
        Edge[] result = new Edge[edgeArray.size()];
        boolean[] alreadyInUseVertexes = new boolean[graph.getSize()];
        for(int i = 0; i <edges.length;  i++) {
            Edge edge = edges[i];
            if(!(alreadyInUseVertexes[edge.getVertexFrom()] && alreadyInUseVertexes[edge.getVertexDestination()])) {

                alreadyInUseVertexes[edge.getVertexFrom()] = true;
                alreadyInUseVertexes[edge.getVertexDestination()] = true;
                result[counter++] = edge;
            }
        }
        return result;
    }

    //сортировка вставкой в новый массив
    private static Edge[] sortEdgeArrayByWeight(SpaceArray<Edge> edgeArray) {
        Edge[] result = new Edge[edgeArray.size()];
        for(int i = 0; i < result.length; i++) {
            Edge edge = edgeArray.get(i);
            int j = 0;
            while (true) {
                if(nonNull(result[j]) && result[j].getWeight() >  edge.getWeight()) {
                    System.arraycopy(result, j,result,j + 1, result.length - 1 - j);
                    result[j] = edge;
                    break;
                } else if(isNull(result[j])) {
                    result[j] = edge;
                    break;
                } else {
                    j++;
                }
            }
        }
        return result;
    }
}
