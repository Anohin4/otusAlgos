package main;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
/*
Простое дерево, без оптимизаций
 */
public class BinaryTree {

    protected Node rootNode;
    protected int size;
    protected int counterForSorting;

    public BinaryTree(int firstValue) {
        this.rootNode = new Node(firstValue);
        this.size = 1;
    }

    public void insert(int value) {
        addNode(getRootNode(), value);
        size++;
    }


    protected void addNode(Node node, int value) {
        if (value > node.getStorageValue()) {
            if(isNull(node.getRightChild())) {
                node.setRightChild(new Node(value));
            } else {
                addNode(node.getRightChild(), value);
            }
        } else if(value < node.getStorageValue()) {
            if(isNull(node.getLeftChild())) {
                node.setLeftChild(new Node(value));
            } else {
                addNode(node.getLeftChild(), value);
            }
        } else {
            node.addAmount();
        }
    }

    public Node getRootNode() {
        return rootNode;
    }

    public boolean search(int i) {
        Node node = findNode(rootNode, i);
        return !isNull(node);
    }

    public int[] returnSortedArray() {
        int[] newArray = new int[size];
        counterForSorting = 0;
        collectArrayFromNodes(newArray, rootNode);
        return newArray;
    }

    protected void collectArrayFromNodes(int[] newArray, Node node) {
        if (isNull(node.getLeftChild())) {
            for (int i = node.getAmount(); i > 0; i--) {
                newArray[counterForSorting] = node.getStorageValue();
                counterForSorting++;
            }
        }
        if (nonNull(node.getLeftChild())) {
            collectArrayFromNodes(newArray, node.getLeftChild());
            for (int i = node.getAmount(); i > 0; i--) {
                newArray[counterForSorting] = node.getStorageValue();
                counterForSorting++;
            }
        }
        if (nonNull(node.getRightChild())) {
            collectArrayFromNodes(newArray, node.getRightChild());
        }
    }

    protected Node findNode(Node nodeToStartSearch, int valueToFind) {
        if (valueToFind == nodeToStartSearch.getStorageValue()) {
            return nodeToStartSearch;
        }
        if (valueToFind > nodeToStartSearch.getStorageValue()) {
            return nonNull(nodeToStartSearch.getRightChild()) ? findNode(nodeToStartSearch.getRightChild(), valueToFind)
                    : null;
        }
        return nonNull(nodeToStartSearch.getLeftChild()) ? findNode(nodeToStartSearch.getLeftChild(), valueToFind)
                : null;
    }

    public void delete(int data) {
        deleteNode(this.rootNode, data);
    }

    protected Node deleteNode(Node node, Integer data) {
        if (isNull(node)) {
            //если мы пришли сюда - нужного числа нет
            return null;
        }
        //рекурсивно итерируемся по дереву в поисках нужной ноды
        if (data > node.getStorageValue()) {
            node.setRightChild(deleteNode(node.getRightChild(), data));
        } else if (data < node.getStorageValue()) {
            node.setLeftChild(deleteNode(node.getLeftChild(), data));

        } else
            //дошли сюда - значит нашли нужное значение
            //случай без детей
            if (isNull(node.getLeftChild()) && isNull(node.getRightChild())) {
                size--;
                //просто удаляем
                return null;

                //есть оба ребенка
            } else if (nonNull(node.getLeftChild()) && nonNull(node.getRightChild())) {
                //подставляем значение и удаляем ноду
                Node nodeToSwitch = minValue(node.getRightChild());
                node.copyValues(nodeToSwitch);
                return deleteNode(node.getRightChild(), node.getStorageValue());
            } else if (this.rootNode.getStorageValue() == data) {
                //случай, когда искомая нода - корень, у которого только один ребенок
                this.rootNode =
                        nonNull(this.rootNode.getLeftChild()) ? rootNode.getLeftChild() : rootNode.getRightChild();
                size--;
                return null;
            } else if (nonNull(node.getLeftChild())) {
                size--;
                return node.getLeftChild();
            } else {
                size--;
                return node.getRightChild();
            }
        //если мы здесь - это промежуточная нода, над которой ничего не делают, просто возвращаем ее
        return node;
    }

    private Node minValue(Node node) {

        if (node.getLeftChild() != null) {
            return minValue(node.getLeftChild());
        }
        return node;
    }

}
