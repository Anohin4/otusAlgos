package main.power;

public class IterationPow {

    public double pow(double a, int n) {
        double result = 1;
        for (; n > 0; n--) {
            result *= a;
        }
        return result;
    }

}
