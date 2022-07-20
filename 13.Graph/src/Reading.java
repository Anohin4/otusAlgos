import java.io.BufferedReader;
import java.io.FileReader;

public class Reading {

    public static int[][] readGraphFromFile(String fileName) {
        try (FileReader fr = new FileReader(fileName);
                BufferedReader br = new BufferedReader(fr)) {
            String[] graphDefinition = br.readLine().trim().split(" ");
            int vertexes = Integer.parseInt(graphDefinition[0]);
            int edges = Integer.parseInt(graphDefinition[1]);
            int[][] result = new int[edges][2];
            while (edges > 0) {
                String[] s = br.readLine().trim().split(" ");
                result[edges - 1][0] = Integer.parseInt(s[0]);
                result[edges - 1][1] = Integer.parseInt(s[1]);
                edges--;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
