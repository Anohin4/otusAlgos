package main.power;

public class PowerWithMultiplication {

    public double power(double a, int n) {
        double p = 1;
        double result = a;
        for (; p <= n / 2; p *= 2) {
            result *= result;
        }
        for (double i = p - n; i > 0; i--) {
            result *= a;
        }
        return result;
    }

}
