package main.primes;

public class SieveOfEratosthenes {

    public static void main(String[] args) {
        System.out.println(countPrimeNumbers(10000));
    }
    static long counter;

    public static long countPrimeNumbers(int n) {
        int p = 2;
        counter = 1;
        int[] arrayOfAllNumbers = new int[n];
        for (int j = 2; j < n; j++) {
            arrayOfAllNumbers[j] = j;
        }


        for (; p<=n; p = getNextPrimeNumber(p, arrayOfAllNumbers)) {
            if (p == 0) {
                break;
            }
            for (int i = p*p; i < n; i += p) {
                arrayOfAllNumbers[i] = 0;

            }
        }
        return counter;
    }

    static public int getNextPrimeNumber(int currentP, int[] array) {
        for (int i = currentP + 1; i < array.length; i++) {
            if (array[i] != 0) {
                counter++;
                return i;
            }
        }
        return 0;
    }

}
