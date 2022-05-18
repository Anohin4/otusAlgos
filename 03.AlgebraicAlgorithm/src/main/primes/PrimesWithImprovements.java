package main.primes;

public class PrimesWithImprovements {

    private long[] primesArray;
    private int counter;

    public int countPrimeNumbers(int n) {
        if(n == 0 || n == 1) {
            return 0;
        }
        if(n == 2) {
            return 1;
        }
        counter = 1;
        primesArray = new long[n];
        primesArray[0] = 2;
        for (int i = 3; i <= n; i++) {
            if (isPrime(i)) {
                primesArray[counter] = i;
                counter++;
            }
        }
        return counter;
    }

    public boolean isPrime(int i) {
        int sqrtI = (int) Math.sqrt(i);
        for (int j = 0; primesArray[j] <= sqrtI; j++) {
            if (i % primesArray[j] == 0) {
                return false;
            }
        }
        return true;
    }

}
