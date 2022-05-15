package main.fibonachi;

public class RecursiveFibonacci {

    public static void main(String[] args) {
        System.out.println(getFibonacciNumber(10));
    }
    public static long getFibonacciNumber(int n) {
        if(n == 0) {
            return 0;
        }
        if(n == 1 || n == 2) {
            return 1;
        }
        return getFibonacciNumber(n - 1) + getFibonacciNumber( n - 2);
    }

}
