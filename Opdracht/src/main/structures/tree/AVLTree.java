package main.structures.tree;

import main.tools.FormatForGraphViz;

import java.util.function.Function;

public class AVLTree<T extends Comparable<T>> {
    private AVLNode<T> root;

    public AVLTree() {
        root = null;
    }

    public void add(T data) {
        root = add(root, data);
    }

    private AVLNode<T> add(AVLNode<T> node, T data) {
        if (node == null) {
            return new AVLNode<>(data);
        }

        if (data.compareTo(node.getData()) < 0) {
            node.setLeft(add(node.getLeft(), data));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(add(node.getRight(), data));
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

    public boolean contains(T data) {
        return contains(root, data);
    }

    private boolean contains(AVLNode<T> node, T data) {
        if (node == null) {
            return false;
        }

        if (data.compareTo(node.getData()) < 0) {
            return contains(node.getLeft(), data);
        } else if (data.compareTo(node.getData()) > 0) {
            return contains(node.getRight(), data);
        } else {
            return true;
        }
    }

    public void remove(T data) {
        root = remove(root, data);
    }

    public AVLNode<T> remove(AVLNode<T> node, T data) {
        if (node == null) {
            return null;
        }

        if (data.compareTo(node.getData()) < 0) {
            node.setLeft(remove(node.getLeft(), data));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(remove(node.getRight(), data));
        } else {
            if (node.isLeaf()) {
                AVLNode<T> temp;
                if (node.getLeft() == null) {
                    temp = node.getRight();
                } else {
                    temp = node.getLeft();
                }
                node = temp;
            } else {
                AVLNode<T> temp = minValueNode(node.getRight());
                node.setData(temp.getData());
                node.setRight(remove(node.getRight(), temp.getData()));
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

    public T get(String property, Function<T, String> propertyExtractor) {
        return get(root, property, propertyExtractor);
    }

    private T get(AVLNode<T> node, String property, Function<T, String> propertyExtractor) {
        if (node == null) {
            return null;
        }

        T nodeData = node.getData();
        String nodeProperty = propertyExtractor.apply(nodeData);

        int cmp = property.compareTo(nodeProperty);
        if (cmp < 0) {
            return get(node.getLeft(), property, propertyExtractor);
        } else if (cmp > 0) {
            return get(node.getRight(), property, propertyExtractor);
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

    public String toString() {
        return "Tree {\n" + toString(root) + "}";
    }

    private String toString(AVLNode<T> node) {
        if (node == null) {
            return "";
        }

        return "\t" +
                node.getData().toString() +
                "\n" +
                toString(node.getLeft()) +
                toString(node.getRight());
    }

    public String graphViz() {
        return "digraph G {\n" + graphViz(root) + "}";
    }

    private String graphViz(AVLNode<T> node) {
        if (node == null) {
            return "";
        }

        StringBuilder result = new StringBuilder();

        if (node.getLeft() != null) {
            result.append("\t");
            result.append(FormatForGraphViz.formatForGraphviz(node.getData().toString()));
            result.append(" -> ");
            result.append(FormatForGraphViz.formatForGraphviz(node.getLeft().getData().toString()));
            result.append(";\n");
        }

        if (node.getRight() != null) {
            result.append("\t");
            result.append(FormatForGraphViz.formatForGraphviz(node.getData().toString()));
            result.append(" -> ");
            result.append(FormatForGraphViz.formatForGraphviz(node.getRight().getData().toString()));
            result.append(";\n");
        }

        result.append(graphViz(node.getLeft()));
        result.append(graphViz(node.getRight()));

        return result.toString();
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size(root);
    }

    private int size(AVLNode<T> node) {
        if (node == null) {
            return 0;
        }
        return 1 + size(node.getLeft()) + size(node.getRight());
    }
}