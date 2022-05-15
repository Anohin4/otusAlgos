package main.fibonachi;

public class FibonacciWithGoldenRatio {

    public static void main(String[] args) {
        System.out.println(getFibonacciNumber(10));
    }
    public static long getFibonacciNumber(int n) {
        return (long) getGoldenRatio(n);
    }

    private static double getGoldenRatio(int n) {
        double f = (Math.sqrt(5) + 1)/2;
        return Math.pow(f, n) / Math.sqrt(5) + 0.5;
    }

}
