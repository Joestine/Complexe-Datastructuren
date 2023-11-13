package main.structures.tree;

import java.util.function.Function;

public class AVLTree<T extends Comparable<T>> {
    private AVLNode<T> root;

    public AVLTree() {
        root = null;
    }

    public void insert(T data) {
        if (data == null) {
            throw new NullPointerException("Data cannot be null");
        }
        root = insert(root, data);
    }

    private AVLNode<T> insert(AVLNode<T> node, T data) {
        if (node == null) {
            return new AVLNode<>(data);
        }

        if (data.compareTo(node.getData()) < 0) {
            node.setLeft(insert(node.getLeft(), data));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(insert(node.getRight(), data));
        } else {
            return node;
        }

        node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));

        int balance = getBalance(node);

        if (balance > 1 && data.compareTo(node.getLeft().getData()) < 0) {
            return rightRotate(node);
        }

        if (balance < -1 && data.compareTo(node.getRight().getData()) > 0) {
            return leftRotate(node);
        }

        if (balance > 1 && data.compareTo(node.getLeft().getData()) > 0) {
            node.setLeft(leftRotate(node.getLeft()));
            return rightRotate(node);
        }

        if (balance < -1 && data.compareTo(node.getRight().getData()) < 0) {
            node.setRight(rightRotate(node.getRight()));
            return leftRotate(node);
        }

        return node;
    }

    public void delete(T data) {
        root = delete(root, data);
    }

    public AVLNode<T> delete(AVLNode<T> node, T data) {
        if (node == null) {
            return null;
        }

        if (data.compareTo(node.getData()) < 0) {
            node.setLeft(delete(node.getLeft(), data));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(delete(node.getRight(), data));
        } else {
            if ((node.getLeft() == null) || (node.getRight() == null)) {
                AVLNode<T> temp = null;
                if (temp == node.getLeft()) {
                    temp = node.getRight();
                } else {
                    temp = node.getLeft();
                }

                if (temp == null) {
                    node = null;
                } else {
                    node = temp;
                }
            } else {
                AVLNode<T> temp = minValueNode(node.getRight());
                node.setData(temp.getData());
                node.setRight(delete(node.getRight(), temp.getData()));
            }
        }

        if (node == null) {
            return null;
        }

        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);

        int balance = getBalance(node);

        if (balance > 1 && getBalance(node.getLeft()) >= 0) {
            return rightRotate(node);
        }

        if (balance > 1 && getBalance(node.getLeft()) < 0) {
            node.setLeft(leftRotate(node.getLeft()));
            return rightRotate(node);
        }

        if (balance < -1 && getBalance(node.getRight()) <= 0) {
            return leftRotate(node);
        }

        if (balance < -1 && getBalance(node.getRight()) > 0) {
            node.setRight(rightRotate(node.getRight()));
            return leftRotate(node);
        }

        return node;
    }

    public T search(String property, Function<T, String> propertyExtractor) {
        return search(root, property, propertyExtractor);
    }

    private T search(AVLNode<T> node, String property, Function<T, String> propertyExtractor) {
        if (node == null) {
            return null;
        }

        T nodeData = node.getData();
        String nodeProperty = propertyExtractor.apply(nodeData);

        int cmp = property.compareTo(nodeProperty);
        if (cmp < 0) {
            return search(node.getLeft(), property, propertyExtractor);
        } else if (cmp > 0) {
            return search(node.getRight(), property, propertyExtractor);
        } else {
            return nodeData;
        }
    }

    private AVLNode<T> minValueNode(AVLNode<T> right) {
        AVLNode<T> current = right;

        while (current.getLeft() != null) {
            current = current.getLeft();
        }

        return current;
    }

    private AVLNode<T> rightRotate(AVLNode<T> node) {
        AVLNode<T> left = node.getLeft();
        AVLNode<T> leftRight = left.getRight();

        left.setRight(node);
        node.setLeft(leftRight);

        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);
        left.setHeight(Math.max(height(left.getLeft()), height(left.getRight())) + 1);

        return left;
    }

    private AVLNode<T> leftRotate(AVLNode<T> node) {
        AVLNode<T> right = node.getRight();
        AVLNode<T> rightLeft = right.getLeft();

        right.setLeft(node);
        node.setRight(rightLeft);

        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);
        right.setHeight(Math.max(height(right.getLeft()), height(right.getRight())) + 1);

        return right;
    }

    private int height(AVLNode<T> node) {
        if (node == null) {
            return 0;
        }
        return node.getHeight();
    }

    private int getBalance(AVLNode<T> node) {
        if (node == null) {
            return 0;
        }
        return height(node.getLeft()) - height(node.getRight());
    }
}