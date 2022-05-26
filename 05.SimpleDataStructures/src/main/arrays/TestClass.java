package main.arrays;

import main.arrays.FactorArray;
import main.queue.PriorityQueue;

public class TestClass {

    public static void main(String[] args) {
//        FactorArray<Integer> test = new FactorArray<>(2);
//        for (int i = 0; i < 8; i++) {
//            test.add(i);
//        }
//        System.out.println(test);
//
//        test.remove( 0);
//        System.out.println(test);
//        test.remove(4);
//        test.remove( 0);        test.remove( 0);
//        test.remove( 0);
//
//        System.out.println(test);
//        test.add(0,0);
//        System.out.println(test);
//        test.add(5,5);
//
//
//        System.out.println(test);

        PriorityQueue<Integer> myQueue = new PriorityQueue<>();
        for (int i = 0; i < 8; i++) {
            myQueue.enqueue(i);
        }
        System.out.println(myQueue.pool());
        System.out.println(myQueue.pool());
        for (int i = 44; i < 48; i++) {
            myQueue.enqueue(i, i + 1);
        }
        System.out.println(myQueue.pool());
        System.out.println(myQueue.pool());
        System.out.println(myQueue.pool());
    }

}
