package type.tree;

import static java.util.Objects.nonNull;

public class Node<T> {

    private T storageValue;
    private int height;

    private Node<T> rightChild;

    private Node<T> leftChild;
    private int amount;

    public Node(T storageValue) {
        this.storageValue = storageValue;
        this.amount = 1;
        this.height = 1;
    }

    public void copyValues(Node<T> node) {
        setAmount(node.getAmount());
        setStorageValue(node.getStorageValue());
    }

    public Node<T> getCopy() {
        Node<T> node = new Node<>(storageValue);
        node.setLeftChild(leftChild);
        node.setRightChild(rightChild);
        node.setHeight(height);
        node.setAmount(amount);
        return node;
    }

    public void updateHeight() {
        int rightHeight = nonNull(rightChild) ? rightChild.getHeight() : 0;
        int leftHeight = nonNull(leftChild) ? leftChild.getHeight() : 0;

        height = Math.max(rightHeight, leftHeight) + 1;
    }

    private int getBalance() {
        int rightHeight = nonNull(rightChild) ? rightChild.getHeight() : 0;
        int leftHeight = nonNull(leftChild) ? leftChild.getHeight() : 0;

        return rightHeight - leftHeight;
    }

    public void rebalance() {
        int balance = getBalance();
        if (balance <= 1 && balance >= -1) {
            //баланс норм - ничего не делаем
            return;
        }
        if (balance > 1) {
            //баланс смещен в правую сторону
            leftRotation();
        } else {
            //баланс смещен в левую сторону
            rightRotation();
        }
    }

    private void leftRotation() {
        Node<T> childToMoveLeft = rightChild.getLeftChild();

        int amountCopy = getAmount();
        T storageCopy = getStorageValue();

        setAmount(rightChild.getAmount());
        setStorageValue(rightChild.getStorageValue());
        setRightChild(rightChild.getRightChild());
        if (nonNull(leftChild)) {
            leftChild.setLeftChild(leftChild.getCopy());
            leftChild.setAmount(amountCopy);
            leftChild.setStorageValue(storageCopy);
        } else {
            leftChild = new Node<>(storageCopy);
            leftChild.setAmount(amountCopy);
        }

        leftChild.setRightChild(childToMoveLeft);

        if (nonNull(leftChild.getRightChild())) {
            leftChild.getRightChild().updateHeight();
        }
        if (nonNull(leftChild.getLeftChild())) {
            leftChild.getLeftChild().updateHeight();
        }

        if (nonNull(rightChild)) {
            rightChild.updateHeight();
        }
        leftChild.updateHeight();
        updateHeight();

    }

    private void rightRotation() {
        //копия ноды, которая теперь новый рут
        Node<T> childToMoveRight = leftChild.getRightChild();
        //
        int amountCopy = getAmount();
        T storageCopy = getStorageValue();
        //1
        //делаем перевешивающую ноду текущим корнем
        setAmount(leftChild.getAmount());
        setStorageValue(leftChild.getStorageValue());
        //сдвигаем левый дальний узел вверх
        setLeftChild(leftChild.getLeftChild());
        //сдвигаем то, что раньше было корневой нодой вправо
        if (nonNull(rightChild)) {
            rightChild.setRightChild(rightChild.getCopy());
            rightChild.setAmount(amountCopy);
            rightChild.setStorageValue(storageCopy);
        } else {
            rightChild = new Node<>(storageCopy);
            rightChild.setAmount(amountCopy);
        }

        rightChild.setLeftChild(childToMoveRight);

        if (nonNull(rightChild.getRightChild())) {
            rightChild.getRightChild().updateHeight();
        }
        if (nonNull(rightChild.getLeftChild())) {
            rightChild.getLeftChild().updateHeight();
        }

        rightChild.updateHeight();
        if (nonNull(leftChild)) {
            leftChild.updateHeight();
        }
        updateHeight();

    }

    public void addAmount() {
        this.amount++;
    }

    public Node<T> getRightChild() {
        return rightChild;
    }

    public void setStorageValue(T storageValue) {
        this.storageValue = storageValue;
    }

    public void setRightChild(Node<T> rightChild) {
        this.rightChild = rightChild;
    }

    public void setLeftChild(Node<T> leftChild) {
        this.leftChild = leftChild;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Node<T> getLeftChild() {
        return leftChild;
    }

    public int getAmount() {
        return amount;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public T getStorageValue() {
        return storageValue;
    }
}
