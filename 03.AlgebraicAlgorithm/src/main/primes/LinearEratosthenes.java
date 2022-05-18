package main.primes;

public class LinearEratosthenes {

    public int countPrimeNumbers(int n) {
        int counter = 0;
        long[] arrayOfAllPrimes = new long[n+1];
        long[] arrayOfAllNumbers = new long[n+1];
        if(n == 0 || n == 1) {
            return 0;
        }
        if(n == 2) {
            return 1;
        }


        for (long p = 2; p <= n; p++) {
            //число не изменилось за прошлые итерации - значит простое
            if (arrayOfAllNumbers[(int)p] == 0) {
                long k =  p * p;
                //вычеркиваем квадрат числа
                if (k < n) {
                    arrayOfAllNumbers[(int) (p * p)] = 1;
                }
                arrayOfAllPrimes[counter] = p;
                counter++;
            }
            //проставляем 9 в те числа, которые точно не простые
            for (long i = 0; i < counter; i++) {
                long j = p *  arrayOfAllPrimes[(int)i];
                //защита от arrayOutOfBound
                if (j > n) {
                    continue;
                }
                arrayOfAllNumbers[(int) j] = 9;
            }
        }
        return counter;
    }

}
