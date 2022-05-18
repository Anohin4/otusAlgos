package main.power;

public class PowerAdvanced {
    public double power(double a, double n) {
        double result = 1;
        double temp = a;
        if (n == 0) {
            return 1;
        }
        for (n /= 2; n >= 1; n /= 2) {
            temp *= temp;
            if (n % 2 == 1) {
                result *= temp;
            }
        }
        result *= temp;
        return result;
    }
}
