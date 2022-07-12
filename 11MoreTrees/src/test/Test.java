package test;

import main.BinaryTree;
import main.RandomTree;
import main.SplayTree;

public class Test {

    public static void main(String[] args) {
        RandomTree tree = new RandomTree(4);
        SplayTree splayTree = new SplayTree(0);
        System.out.println("------Random tree-----");
        runTests(tree);
        System.out.println("------Splay tree-----");
        runTests(splayTree);
    }


    private static void runTests(BinaryTree tree)
    {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            tree.insert((int) (Math.random()*10000));
        }
        long endTime = (System.currentTimeMillis() - start);
        System.out.println("time adding random ints " + endTime + "ms");
        search10000(tree);
        delete10000(tree);
        tree.clear();

        start = System.currentTimeMillis();
        for (int i = 1; i < 10000; i++) {
            tree.insert(i);
        }
        endTime = (System.currentTimeMillis() - start);
        System.out.println("time adding successive ints " + endTime + "ms");
        search10000(tree);
        delete10000(tree);


    }

    private static void delete10000(BinaryTree tree) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            tree.delete((int) (Math.random()*65000));
        }
        long endTime = (System.currentTimeMillis() - start);
        System.out.println("time of deletion " + endTime + "ms");
    }

    private static void search10000(BinaryTree tree) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            tree.search((int) (Math.random()*65000));
        }
        long endTime = (System.currentTimeMillis() - start);
        System.out.println("time of search " + endTime + "ms");
    }

}
