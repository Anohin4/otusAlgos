package type.tree;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import index.RowEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
Простое дерево, без оптимизаций
 */
public class BinaryTree {

    protected Node rootNode;

    public int getSize() {
        return size;
    }

    protected int size;
    protected int counterForSorting;

    public BinaryTree(RowEntityForBd firstValue) {
        this.rootNode = new Node(firstValue);
        this.size = 1;
    }

    public BinaryTree() {
        this.rootNode = null;
        this.size = 0;
    }

    public void insert(RowEntity value) {
        List<RowId> rowIds = new ArrayList<>();
        rowIds.add(new RowId(value.getRowId(), value.getDeleted()));
        insert(new RowEntityForBd(value.getIndexValue(), rowIds));
    }

    public void insert(RowEntityForBd value) {
        if (size == 0) {
            rootNode = new Node(value);
            size = 1;
        } else {
            boolean result = addNode(getRootNode(), value);
            if(result) {
                size++;
            }
        }
    }


    protected boolean addNode(Node node, RowEntityForBd valueToCompare) {
        RowEntityForBd storageValue = node.getStorageValue();
        if (valueToCompare.compareTo(storageValue) > 0) {
            if (isNull(node.getRightChild())) {
                node.setRightChild(new Node(valueToCompare));
                return true;
            } else {
                return addNode(node.getRightChild(), valueToCompare);
            }
        } else if (valueToCompare.compareTo(storageValue) < 0) {
            if (isNull(node.getLeftChild())) {
                node.setLeftChild(new Node(valueToCompare));
                return true;
            } else {
                return addNode(node.getLeftChild(), valueToCompare);
            }
        } else {
            return node.addAmount(valueToCompare);
        }
    }

    public Node getRootNode() {
        return rootNode;
    }

    public boolean search(RowEntityForBd i) {
        Node node = findNode(rootNode, i);
        return !isNull(node);
    }
    public Optional<RowEntityForBd> getValue(String value) {
        Node node = findNode(rootNode, new RowEntityForBd(value, new ArrayList<>()));
        if(nonNull(node)) {
            return Optional.ofNullable(node.getStorageValue());
        }
        return Optional.empty();

    }

    protected void goThroughTree(RowEntityForBd[] newArray, Node node) {
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

    protected Node findNode(Node nodeToStartSearch, RowEntityForBd valueToFind) {
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

    public void delete(RowEntityForBd data) {
        deleteNode(this.rootNode, data);
    }

    protected Node deleteNode(Node node, RowEntityForBd data) {
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

    public void setRootNode(Node rootNode) {
        this.rootNode = rootNode;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
