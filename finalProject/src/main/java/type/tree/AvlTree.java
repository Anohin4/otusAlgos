package type.tree;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import index.RowEntity;

public class AvlTree<T extends RowEntity> extends BinaryTree<T> {

    public AvlTree() {
        super();
    }

    public AvlTree(T value) {
        super(value);
    }
    @Override
    protected Node<T> deleteNode(Node<T> node, T data) {
        if (isNull(node)) {
            //если мы пришли сюда - нужного числа нет
            return null;
        }
        //рекурсивно итерируемся по дереву в поисках нужной ноды
        if (data.compareTo(node.getStorageValue()) > 0) {
            node.setRightChild(deleteNode(node.getRightChild(), data));
            node.updateHeight();
            node.rebalance();
        } else if (data.compareTo(node.getStorageValue()) < 0) {
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

    @Override
    protected void addNode(Node<T> node, T value) {
        super.addNode(node, value);
        node.updateHeight();
        node.rebalance();
    }

    private Node<T> minValue(Node<T> node) {

        if (node.getLeftChild() != null) {
            return minValue(node.getLeftChild());
        }
        return node;
    }

}
