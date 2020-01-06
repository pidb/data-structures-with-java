public class TwoThreeTree<K extends Comparable<K>, V> {
    static private class Node<K extends Comparable<K>, V> {
        public K leftKey, rightKey;
        public V leftValue, rightValue;
        public Node<K, V> left, middle, right;

        Node(K key1, V value1, K key2, V value2, Node<K, V> left, Node<K, V> middle, Node<K, V> right) {
            this.leftKey = key1;
            this.leftValue = value1;
            this.rightKey = key2;
            this.rightValue = value2;
            this.left = left;
            this.middle = middle;
            this.right = right;
        }

        Node(K key, V value) {
            this(key, value, null, null, null, null, null);
        }

    }

    private int size;
    private Node<K, V> root;

    TwoThreeTree() {
        this.size = 0;
        this.root = null;
    }

    public void add(K key, V value) {
        root = add(root, key, value);
    }


    private Node<K, V> add(Node<K, V> node, K key, V value) {
        if (node == null) {
            size++;
            return new Node<>(key, value);
        }

        if (node.left == null && node.right == null) {
            if (isTwoNode(node)) {
                // 合并为3节点
                return merge(node, key, value);
            }

            if (isThreeNode(node)) {
                // 分裂为2节点
                return split(node, key, value);
            }
        }


        if (isTwoNode(node)) {
            if (key.compareTo(node.leftKey) < 0) { // node.key1 < key
                Node<K, V> newRoot = add(node.left, key, value);

                // 叶子节点合并为了一个3节点
                if (isThreeNode(newRoot)) {
                    node.left = newRoot;
                    return node;
                }

                // 叶子节点分裂且root节点是一个2节点
                if (isTwoNode(newRoot) && isTwoNode(node)) {
                    // node合并为2节点
                    if (newRoot.leftKey.compareTo(node.leftKey) < 0) { // newRoot < node
                        node.rightKey = node.leftKey;
                        node.rightValue = node.leftValue;
                        node.leftKey = newRoot.leftKey;
                        node.leftValue = newRoot.leftValue;

                    } else if (newRoot.leftKey.compareTo(node.leftKey) > 0) { // newRoot > node
                        node.rightKey = newRoot.leftKey;
                        node.rightValue = newRoot.leftValue;
                    }

                    // 形成三叉树
                    node.left = new Node<K, V>(newRoot.left.leftKey, newRoot.left.leftValue);
                    node.middle = new Node<K, V>(newRoot.right.leftKey, newRoot.right.leftValue);
                    return node;

                } else if (isTwoNode(newRoot) && isThreeNode(node)) { // 叶子节点分裂且root节点是一个3节点
                    // 找出待分裂节点

                    // node.left < newRoot < node.right
                    if (newRoot.leftKey.compareTo(node.leftKey) > 0 && newRoot.leftKey.compareTo(node.rightKey) < 0) {
                        Node<K, V> left = new Node<K, V>(node.leftKey, node.leftValue, null, null, node.left, null, node.middle);
                        Node<K, V> right = new Node<K, V>(node.rightKey, node.rightValue, null, null, newRoot.left, null, newRoot.right);
                        return new Node<K, V>(newRoot.leftKey, newRoot.leftValue, null, null, left, null, right);
                    }

                    // newRoot < node.left -> newRoot.left, node.left, node.right
                    if (newRoot.leftKey.compareTo(node.leftKey) < 0) {
                        Node<K, V> left = new Node<K, V>(newRoot.leftKey, newRoot.leftValue, null, null, node.left, null, node.middle);
                        Node<K, V> right = new Node<K, V>(node.rightKey, node.rightValue, null, null, newRoot.left, null, newRoot.right);
                        return new Node<K, V>(node.leftKey, node.leftValue, null, null, left, null, right);
                    }

                    // newRoot > node.right -> node.left, node.right, newRoot.left
                    if (newRoot.leftKey.compareTo(node.rightKey) > 0) {
                        Node<K, V> left = new Node<K, V>(node.leftKey, node.leftValue, null, null, node.left, null, node.middle);
                        return new Node<K, V>(node.rightKey, node.rightValue, null, null, left, null, newRoot);
                    }
                }
            } else if(key.compareTo(node.leftKey) > 0) {

            }
        }


        if (isThreeNode(node)) {
            // node.left < key < node.right
            if (key.compareTo(node.leftKey) > 0 && key.compareTo(node.rightKey) < 0) {

            }

            // key < node.left
            if (key.compareTo(node.leftKey) < 0) {
                Node<K, V> newRoot = add(node.left, key, value);

                // 叶子节点合并为了一个3节点
                if (isThreeNode(newRoot)) {
                    node.left = newRoot;
                    return node;
                }

                // 叶子节点分裂且root节点是一个2节点
                if (isTwoNode(newRoot) && isTwoNode(node)) {
                    // node合并为2节点
                    if (newRoot.leftKey.compareTo(node.leftKey) < 0) { // newRoot < node
                        node.rightKey = node.leftKey;
                        node.rightValue = node.leftValue;
                        node.leftKey = newRoot.leftKey;
                        node.leftValue = newRoot.leftValue;

                    } else if (newRoot.leftKey.compareTo(node.leftKey) > 0) { // newRoot > node
                        node.rightKey = newRoot.leftKey;
                        node.rightValue = newRoot.leftValue;
                    }

                    // 形成三叉树
                    node.left = new Node<K, V>(newRoot.left.leftKey, newRoot.left.leftValue);
                    node.middle = new Node<K, V>(newRoot.right.leftKey, newRoot.right.leftValue);
                    return node;

                } else if (isTwoNode(newRoot) && isThreeNode(node)) { // 叶子节点分裂且root节点是一个3节点
                    // 找出待分裂节点

                    // node.left < newRoot < node.right
                    if (newRoot.leftKey.compareTo(node.leftKey) > 0 && newRoot.leftKey.compareTo(node.rightKey) < 0) {
                        Node<K, V> left = new Node<K, V>(node.leftKey, node.leftValue, null, null, node.left, null, node.middle);
                        Node<K, V> right = new Node<K, V>(node.rightKey, node.rightValue, null, null, newRoot.left, null, newRoot.right);
                        return new Node<K, V>(newRoot.leftKey, newRoot.leftValue, null, null, left, null, right);
                    }

                    // newRoot < node.left -> newRoot.left, node.left, node.right
                    if (newRoot.leftKey.compareTo(node.leftKey) < 0) {
                        Node<K, V> right = new Node<K, V>(node.rightKey, node.rightValue, null, null, node.middle, null, node.right);
                        return new Node<K, V>(node.leftKey, node.leftValue, null, null, newRoot, null, right);
                    }

                    // newRoot > node.right -> node.left, node.right, newRoot.left
                    if (newRoot.leftKey.compareTo(node.rightKey) > 0) {
                        Node<K, V> left = new Node<K, V>(node.leftKey, node.leftValue, null, null, node.left, null, node.middle);
                        return new Node<K, V>(node.rightKey, node.rightValue, null, null, left, null, newRoot);
                    }
                }
            }
        }

        return node;
    }

    private Node<K, V> merge(Node<K, V> node, K key, V value) {
        if (key.compareTo(node.leftKey) < 0) { // key < node.key1
            node.rightKey = node.leftKey;
            node.rightValue = node.leftValue;
            node.leftKey = key;
            node.leftValue = value;
        } else if (key.compareTo(node.leftKey) > 0) { // key > node.key1
            node.rightKey = key;
            node.rightValue = value;
        }

        return node;
    }

    // split 对给定的4节点进行分裂, 生成一棵新树, 返回新树的根节点
    private Node<K, V> split(Node<K, V> node, K key, V value) {
        if (key.compareTo(node.leftKey) > 0 && key.compareTo(node.rightKey) < 0) { // node.key1 < key < node.key2
            Node<K, V> left = new Node<K, V>(node.leftKey, node.leftValue);
            Node<K, V> right = new Node<K, V>(node.rightKey, node.rightValue);
            return new Node<K, V>(key, value, null, null, left, null, right);

        } else if (key.compareTo(node.leftKey) < 0) { // key < node.key1
            Node<K, V> left = new Node<K, V>(key, value);
            Node<K, V> right = new Node<K, V>(node.rightKey, node.rightValue);
            return new Node<K, V>(node.leftKey, node.leftValue, null, null, left, null, right);

        } else if (key.compareTo(node.rightKey) > 0) { // key > node.key2
            Node<K, V> left = new Node<K, V>(node.leftKey, node.leftValue);
            Node<K, V> right = new Node<K, V>(key, value);
            return new Node<K, V>(node.rightKey, node.rightValue, null, null, left, null, right);

        } else {
            return node;
        }
    }

    // isTwoNode 判断节点是否是2节点
    private boolean isTwoNode(Node<K, V> node) {
        if (node.leftKey != null && node.rightKey == null) {
            return true;
        }

        return false;
    }

    // isThreeNde 判断节点是否是3节点
    private boolean isThreeNode(Node<K, V> node) {
        if (node.leftKey != null && node.rightKey != null) {
            return true;
        }

        return false;
    }
}
