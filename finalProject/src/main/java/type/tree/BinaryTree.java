package type.tree;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import index.RowEntity;

/*
Простое дерево, без оптимизаций
 */
public class BinaryTree<T extends RowEntity> {

    protected Node<T> rootNode;

    public int getSize() {
        return size;
    }

    protected int size;
    protected int counterForSorting;

    public BinaryTree(T firstValue) {
        this.rootNode = new Node<>(firstValue);
        this.size = 1;
    }
    public BinaryTree() {
        this.rootNode = null;
        this.size = 0;
    }

    public void insert(T value) {
        if(size == 0) {
            rootNode = new Node<>(value);
            size = 1;
        } else {
            addNode(getRootNode(), value);
            size++;
        }
    }


    protected void addNode(Node<T> node, T valueToCompare) {
        T storageValue = node.getStorageValue();
        if (valueToCompare.compareTo(storageValue) > 0) {
            if(isNull(node.getRightChild())) {
                node.setRightChild(new Node<>(valueToCompare));
            } else {
                addNode(node.getRightChild(), valueToCompare);
            }
        } else if(valueToCompare.compareTo(storageValue) < 0) {
            if(isNull(node.getLeftChild())) {
                node.setLeftChild(new Node<>(valueToCompare));
            } else {
                addNode(node.getLeftChild(), valueToCompare);
            }
        } else {
            node.addAmount();
        }
    }

    public Node<T> getRootNode() {
        return rootNode;
    }

    public boolean search(T i) {
        Node<T> node = findNode(rootNode, i);
        return !isNull(node);
    }

    protected void goThroughTree(T[] newArray, Node<T> node) {
        if (isNull(node.getLeftChild())) {
            for (int i = node.getAmount(); i > 0; i--) {
                newArray[counterForSorting] = node.getStorageValue();
                counterForSorting++;
            }
        }
        if (nonNull(node.getLeftChild())) {
            goThroughTree(newArray, node.getLeftChild());
            for (int i = node.getAmount(); i > 0; i--) {
                newArray[counterForSorting] = node.getStorageValue();
                counterForSorting++;
            }
        }
        if (nonNull(node.getRightChild())) {
            goThroughTree(newArray, node.getRightChild());
        }
    }

    protected Node<T> findNode(Node<T> nodeToStartSearch, T valueToFind) {
        if (valueToFind.compareTo(nodeToStartSearch.getStorageValue()) == 0) {
            return nodeToStartSearch;
        }
        if (valueToFind.compareTo(nodeToStartSearch.getStorageValue()) > 0) {
            return nonNull(nodeToStartSearch.getRightChild()) ? findNode(nodeToStartSearch.getRightChild(), valueToFind)
                    : null;
        }
        return nonNull(nodeToStartSearch.getLeftChild()) ? findNode(nodeToStartSearch.getLeftChild(), valueToFind)
                : null;
    }

    public void clear() {
        rootNode.setLeftChild(null);
        rootNode.setRightChild(null);
        rootNode.updateHeight();
        size = 1;
    }
    public void delete(T data) {
        deleteNode(this.rootNode, data);
    }

    protected Node<T> deleteNode(Node<T> node, T data) {
        if (isNull(node)) {
            //если мы пришли сюда - нужного числа нет
            return null;
        }
        //рекурсивно итерируемся по дереву в поисках нужной ноды
        if (data.compareTo(node.getStorageValue()) > 0) {
            node.setRightChild(deleteNode(node.getRightChild(), data));
        } else if (data.compareTo(node.getStorageValue()) < 0) {
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
                Node<T> nodeToSwitch = minValue(node.getRightChild());
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

    private Node<T> minValue(Node<T> node) {

        if (node.getLeftChild() != null) {
            return minValue(node.getLeftChild());
        }
        return node;
    }

}
