package main.power;

public class PowerWithMultiplication {


    public  long power(int a, int n) {
        int p = 1;
        long result = a;
        for (; p <= n/2; p *= 2) {
            result *= result;
        }
        for (int i = p - n; i > 0; i--) {
           result *= a;
        }
        return result;
    }

}
