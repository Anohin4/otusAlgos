package main;

import static java.util.Objects.nonNull;

import java.io.BufferedReader;
import java.io.FileReader;

public class Utils {

    public static int[][] readGraphFromFile(String fileName) {
        try (FileReader fr = new FileReader(fileName);
                BufferedReader br = new BufferedReader(fr)) {
            String[] graphDefinition = br.readLine().trim().split(" ");
            int edges = Integer.parseInt(graphDefinition[1]);

            //читаем первое ребро и определяем, есть вес или нет
            String[] edge = br.readLine().trim().split(" ");
            int[][] result = new int[edges][edge.length];
            while (edges > 0) {
                result[edges - 1][0] = Integer.parseInt(edge[0]);
                result[edges - 1][1] = Integer.parseInt(edge[1]);
                if(edge.length == 3) {
                    result[edges - 1][2] = Integer.parseInt(edge[2]);
                }
                String nextEdge = br.readLine();
                if(nonNull(nextEdge)) {
                    edge = nextEdge.trim().split(" ");
                }
                edges--;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int[][] readMatrixFromEdgesList(String fileName, boolean isDirected) {
        try (FileReader fr = new FileReader(fileName);
                BufferedReader br = new BufferedReader(fr)) {
            String[] graphDefinition = br.readLine().trim().split(" ");
            int vertexes = Integer.parseInt(graphDefinition[0]);
            int edges = Integer.parseInt(graphDefinition[1]);
            int[][] result = new int[vertexes][vertexes];
            String[] edge = br.readLine().trim().split(" ");
            while (edges > 0) {
                int vertex = Integer.parseInt(edge[0]);
                int edgeToConnect = Integer.parseInt(edge[1]);
                result[vertex][edgeToConnect] = result[vertex][edgeToConnect] + 1;
                if(!isDirected) {
                    result[edgeToConnect][vertex] = result[edgeToConnect][vertex] + 1;
                }
                String nextEdge = br.readLine();
                if(nonNull(nextEdge)) {
                    edge = nextEdge.trim().split(" ");
                }
                edges--;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void printMatrix(int[][] matrix) {
        printMatrix(matrix, false);
    }
    public static void printMatrix(int[][] matrix, boolean startFromZero) {
        int length = matrix.length;
        StringBuilder sb = new StringBuilder("   ");
        for (int i = 1; i <= length; i++) {
            if(startFromZero) {
                sb.append(i - 1).append(" ");
            } else {
                sb.append(i).append(" ");
            }
        }
        sb.append("\n");
        for (int i = 0; i <= length * 2 + 2; i++) {
            sb.append("-");
        }
        sb.append("\n");

        for (int i = 0; i < length; i++) {
            if(startFromZero) {
                sb.append(i);
            } else {
                sb.append(i + 1);
            }
            sb.append("| ");
            for (int j : matrix[i]) {
                sb.append(j).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }

}
