import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class AvlTree {

    private Node rootNode;
    private int size;
    private int counterForSorting;

    public AvlTree(int value) {
        this.rootNode = new Node(value);
        this.size = 1;
    }

    public void insert(int value) {
        addNode(rootNode, value);
        size++;
    }

    public int[] returnSortedArray() {
        int[] newArray = new int[size];
        counterForSorting = 0;
        goThroughTree(newArray, rootNode);
        return newArray;
    }

    public void delete(Integer data) {
        Node node = deleteNode(this.rootNode, data);
    }

    private Node deleteNode(Node node, Integer data) {
        if (isNull(node)) {
            //если мы пришли сюда - нужного числа нет
            return null;
        }
        //рекурсивно итерируемся по дереву в поисках нужной ноды
        if (data > node.getStorageValue()) {
            node.setRightChild(deleteNode(node.getRightChild(), data));
            node.updateHeight();
            node.rebalance();
        } else if (data < node.getStorageValue()) {
            node.setLeftChild(deleteNode(node.getLeftChild(), data));
            node.updateHeight();
            node.rebalance();
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
    private void goThroughTree(int[] newArray, Node node) {
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

    private void addNode(Node node, int value) {
        if (value > node.getStorageValue()) {
            if (isNull(node.getRightChild())) {
                node.setRightChild(new Node(value));
            } else {
                addNode(node.getRightChild(), value);
            }
        } else if (value < node.getStorageValue()) {
            if (isNull(node.getLeftChild())) {
                node.setLeftChild(new Node(value));
            } else {
                addNode(node.getLeftChild(), value);
            }
        } else {
            node.addAmount();
        }
        node.updateHeight();
        node.rebalance();
    }

    private Node minValue(Node node) {

        if (node.getLeftChild() != null) {
            return minValue(node.getLeftChild());
        }
        return node;
    }

}
