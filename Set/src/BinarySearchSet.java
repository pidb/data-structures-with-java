public class BinarySearchSet<E extends Comparable<E>> implements Set<E> {
    private class Node<E> {
        public E element;

        public Node<E> left, right;

        Node(E element, Node<E> left, Node<E> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }

        Node(E element) {
            this(element, null, null);
        }

        Node() {
            this(null, null, null);
        }
    }

    private int size;

    private Node<E> root;

    BinarySearchSet() {
        this.size = 0;
        this.root = null;
    }

    @Override
    public void add(E e) {
        root = add(root, e);
    }

    // add 函数在二分搜索树中插入新的元素，返回插入的根节点
    private Node<E> add(Node<E> node, E e) {
        if (node == null) {
            size++;
            return new Node<E>(e);
        }

        if (e.compareTo(node.element) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.element) > 0) {
            node.right = add(node.right, e);
        }

        return node;
    }

    @Override
    public void remove(E e) {
        root = remove(root, e);
    }

    private Node<E> remove(Node<E> node, E e) {
        if (node == null) {
            return null;
        }

        if (e.compareTo(node.element) < 0) {
            node.left = remove(node.left, e);
        } else if (e.compareTo(node.element) > 0) {
            node.right = remove(node.right, e);
        } else {
            // hibbard delection
            if (node.left == null) {
                Node<E> rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            if (node.right == null) {
                Node<E> leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            Node<E> successor = getMinNode(node.right);
            successor.right = removeMinNode(node.right);
            successor.left = node.left;
            node.left = node.right = null;
            return successor;
        }

        return node;
    }

    private Node<E> getMinNode(Node<E> node) {
        if (node == null) {
            return null;
        }

        if (node.left != null) {
            return getMinNode(node.left);
        }

        return node;
    }

    private Node<E> removeMinNode(Node<E> node) {
        if (node == null) {
            return null;
        }

        if (node.left != null) {
            node.left = removeMinNode(node.left);
            return node;
        }

        Node<E> rightNode = node.right;
        node.right = null;
        size--;
        return rightNode;
    }

    @Override
    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node<E> node, E e) {
        if (node == null) {
            return false;
        }

        if (e.compareTo(node.element) < 0) {
            return contains(node.left, e);
        } else if (e.compareTo(node.element) > 0) {
            return contains(node.right, e);
        } else {
            return true;
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append(String.format("Binary Search Tree Set (size = %d): [", getSize()));
        preOrder(root, b);
        b.append("]");
        return b.toString();
    }

    private void preOrder(Node<E> node, StringBuilder b) {
        if (node == null) {
            return;
        }

        b.append(node.element);
        if (node.left != null || node.right != null) {
            b.append(",");
        }
        preOrder(node.left, b);
        preOrder(node.right, b);
    }
}
