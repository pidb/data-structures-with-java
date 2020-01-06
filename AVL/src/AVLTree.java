public class AVLTree<K extends Comparable<K>, V> {
    static private class Node<K extends Comparable<K>, V> {
        public int height;
        public K key;
        public V value;
        public Node<K, V> left, right;

        Node(K key, V value, Node<K, V> left, Node<K, V> right, int height) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.height = height;
        }

        Node(K key, V value, Node<K, V> left, Node<K, V> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.height = 1;
        }

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }

    private int size;
    private Node<K, V> root;

    AVLTree() {
        this.size = 0;
        this.root = null;
    }

    private int getHeight(Node<K, V> node) {
        if (node == null) {
            return 0;
        }

        return node.height;
    }

    private int getBalanceFactor(Node<K, V> node) {
        return getHeight(node.left) - getHeight(node.right);
    }

    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node<K, V> put(Node<K, V> root, K key, V value) {
        if (root == null) {
            size++;
            return new Node<K, V>(key, value);
        }

        if (key.compareTo(root.key) < 0) {
            root.left = put(root.left, key, value);
        } else if (key.compareTo(root.key) > 0) {
            root.right = put(root.right, key, value);
        }

        root = rotate(root);

        return root;
    }

    public void remove(K key) {
        root = remove(root, key);
    }

    private Node<K, V> remove(Node<K, V> root, K key) {
        if (root == null) {
            return null;
        }

        if (key.compareTo(root.key) < 0) {
            root.left = remove(root.left, key);

        } else if (key.compareTo(root.key) > 0) {
            root.right = remove(root.right, key);

        } else {
            // 左-右子树都为空说明是叶子节点, 直接删除即可
            if (root.left == null && root.right == null) {
                root = null;
                size--;
                return null;
            }

            // 左子树为空 删除该节点, 调整右子树并返回
            if (root.left == null) {
                Node<K, V> rightNode = root.right;
                root.right = null;
                size--;
                return rotate(rightNode);
            }

            // 右子树为空 删除该节点, 调整左子树并返回
            if (root.right == null) {
                Node<K, V> leftNode = root.left;
                root.left = null;
                size--;
                return rotate(leftNode);
            }

            // 左-右子树都不为空, 使用hibbard deletion算法后旋转successor
            Node<K, V> successor = getMinNode(root.right);
            successor.right = remove(root.right, successor.key);
            successor.left = root.left;
            root.left = root.right = null;
            return rotate(successor);
        }

        root = rotate(root);
        return root;
    }

    // rotate 对给定对节点进行旋转，返回旋转后的根节点
    private Node<K, V> rotate(Node<K, V> root) {
        int factor = getBalanceFactor(root);
        // LL
        if (factor > 1 && getBalanceFactor(root.left) >= 0) {
            // 右旋转修复
            root = rotateRight(root);
        } else if (factor < -1 && getBalanceFactor(root.right) <= 0) { // RR
            // 左旋转修复
            root = rotateLeft(root);
        } else if (factor > 1 && getBalanceFactor(root.left) < 0) { // LR
            // 左-右旋转修复
            root.left = rotateLeft(root.left);
            root = rotateRight(root);
        } else if (factor < -1 && getBalanceFactor(root.right) > 0) { // RL
            // 右-左旋转修复
            root.right = rotateRight(root.right);
            root = rotateLeft(root);
        } else {
            // 更新树的高度
            root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
        }

        return root;
    }

    private Node<K, V> rotateRight(Node<K, V> root) {
        Node<K, V> rootLeft = root.left;
        root.left = rootLeft.right;
        rootLeft.right = root;
        root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
        rootLeft.height = Math.max(getHeight(rootLeft.left), getHeight(rootLeft.right)) + 1;
        return rootLeft;
    }

    private Node<K, V> rotateLeft(Node<K, V> root) {
        Node<K, V> rootRight = root.right;
        root.right = rootRight.left;
        rootRight.left = root;
        root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
        rootRight.height = Math.max(getHeight(rootRight.left), getHeight(rootRight.right)) + 1;
        return rootRight;
    }

    private Node<K, V> getMinNode(Node<K, V> root) {
        if (root == null) {
            return null;
        }

        if (root.left != null) {
            return getMinNode(root.left);
        }

        return root;
    }

    public int size() {
        return size;
    }

    public boolean isBalance() {
        return isBalance(root);
    }

    private boolean isBalance(Node<K, V> root) {
        if (root == null) {
            return true;
        }

        if (Math.abs(getBalanceFactor(root)) > 1) {
            return false;
        }

        return isBalance(root.left) && isBalance(root.right);
    }

}
