import static java.util.Objects.nonNull;

public class Node {

    private int storageValue;
    private Node rightChild;

    public int getStorageValue() {
        return storageValue;
    }

    private Node leftChild;
    private int amount;

    public Node(int storageValue) {
        this.storageValue = storageValue;
        this.amount = 1;
    }
    public void addAmount() {
        this.amount++;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setStorageValue(int storageValue) {
        this.storageValue = storageValue;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    public Node getLeftChild() {
        return leftChild;
    }

    public int getAmount() {
        return amount;
    }
    public void copyValues(Node node) {
        setAmount(node.getAmount());

        setStorageValue(node.getStorageValue());
    }

}
