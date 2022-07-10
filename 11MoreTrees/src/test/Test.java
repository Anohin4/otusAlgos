package test;

import main.BinaryTree;
import main.SplayTree;

public class Test {

    public static void main(String[] args) {
        SplayTree tree = new SplayTree(4);
        tree.insert(5);
        tree.insert(6);
        tree.insert(7);
        tree.insert(8);
        tree.insert(9);
        tree.insert(15);
        tree.search(15);
        tree.search(15);
        tree.search(15);
        tree.search(15);
        tree.search(15);
        tree.search(15);






    }


    private static void delete1000(BinaryTree tree) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            tree.delete((int) (Math.random()*65000));
        }
        long endTime = (System.currentTimeMillis() - start);
        System.out.println("time of deletion " + endTime + "ms");
    }

    private static void search1000(BinaryTree tree) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            tree.search((int) (Math.random()*65000));
        }
        long endTime = (System.currentTimeMillis() - start);
        System.out.println("time of ыуфкср " + endTime + "ms");
    }

}
