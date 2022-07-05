import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree(10);
        binaryTree.insert(1);
        binaryTree.insert(14);
        binaryTree.insert(13);
        binaryTree.insert(12);
        binaryTree.insert(11);
        System.out.println(Arrays.toString(binaryTree.returnSortedArray()));
        binaryTree.delete(1);
        System.out.println(Arrays.toString(binaryTree.returnSortedArray()));
        binaryTree.delete(10);
        System.out.println(Arrays.toString(binaryTree.returnSortedArray()));
//        binaryTree.insert(21);
//        binaryTree.insert(9);
//        binaryTree.insert(3);
//        binaryTree.insert(43);
//        binaryTree.insert(4);
//        binaryTree.insert(31);




    }

}
