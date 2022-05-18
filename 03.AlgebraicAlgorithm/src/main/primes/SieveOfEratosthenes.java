package main.primes;

public class SieveOfEratosthenes {

     int counter;

    public int countPrimeNumbers(int n) {

        int p = 2;
        counter = 1;
        int[] arrayOfAllNumbers = new int[n+1];
        if(n == 0 || n == 1) {
            return 0;
        }

        for (; p <= n; p = getNextPrimeNumber(p, arrayOfAllNumbers)) {
            if (p == 1) {
                break;
            }
            //проставляем девятки в те числа, которые нам не интересны
            for (int i = p + p; i <= n; i += p) {
                arrayOfAllNumbers[i] = 9;
            }
        }
        return counter;
    }

    public int getNextPrimeNumber(int currentP, int[] array) {
        for (int i = currentP + 1; i < array.length; i++) {
            if (array[i] == 0) {
                counter++;
                return i;
            }
        }
        return 1;
    }

}
