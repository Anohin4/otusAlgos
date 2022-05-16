package main.primes;

public class PrimesWithIteration {

    public static void main(String[] args) {
        System.out.println(countPrimeNumbers(10000));
    }

    public static long countPrimeNumbers(int n) {
        long counter = 0;
        for (int i = 2; i<=n; i++) {
            for (int j = 2; j <= i; j++) {
                if(i == j) {
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
