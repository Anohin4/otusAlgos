package main.primes;

public class PrimesWithIteration {

    public int countPrimeNumbers(int n) {
        int counter = 0;
        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= i; j++) {
                if (i == j) {
                    counter++;
                }
                if (i % j == 0) {
                    break;
                }
            }
        }
        return counter;
    }

}
