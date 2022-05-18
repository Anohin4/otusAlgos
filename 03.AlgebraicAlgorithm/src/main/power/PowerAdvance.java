package main.power;

public class PowerAdvance {

    public int power(int a, int n) {
        int result = 1;
        long temp = a;
        for (n /= 2; n >= 1; n /= 2) {
            temp *= temp;
            if (n % 2 == 1) {
                result *= temp;
            }
        }
        //result *= temp;
        return result;
    }
}
