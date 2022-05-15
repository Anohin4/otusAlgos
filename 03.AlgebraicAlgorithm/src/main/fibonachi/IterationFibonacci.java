package main.fibonachi;

public class IterationFibonacci {

    public static void main(String[] args) {
        System.out.println(getFibonacciNumber(7));
    }
    public static long getFibonacciNumber (int n) {
        long f1 = 1;
        long f2 = 1;
        long result = 0;
        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        for(n -= 2; n>0; n--) {
            result = f2 + f1;
            f1 = f2;
            f2 = result;
        }
        return result;

    }

}
