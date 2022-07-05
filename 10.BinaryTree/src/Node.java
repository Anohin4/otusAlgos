import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class Node {

    private int storageValue;
    private Node rightChild;

    public int getStorageValue() {
        return storageValue;
    }

    private Node leftChild;
    private int amount;
    private int height;

    public Node(int storageValue, int height) {
        this.storageValue = storageValue;
        this.amount = 1;
        this.height = height;
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

    public void setHeight(int height) {
        this.height = height;
    }

    public Node getLeftChild() {
        return leftChild;
    }


    public Node(int storageValue) {
        this.storageValue = storageValue;
    }

    public int getAmount() {
        return amount;
    }

    public void insert(int i) {
        if (i > storageValue) {
            if (nonNull(rightChild)) {
                rightChild.insert(i);
            } else {
                rightChild = new Node(i, height + 1);
            }
        }
        if (i < storageValue) {
            if (nonNull(leftChild)) {
                leftChild.insert(i);
            } else {
                leftChild = new Node(i, height + 1);
            }
        }
        if (i == storageValue) {
            amount++;
        }
    }
    public void copyValues(Node node) {
        setAmount(node.getAmount());
        setHeight(node.getHeight());
        setStorageValue(node.getStorageValue());
    }

    public Node getCopy() {
        Node node = new Node(getStorageValue());
        node.setAmount(getAmount());
        node.setHeight(getHeight());
        node.setLeftChild(getLeftChild());
        node.setRightChild(getRightChild());
        node.setStorageValue(getStorageValue());
        return node;
    }

    public int getHeight() {
        return height;
    }

    private Node findNode(int i) {
        if (i == storageValue) {
            return this;
        }
        if(i > storageValue) {
            return nonNull(rightChild) ? rightChild.findNode(i) : null;
        }
        return nonNull(leftChild) ? leftChild.findNode(i) : null;
    }
}
