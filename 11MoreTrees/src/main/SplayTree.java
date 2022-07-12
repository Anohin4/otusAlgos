package main;

import static java.util.Objects.nonNull;

public class SplayTree extends BinaryTree {

    public SplayTree(int firstValue) {
        super(firstValue);
    }

    @Override
    public boolean search(int valueToFind) {
        Node node = findNode(rootNode, valueToFind);
        if(nonNull(node)) {
            return true;
        }
        return false;
    }

    @Override
    protected Node findNode(Node nodeToStartSearch, int valueToFind) {
        if (valueToFind == nodeToStartSearch.getStorageValue()) {
            //мы в корневой ноде, поднимать некуда, просто возвращаем
            return nodeToStartSearch;
        }
        if (valueToFind > nodeToStartSearch.getStorageValue() ) {
            if(nonNull(nodeToStartSearch.getRightChild()) && nodeToStartSearch.getRightChild().getStorageValue() == valueToFind) {
                //если нужная нода - правый ребенок, делаем ротацию в результате которой нужное значение будет в текущей ноде и возвращем
                //в результате нужное значение поднимается на один
                nodeToStartSearch.leftRotation();
                return nodeToStartSearch;
            }
            if(nonNull(nodeToStartSearch.getRightChild())) {
                //значение где-то глубже, копаем дальше
                return findNode(nodeToStartSearch.getRightChild(), valueToFind);
            }
            //значения нет в дереве
        } else {
            if(nonNull(nodeToStartSearch.getLeftChild()) && nodeToStartSearch.getLeftChild().getStorageValue() == valueToFind) {
                nodeToStartSearch.rightRotation();
                return nodeToStartSearch;
            }
            if(nonNull(nodeToStartSearch.getLeftChild())) {
                return findNode(nodeToStartSearch.getLeftChild(), valueToFind);
            }
        }
        return null;
    }
}
