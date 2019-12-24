public class BST<E extends Comparable<E>> {
    private class Node<E> {
        public E element;
        public Node<E> left;
        public Node<E> right;

        Node(E e) {
            this.element = e;
            this.left = null;
            this.right = null;
        }

        Node() {
            this(null);
        }
    }

    private Node<E> root;

    private int size;

    BST() {
        root = null;
        size = 0;
    }

    public void add(E e) {
        root = add(root, e);
    }

    private Node<E> add(Node<E> node, E e) {
        if (node == null) {
            node = new Node<>();
            node.element = e;
            size++;
            return node;
        }

        if (node.element.compareTo(e) > 0) {
            node.left = add(node.left, e);
        } else {
            node.right = add(node.right, e);
        }

        return node;
    }

    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node<E> node, E e) {
        if (node == null) return false;
        if (node.element.compareTo(e) < 0) {
            return contains(node.right, e);
        } else if (node.element.compareTo(e) > 0) {
            return contains(node.left, e);
        } else {
            return true;
        }
    }

    // getMax 获取当前二分搜索树最大节点所包含的元素
    public E getMax() {
        Node<E> maxNode = getMaxNode(root);
        if (maxNode == null) {
            return null;
        }
        return maxNode.element;
    }

    // getMaxNode 获取当前二分搜索树最大的节点
    private Node<E> getMaxNode(Node<E> node) {
        if (node == null) {
            return null;
        }

        if (node.right != null) {
            return getMaxNode(node.right);
        }

        return node;
    }

    // getMin 获取当前二分搜索树最小节点所包含的元素
    public E getMin() {
        Node<E> minNode = getMinNode(root);
        if (minNode == null) {
            return null;
        }
        return minNode.element;
    }

    // getMinNode 获取当前二分搜索树最小的节点
    private Node<E> getMinNode(Node<E> node) {
        if (node == null) {
            return null;
        }

        if (node.left != null) {
            return getMinNode(node.left);
        } else {
            return node;
        }
    }

    // removeMin 删除二分搜索树最小节点的元素并返回
    public E removeMin() {
        E minElement = getMin();
        if (minElement == null) {
            return null;
        }

        root = removeMinNode(root);
        return minElement;
    }

    // removeMinNode 删除二分搜索树最小的节点返回二叉树的根节点
    private Node<E> removeMinNode(Node<E> node) {
        if (node == null) {
            return null;
        }

        if (node.left == null) {
            Node<E> nodeRight = node.right;
            node.right = null;
            size--;
            return nodeRight;
        }

        node.left = removeMinNode(node.left);
        return node;
    }

    // removeMax 删除二分搜索树最大节点的元素并返回
    public E removeMax() {
        E maxElement = getMax();
        if (maxElement == null) {
            return null;
        }

        root = removeMaxNode(root);
        return maxElement;
    }

    // removeMaxNode 删除二分搜索树最大的节点返回二叉树的根节点
    private Node<E> removeMaxNode(Node<E> node) {
        if (node == null) {
            return null;
        }
        if (node.right == null) {
            Node<E> nodeLeft = node.left;
            node.left = null;
            size--;
            return nodeLeft;
        }

        node.right = removeMaxNode(node.right);
        return node;
    }

    // remove 从二分搜索树中删除指定的元素
    public void remove(E e) {
        root = removeNode(root, e);
    }

    // removeNode 从二分搜索树中删除给定的元素，并返回二分搜索树的根节点
    private Node<E> removeNode(Node<E> node, E e) {
        if (node == null) {
            return null;
        }

        if (e.compareTo(node.element) > 0) {
            node.right = removeNode(node.right, e);
            return node;
        } else if (e.compareTo(node.element) < 0) {
            node.left = removeNode(node.left, e);
            return node;
        } else {
            // hibbard deletion
            if (node.left  == null) {
                Node<E> rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            if (node.right  == null) {
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
    }

    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node<E> node) {
        if (node == null) return;
        System.out.println(node.element);
        preOrder(node.left);
        preOrder(node.right);
    }

    public void middleOrder() {
        middleOrder(root);
    }

    private void middleOrder(Node<E> node) {
        if (node == null) return;
        middleOrder(node.left);
        System.out.println(node.element);
        middleOrder(node.right);
    }

    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node<E> node) {
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.element);
    }

    public void preOrderNR() {
        if (root == null) {
            return;
        }
        StringBuilder builder = new StringBuilder();
        Stack<Node<E>> stack = new LinkedListStack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node<E> node = stack.pop();
            builder.append(node.element);
            builder.append(",");
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        System.out.println("preOrderNR: "+ builder.toString());
    }

    public void traverse() {
        Queue<Node<E>> queue = new LinkedListQueue<>();
        queue.enqueue(root);
        while(!queue.isEmpty()) {
            Node<E> node = queue.dequeue();
            System.out.println(node.element);
            if (node.left != null) {
                queue.enqueue(node.left);
            }

            if (node.right != null) {
                queue.enqueue(node.right);
            }
        }
    }
}
