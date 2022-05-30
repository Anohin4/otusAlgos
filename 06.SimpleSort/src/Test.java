import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        int[] array = new int[] {1, 99,3, 4, 5,18,  6, 7, 0};
        ShellSort sort = new ShellSort();
        sort.sort(array);
        System.out.println(Arrays.toString(array));
    }

}
