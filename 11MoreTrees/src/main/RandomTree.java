package main;

import static java.util.Objects.nonNull;

public class RandomTree extends BinaryTree {

    public RandomTree(int firstValue) {
        super(firstValue);
    }

    @Override
    public void insert(int i) {
        int v = (int) (Math.random() * (size + 1));
        if(v % (size + 1) == 0) {
            insertToRoot(new Node(i));
        } else {
            addNode(rootNode, i);
        }
        size++;
    }

    private void insertToRoot(Node newNode) {
        if (newNode.getStorageValue() > rootNode.getStorageValue()) {
            if (nonNull(rootNode.getRightChild()) && rootNode.getRightChild().getStorageValue() > newNode.getStorageValue()) {
                newNode.setRightChild(rootNode.getRightChild());
            } else {
                newNode.setLeftChild(rootNode.getRightChild());
            }
            rootNode.setRightChild(newNode);
            rootNode.leftRotation();
        } else {
            if (nonNull(rootNode.getLeftChild()) && rootNode.getLeftChild().getStorageValue() > newNode.getStorageValue()) {
                newNode.setRightChild(rootNode.getLeftChild());
            } else {
                newNode.setLeftChild(rootNode.getLeftChild());
            }
            rootNode.setLeftChild(newNode);
            rootNode.rightRotation();
        }
    }
}
