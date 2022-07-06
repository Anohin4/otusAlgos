import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        AvlTree binaryTree = new AvlTree(10);
        for (int i = 0; i < 2000000; i++) {
            binaryTree.insert((int) (Math.random()*65000));
        }
        long endTime = (System.currentTimeMillis() - start);
        System.out.println("time with random ints " + endTime + "ms");

        start = System.currentTimeMillis();
        AvlTree binaryTree2 = new AvlTree(0);
        for (int i = 1; i < 2000000; i++) {
            binaryTree2.insert(i);
        }
        endTime = (System.currentTimeMillis() - start);
        System.out.println("time with particular ints " + endTime + "ms");
        System.out.println(Arrays.toString(binaryTree2.returnSortedArray()));





    }

}
