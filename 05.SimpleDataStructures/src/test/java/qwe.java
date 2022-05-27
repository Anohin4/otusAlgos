package test.java;

public class qwe {

    public static void main(String[] args) {
        System.out.println(8 >>> 1);

    }

    public static void test(int[] array, int j) {
        int maxIndex;
        int maxElem = Integer.MIN_VALUE;
        for (int i = 0; i <= j; i++) {
            if (array[i] > maxElem) {
                maxIndex = i;
                maxElem = array[i];
            }
        }
    }

    public static void swap(int[] array, int x, int r) {
        int temp = array[x];
        array[x] = array[r];
        array[r] = temp;
    }

}
