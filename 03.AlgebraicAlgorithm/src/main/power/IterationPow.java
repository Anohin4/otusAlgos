package main.power;

public class IterationPow {

    public int pow(int a, int n) {
        int result = 1;
        for (; n > 0; n--) {
            result *= a;
        }
        return result;
    }

}
