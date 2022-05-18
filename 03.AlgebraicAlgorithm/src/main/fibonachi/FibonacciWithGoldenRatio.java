package main.fibonachi;

public class FibonacciWithGoldenRatio {

    public long getFibonacciNumber(int n) {
        return (long) getGoldenRatio(n);
    }

    private double getGoldenRatio(int n) {

        double f = (Math.sqrt(5) + 1) / 2;
        return Math.pow(f, n) / Math.sqrt(5) + 0.5;
    }

}
