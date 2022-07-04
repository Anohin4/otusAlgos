import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class BinaryTree {

    private Node rootNode;
    private final Node nullNode = null;
    private int size;
    int counterForSorting;

    public BinaryTree(int firstValue) {
        this.rootNode = new Node(firstValue, 1);
        this.size = 1;
    }

    public void insert(int value) {
        rootNode.insert(value);
        size++;
    }

    public int[] returnSortedArray() {
        int[] newArray = new int[size];
        counterForSorting = 0;
        goThroughTree(newArray, rootNode);
        return newArray;
    }

    private void goThroughTree(int[] newArray, Node node) {
        if(isNull(node.getLeftChild())) {
           for (int i = node.getAmount();i > 0; i --) {
               newArray[counterForSorting] = node.getStorageValue();
               counterForSorting++;
           }
        }
        if(nonNull(node.getLeftChild())) {
            goThroughTree(newArray, node.getLeftChild());
            for (int i = node.getAmount();i > 0; i --) {
                newArray[counterForSorting] = node.getStorageValue();
                counterForSorting++;
            }
        }
        if(nonNull(node.getRightChild())) {
            goThroughTree(newArray, node.getRightChild());
        }
    }
    public boolean delete(int i) {
        Node node = findNode(rootNode, i);
        if (node)
        return false;
    }

    public Node findNodeToSwitch(Node parentNode) {

    }

    public boolean search(int i) {
        Node node = findNode(rootNode, i);
        if(isNull(node)) {
            return false;
        }
        return true;
    }
    private Node findNode(Node node, int i) {
        if (i == node.getStorageValue()) {
            return node;
        }
        if(i > node.getStorageValue()) {
            return nonNull(node.getRightChild()) ? findNode(node.getRightChild(),i) : null;
        }
        return nonNull(node.getLeftChild()) ? findNode(node.getLeftChild(),i) : null;
    }
    private Node findParentNode(Node node, int i) {
        if (i == node.getStorageValue()) {
            return node;
        }
        if(i > node.getStorageValue()) {
            return nonNull(node.getRightChild()) ? findNode(node.getRightChild(),i) : null;
        }
        return nonNull(node.getLeftChild()) ? findNode(node.getLeftChild(),i) : null;
    }

}
