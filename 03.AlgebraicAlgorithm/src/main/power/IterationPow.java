package main.power;

public class IterationPow {

    public double power(double a, double n) {
        double result = 1;
        for (; n > 0; n--) {
            result *= a;
        }
        return result;
    }

}
