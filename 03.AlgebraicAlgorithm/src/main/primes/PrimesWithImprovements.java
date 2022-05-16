package main.primes;

public class PrimesWithImprovements {

    public static void main(String[] args) {
        System.out.println(countPrimeNumbers(10000));
    }
    private static long[] primesArray;
    private static int counter;
    public static long countPrimeNumbers(int n) {
        counter = 1;
        primesArray = new long[n/2];
        primesArray[0] = 2;
        for (int i = 3; i<=n; i++) {
            if(isPrime(i)) {
                primesArray[counter] = i;
                counter++;
            }
        }
        return counter;
    }

    public static boolean isPrime(int i) {
        int sqrtI = (int) Math.sqrt(i);
        for (int j = 0; primesArray[j] <= sqrtI; j++) {
            if(i%primesArray[j] == 0) {
                return false;
            }
        }
        return true;
    }

}
