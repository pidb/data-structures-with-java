public class TwoThreeTree<K extends Comparable<K>, V> {

    // 2-3 node define:
    // 2-node: they hold two links (left and right) and one key
    // 3-node: they hold three links (left, middle, right) and two keys
    static private class Node<K extends Comparable<K>, V> {
        public K leftKey, middleKey, rightKey;
        public V leftValue, middleValue, rightValue;
        public Node<K, V> left, middleLeft, middleRight, right;

        private Node(K key1, V value1, K key2, V value2, K key3, V value3, Node<K, V> left, Node<K, V> middle, Node<K, V> right) {
            this.leftKey = key1;
            this.leftValue = value1;
            this.rightKey = key2;
            this.rightValue = value2;
            this.left = left;
            this.middleLeft = middle;
            this.right = right;
        }

        Node(K key1, V value1, K key2, V value2, Node<K, V> left, Node<K, V> middle, Node<K, V> right) {
            this.leftKey = key1;
            this.leftValue = value1;
            this.rightKey = key2;
            this.rightValue = value2;
            this.left = left;
            this.middleLeft = middle;
            this.right = right;
        }

        /**
         * leftKey, null
         * /      |     \
         * left   null   right
         *
         * @param leftKey
         * @param leftValue
         * @param left
         * @param right
         */
        Node(K leftKey, V leftValue, Node<K, V> left, Node<K, V> right) {
            this(leftKey, leftValue, null, null, null, null, left, null, right);
        }

        /**
         * k:v
         * /    \
         * null  null
         *
         * @param leftKey
         * @param leftValue
         */
        Node(K leftKey, V leftValue) {
            this(leftKey, leftValue, null, null, null, null, null, null, null);
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
        // 分解根节点
        if (isFourNode(root)) {
            Node<K, V> left = new Node<K, V>(root.leftKey, root.leftValue, root.left, root.middleLeft);
            Node<K, V> right = new Node<K, V>(root.rightKey, root.rightValue, root.middleRight, root.right);
            root = new Node<K, V>(root.middleKey, root.middleValue, left, right);
        }
    }

    private Node<K, V> add(Node<K, V> node, K key, V value) {
        if (node == null) {
            size++;
            return new Node<>(key, value);
        }

        if (isLeaf(node)) {
            merge(node, key, value);
            return node;
        } else {
            // 非叶子节点2节点
            if (isTwoNode(node)) {
                // key < node.leftKey
                if (key.compareTo(node.leftKey) < 0) {
                    Node<K, V> newRoot = add(node.left, key, value);

                    // 添加后返回的新节点是 2-node 或者 3-node 直接挂接在树上
                    if (isTwoNode(newRoot) || isThreeNode(newRoot)) {
                        node.left = newRoot;
                        return node;
                    }

                    // 添加后返回的新节点是一个临时的 4-node, 当前根节点是一个2-node,
                    if (isFourNode(newRoot) && isTwoNode(node)) {
                        return split42NodeLeft(newRoot, node);
                    }

                    // 添加后返回的新节点是一个临时的 4-node, 当前根节点是一个3-node,
                    if (isFourNode(newRoot) && isThreeNode(node)) {
                        return split43NodeLeft(newRoot, node);
//                        // 让4-node中键和3-node合并成为4-node
//                        merge(node, newRoot.middleKey, newRoot.middleValue);
//                        // 调整4-node的子节点
//                        node.left = new Node<K, V>(newRoot.leftKey, newRoot.leftValue, newRoot.left, newRoot.middleLeft);
//                        node.middleRight = node.middleLeft;
//                        node.middleLeft = new Node<K, V>(newRoot.rightKey, newRoot.rightValue, newRoot.middleRight, newRoot.right);
//                        return node;
                    }
                }

                // key > node.leftKey
                if (key.compareTo(node.leftKey) > 0) {
                    Node<K, V> newRoot = add(node.right, key, value);

                    // 添加后返回的新节点是 2-node 或者 3-node 直接挂接在树上
                    if (isTwoNode(newRoot) || isThreeNode(newRoot)) {
                        node.right = newRoot;
                        return node;
                    }

                    // 添加后返回的新节点是一个临时的 4-node, 当前根节点是一个2-node,
                    if (isFourNode(newRoot) && isTwoNode(node)) {
                        return split42NodeRight(newRoot, node);
                    }

                    // 添加后返回的新节点是一个临时的 4-node, 当前根节点是一个3-node,
                    if (isFourNode(newRoot) && isThreeNode(node)) {
                        return split43NodeRight(newRoot, node);
//                        // 让4-node中键和3-node合并成为4-node
//                        merge(node, newRoot.middleKey, newRoot.middleValue);
//                        // 调整4-node的子节点
//                        node.middleRight = new Node<K, V>(newRoot.leftKey, newRoot.leftValue, newRoot.left, newRoot.middleLeft);
//                        node.right = new Node<K, V>(newRoot.rightKey, newRoot.rightValue, newRoot.middleRight, newRoot.right);
//                        return node;
                    }
                }

            } else if (isThreeNode(node)) {

                // node.left < key < node.right
                if (key.compareTo(node.leftKey) > 0 && key.compareTo(node.rightKey) < 0) {
                    Node<K, V> newRoot = add(node.middleLeft, key, value);

                    if (isTwoNode(newRoot) || isThreeNode(newRoot)) {
                        node.middleLeft = newRoot;
                        return node;
                    }

                    if (isFourNode(newRoot) && isTwoNode(node)) {
                        System.out.println("middle -> 扯淡2");
                        return node;
                    }

                    if (isFourNode(newRoot) && isThreeNode(node)) {
                        return split43NodeMiddle(newRoot, node);
//                        // 让4-node中键和3-node合并成为4-node
//                        merge(node, newRoot.middleKey, newRoot.middleValue);
//                        node.middleLeft = new Node<K, V>(newRoot.leftKey, newRoot.leftValue, newRoot.left, newRoot.middleLeft);
//                        node.middleRight = new Node<K, V>(newRoot.rightKey, newRoot.rightValue, newRoot.middleRight, newRoot.right);
//                        return node;
                    }
                }

                // key < node.left
                if (key.compareTo(node.leftKey) < 0) {
                    Node<K, V> newRoot = add(node.left, key, value);

                    if (isTwoNode(newRoot) || isThreeNode(newRoot)) {
                        node.left = newRoot;
                        return node;
                    }

                    if (isFourNode(newRoot) && isTwoNode(node)) {
                        System.out.println("left -> 扯淡2");
                        return node;
                    }


                    // 添加后返回的新节点是一个临时的 4-node, 当前根节点是一个3-node,
                    if (isFourNode(newRoot) && isThreeNode(node)) {
                        return split43NodeLeft(newRoot, node);
//                        // 让4-node中键和3-node合并成为4-node
//                        merge(node, newRoot.middleKey, newRoot.middleValue);
//                        // 调整4-node的子节点
//                        node.left = new Node<K, V>(newRoot.leftKey, newRoot.leftValue, newRoot.left, newRoot.middleLeft);
//                        node.middleRight = node.middleLeft;
//                        node.middleLeft = new Node<K, V>(newRoot.rightKey, newRoot.rightValue, newRoot.middleRight, newRoot.right);
//                        return node;
                    }
                }

                // key > node.right
                if (key.compareTo(node.rightKey) > 0) {
                    Node<K, V> newRoot = add(node.right, key, value);

                    if (isTwoNode(newRoot) || isThreeNode(newRoot)) {
                        node.right = newRoot;
                        return node;
                    }

                    if (isFourNode(newRoot) && isTwoNode(node)) {
                        System.out.println("right -> 扯淡2");
                        return node;
                    }

                    // 添加后返回的新节点是一个临时的 4-node, 当前根节点是一个3-node,
                    if (isFourNode(newRoot) && isThreeNode(node)) {
                        return split43NodeRight(newRoot, node);
//                        // 让4-node中键和3-node合并成为4-node
//                        merge(node, newRoot.middleKey, newRoot.middleValue);
//                        // 调整4-node的子节点
//                        node.middleRight = new Node<K, V>(newRoot.leftKey, newRoot.leftValue, newRoot.left, newRoot.middleLeft);
//                        node.right = new Node<K, V>(newRoot.rightKey, newRoot.rightValue, newRoot.middleRight, newRoot.right);
//                        return node;
                    }
                }
            }
        }
        return node;
    }

    private Node<K, V> split42NodeLeft(Node<K, V> node4, Node<K, V> node2) {
        // 分离4-node
        node4 = split4Node(node4);

        // 合并2-node和分离后的4-node为3-node
        merge42Node(node2, node4);

        // 调整3-node的子节点
        node2.left = node4.left;
        node2.middleLeft = node4.right;
        return node2;
    }

    private Node<K, V> split42NodeRight(Node<K, V> node4, Node<K, V> node2) {
        // 分离4-node
        node4 = split4Node(node4);

        // 合并2-node和分离后的4-node为3-node
        merge42Node(node2, node4);

        // 调整3-node的子节点
        node2.middleLeft = node4.left;
        node2.right = node4.right;
        return node2;
    }

    private Node<K, V> split43NodeLeft(Node<K, V> node4, Node<K, V> node3) {
        node4 = split4Node(node4);

        merge43Node(node4, node3);

        // 调整4-node的子节点
        node3.left = node4.left;
        node3.middleRight = node3.middleLeft;
        node3.middleLeft = node4.right;
        return node3;
    }

    private Node<K, V> split43NodeMiddle(Node<K, V> node4, Node<K, V> node3) {
        // 让4-node中键和3-node合并成为4-node
        node4 = split4Node(node4);

        merge43Node(node4, node3);

        // 调整4-node的子节点
        node3.middleLeft = node4.left;
        node3.middleRight = node4.right;
        return node3;
    }

    private Node<K, V> split43NodeRight(Node<K, V> node4, Node<K, V> node3) {
        node4 = split4Node(node4);

        merge43Node(node4, node3);

        // 调整4-node的子节点
        node3.middleRight = node4.left;
        node3.right = node4.right;
        return node3;
    }

    // splitFourNode 分离4-node为一个2-node
    private Node<K, V> split4Node(Node<K, V> node4) {
        K parentKey, leftKey, rightKey;
        V parentValue, leftValue, rightValue;

        if (node4.middleKey.compareTo(node4.leftKey) < 0) {
            parentKey = node4.leftKey;
            parentValue = node4.leftValue;
            leftKey = node4.middleKey;
            leftValue = node4.middleValue;
            rightKey = node4.rightKey;
            rightValue = node4.rightValue;

        } else if (node4.middleKey.compareTo(node4.rightKey) > 0) {
            parentKey = node4.rightKey;
            parentValue = node4.rightValue;
            leftKey = node4.leftKey;
            leftValue = node4.leftValue;
            rightKey = node4.middleKey;
            rightValue = node4.middleValue;
        } else {
            parentKey = node4.middleKey;
            parentValue = node4.middleValue;
            leftKey = node4.leftKey;
            leftValue = node4.leftValue;
            rightKey = node4.rightKey;
            rightValue = node4.rightValue;
        }

        return new Node<K, V>(parentKey, parentValue,
                new Node<K, V>(leftKey, leftValue, node4.left, node4.middleLeft),
                new Node<K, V>(rightKey, rightValue, node4.middleRight, node4.right));
    }

    // merge42Node 合并2-node和4-node为一个3-node
    private void merge42Node(Node<K, V> node2, Node<K, V> node4) {
        if (node4.leftKey.compareTo(node2.leftKey) < 0) { // key < node.key1
            node2.rightKey = node2.leftKey;
            node2.rightValue = node2.leftValue;
            node2.leftKey = node4.leftKey;
            node2.leftValue = node4.leftValue;
        } else if (node4.leftKey.compareTo(node2.leftKey) > 0) { // key > node.key1
            node2.rightKey = node4.leftKey;
            node2.rightValue = node4.leftValue;
        }
    }

    // merge43Node 合并一个4-node和一个3-node为一个4-node
    private void merge43Node(Node<K, V> node4, Node<K, V> node3) {
        if (node4.leftKey.compareTo(node3.leftKey) > 0 && node4.leftKey.compareTo(node3.rightKey) < 0) {
            node3.middleKey = node4.leftKey;
            node3.middleValue = node4.leftValue;
        } else if (node4.leftKey.compareTo(node3.leftKey) < 0) {
            node3.middleKey = node3.leftKey;
            node3.middleValue = node3.leftValue;
            node3.leftKey = node4.leftKey;
            node3.leftValue = node4.leftValue;
        } else if (node4.leftKey.compareTo(node3.rightKey) > 0) {
            node3.middleKey = node3.rightKey;
            node3.middleValue = node3.rightValue;
            node3.rightKey = node4.leftKey;
            node3.rightValue = node4.leftValue;
        }
    }

    private void merge(Node<K, V> node, K key, V value) {
        if (isTwoNode(node)) {
            if (key.compareTo(node.leftKey) < 0) { // key < node.key1
                node.rightKey = node.leftKey;
                node.rightValue = node.leftValue;
                node.leftKey = key;
                node.leftValue = value;
            } else if (key.compareTo(node.leftKey) > 0) { // key > node.key1
                node.rightKey = key;
                node.rightValue = value;
            }
        } else if (isThreeNode(node)) {
            if (key.compareTo(node.leftKey) > 0 && key.compareTo(node.rightKey) < 0) {
                node.middleKey = key;
                node.middleValue = value;
            } else if (key.compareTo(node.leftKey) < 0) {
                node.middleKey = node.leftKey;
                node.middleValue = node.leftValue;
                node.leftKey = key;
                node.leftValue = value;
            } else if (key.compareTo(node.rightKey) > 0) {
                node.middleKey = node.rightKey;
                node.middleValue = node.rightValue;
                node.rightKey = key;
                node.rightValue = value;
            }
        }
    }

//
//    private void mergeToFourNode(Node<K, V> node, K key, V value) {
//        if (key.compareTo(node.leftKey) > 0 && key.compareTo(node.rightKey) < 0) {
//            node.middleKey = key;
//            node.middleValue = value;
//        } else if (key.compareTo(node.leftKey) < 0) {
//            node.middleKey = node.leftKey;
//            node.middleValue = node.leftValue;
//            node.leftKey = key;
//            node.leftValue = value;
//        } else if (key.compareTo(node.rightKey) > 0) {
//            node.middleKey = node.rightKey;
//            node.middleValue = node.rightValue;
//            node.rightKey = key;
//            node.rightValue = value;
//        }
//    }
//    // split 对给定的4节点进行分裂, 生成一棵新树, 返回新树的根节点
//    private Node<K, V> split(Node<K, V> node, K key, V value) {
//        if (key.compareTo(node.leftKey) > 0 && key.compareTo(node.rightKey) < 0) { // node.key1 < key < node.key2
//            Node<K, V> left = new Node<K, V>(node.leftKey, node.leftValue);
//            Node<K, V> right = new Node<K, V>(node.rightKey, node.rightValue);
//            return new Node<K, V>(key, value, null, null, left, null, right);
//
//        } else if (key.compareTo(node.leftKey) < 0) { // key < node.key1
//            Node<K, V> left = new Node<K, V>(key, value);
//            Node<K, V> right = new Node<K, V>(node.rightKey, node.rightValue);
//            return new Node<K, V>(node.leftKey, node.leftValue, null, null, left, null, right);
//
//        } else if (key.compareTo(node.rightKey) > 0) { // key > node.key2
//            Node<K, V> left = new Node<K, V>(node.leftKey, node.leftValue);
//            Node<K, V> right = new Node<K, V>(key, value);
//            return new Node<K, V>(node.rightKey, node.rightValue, null, null, left, null, right);
//
//        } else {
//            return node;
//        }
//    }

    // isLeaf 判断节点是否为叶子节点
    private boolean isLeaf(Node<K, V> node) {
        return node.left == null && node.right == null;
    }

    // isTwoNode 判断节点是否是2-node
    private boolean isTwoNode(Node<K, V> node) {
        return node.leftKey != null && node.rightKey == null && node.middleKey == null;
    }

    // isThreeNde 判断节点是否是3-node
    private boolean isThreeNode(Node<K, V> node) {
        return node.leftKey != null && node.rightKey != null && node.middleKey == null;
    }

    // isFourNode 判断节点是否是4-node
    private boolean isFourNode(Node<K, V> node) {
        return node.leftKey != null && node.rightKey != null && node.middleKey != null;
    }

    public boolean is23Tree() {
        return is23Tree(root);
    }

    private boolean is23Tree(Node<K, V> node) {
        if (node != null) {
            if (isTwoNode(node)) {
                int leftHeight = leftHeight(node.left, 0);
                int rightHeight = rightHeight(node.right, 0);


                if (leftHeight != rightHeight) {
                    return false;
                }

                return is23Tree(node.left) && is23Tree(node.right);
            } else {
                return is23Tree(node.left) && is23Tree(node.middleLeft) && is23Tree(node.right);
            }
        }

        return true;
    }

    private int leftHeight(Node<K, V> node, int height) {
        if (node == null) {
            return height;
        }

        return leftHeight(node.left, height + 1);
    }

    private int rightHeight(Node<K, V> node, int height) {
        if (node == null) {
            return height;
        }
        return rightHeight(node.right, height + 1);
    }
}
