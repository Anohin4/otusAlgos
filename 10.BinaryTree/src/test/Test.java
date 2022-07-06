package test;

import main.AvlTree;
import main.BinaryTree;

public class Test {

    public static void main(String[] args) {
        binaryTest();
        avlTest();



    }

    private static void avlTest()
           {
        System.out.println("---------AVL TEST---------");
        long start = System.currentTimeMillis();
        AvlTree binaryTree = new AvlTree(10);
        for (int i = 0; i < 10000; i++) {
            binaryTree.insert((int) (Math.random()*10000));
        }
        long endTime = (System.currentTimeMillis() - start);
        System.out.println("time adding random ints " + endTime + "ms");
        search1000(binaryTree);
        delete1000(binaryTree);

        start = System.currentTimeMillis();
        AvlTree binaryTree2 = new AvlTree(0);
        for (int i = 1; i < 10000; i++) {
            binaryTree2.insert(i);
        }
        endTime = (System.currentTimeMillis() - start);
        System.out.println("time adding successive ints " + endTime + "ms");
        search1000(binaryTree2);
        delete1000(binaryTree2);


    }
    private static void binaryTest() {
        System.out.println("---------BINARY TEST---------");
        long start = System.currentTimeMillis();
        BinaryTree binaryTree = new BinaryTree(10);
        for (int i = 0; i < 10000; i++) {
            binaryTree.insert((int) (Math.random()*10000));
        }
        long endTime = (System.currentTimeMillis() - start);
        System.out.println("time with random ints " + endTime + "ms");

        search1000(binaryTree);
        delete1000(binaryTree);
        start = System.currentTimeMillis();
        BinaryTree binaryTree2 = new BinaryTree(0);
        for (int i = 1; i < 10000; i++) {
            binaryTree2.insert(i);
        }
        endTime = (System.currentTimeMillis() - start);
        System.out.println("time with successive ints " + endTime + "ms");
        search1000(binaryTree2);
        delete1000(binaryTree2);

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
