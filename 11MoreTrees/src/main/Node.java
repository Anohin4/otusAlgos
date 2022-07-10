package main;

import static java.util.Objects.nonNull;

public class Node {

    private int storageValue;
    private int height;

    private Node rightChild;

    private Node leftChild;
    private int amount;

    public Node(int storageValue) {
        this.storageValue = storageValue;
        this.amount = 1;
        this.height = 1;
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
    public Node getCopy() {
        Node node = new Node(storageValue);
        node.setLeftChild(leftChild);
        node.setRightChild(rightChild);
        node.setHeight(height);
        node.setAmount(amount);
        return node;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getStorageValue() {
        return storageValue;
    }

    public void leftRotation() {
        Node childToMoveLeft = rightChild.getLeftChild();

        int amountCopy = getAmount();
        int storageCopy = getStorageValue();

        setAmount(rightChild.getAmount());
        setStorageValue(rightChild.getStorageValue());
        setRightChild(rightChild.getRightChild());
        if(nonNull(leftChild)) {
            leftChild.setLeftChild(leftChild.getCopy());
            leftChild.setAmount(amountCopy);
            leftChild.setStorageValue(storageCopy);
        } else {
            leftChild = new Node(storageCopy);
            leftChild.setAmount(amountCopy);
        }

        leftChild.setRightChild(childToMoveLeft);

    }

    public void rightRotation() {
        //копия ноды, которая теперь новый рут
        Node childToMoveRight = leftChild.getRightChild();
        //
        int amountCopy = getAmount();
        int storageCopy = getStorageValue();
                //1
        //делаем перевешивающую ноду текущим корнем
        setAmount(leftChild.getAmount());
        setStorageValue(leftChild.getStorageValue());
        //сдвигаем левый дальний узел вверх
        setLeftChild(leftChild.getLeftChild());
        //сдвигаем то, что раньше было корневой нодой вправо
        if(nonNull(rightChild)) {
            rightChild.setRightChild(rightChild.getCopy());
            rightChild.setAmount(amountCopy);
            rightChild.setStorageValue(storageCopy);
        } else {
            rightChild = new Node(storageCopy);
            rightChild.setAmount(amountCopy);
        }
        rightChild.setLeftChild(childToMoveRight);

    }

}
