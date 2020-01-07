public class TwoThreeTree<K extends Comparable<K>, V> {

    // 2-3 node define:
    // 2-node: they hold two links (left and right) and one key
    // 3-node: they hold three links (left, middle, right) and two keys
    static private class Node<K extends Comparable<K>, V> {
        public K leftKey, rightKey, tmpKey;
        public V leftValue, rightValue, tmpValue;
        public Node<K, V> left, middle, middleRight, right;

        Node(K key1, V value1, K key2, V value2, K key3, V value3, Node<K, V> left, Node<K, V> middle, Node<K, V> right) {
            this.leftKey = key1;
            this.leftValue = value1;
            this.rightKey = key2;
            this.rightValue = value2;
            this.left = left;
            this.middle = middle;
            this.right = right;
        }

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
        // 分解根节点
        if (isFourNode(root)) {
            Node<K, V> left = new Node<K, V>(root.leftKey, root.leftValue, null, null, null, null, root.left, null, root.middle);
            Node<K, V> right = new Node<K, V>(root.rightKey, root.rightValue, null, null, null, null, root.middleRight, null, root.right);
            root = new Node<K, V>(root.tmpKey, root.tmpValue, null, null, null, null, left, null, right);
        }
    }

    private Node<K, V> add(Node<K, V> node, K key, V value) {
        if (node == null) {
            size++;
            return new Node<>(key, value);
        }

        if (isLeaf(node)) {
            if (isTwoNode(node)) {
                // 合并为3节点
                mergeToThreeNode(node, key, value);
                return node;
            }

            if (isThreeNode(node)) {
                // 合并为4节点
                mergeToFourNode(node, key, value);
                return node;
            }
        }

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
                    // 让4-node中键和2-node合并成为3-node
                    mergeToThreeNode(node, newRoot.tmpKey, newRoot.tmpValue);
                    // 调整3-node的子节点
                    node.left = new Node<K, V>(newRoot.leftKey, newRoot.leftValue);
                    node.middle = new Node<K, V>(newRoot.rightKey, newRoot.rightValue);
                    return node;
                }

                // 添加后返回的新节点是一个临时的 4-node, 当前根节点是一个3-node,
                if (isFourNode(newRoot) && isThreeNode(node)) {
                    // 让4-node中键和3-node合并成为4-node
                    mergeToFourNode(node, newRoot.tmpKey, newRoot.tmpValue);
                    // 调整4-node的子节点
                    node.left = new Node<K, V>(newRoot.leftKey, newRoot.leftValue);
                    node.middleRight = new Node<K, V>(node.middle.leftKey, node.middle.leftValue);
                    node.middle = new Node<K, V>(newRoot.rightKey, newRoot.rightValue);
                    return node;
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
                    // 让4-node中键和2-node合并成为3-node
                    mergeToThreeNode(node, newRoot.tmpKey, newRoot.tmpValue);
                    // 调整3-node的子节点
                    node.middle = new Node<K, V>(newRoot.leftKey, newRoot.leftValue);
                    node.right = new Node<K, V>(newRoot.rightKey, newRoot.rightValue);
                    return node;
                }

                // 添加后返回的新节点是一个临时的 4-node, 当前根节点是一个3-node,
                if (isFourNode(newRoot) && isThreeNode(node)) {
                    // 让4-node中键和3-node合并成为4-node
                    mergeToFourNode(node, newRoot.tmpKey, newRoot.tmpValue);
                    // 调整4-node的子节点
                    node.middleRight = new Node<K, V>(newRoot.leftKey, newRoot.leftValue);
                    node.right = new Node<K, V>(newRoot.rightKey, newRoot.rightValue);
                    return node;
                }
            }

        } else  if (isThreeNode(node)) {

            // node.left < key < node.right
            if (key.compareTo(node.leftKey) > 0 && key.compareTo(node.rightKey) < 0) {
                Node<K, V> newRoot = add(node.middle, key, value);

                if (isTwoNode(newRoot) || isThreeNode(newRoot)) {
                    node.middle = newRoot;
                    return node;
                }

                if (isFourNode(newRoot) && isTwoNode(node)) {
                    System.out.println("middle -> 扯淡2");
                    return node;
                }

                if (isFourNode(newRoot) && isThreeNode(node)) {
                    node.middle = new Node<K, V>(newRoot.leftKey, newRoot.leftValue);
                    node.middleRight = new Node<K, V>(newRoot.rightKey, newRoot.rightValue);
                    return node;
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
                    // 让4-node中键和3-node合并成为4-node
                    mergeToFourNode(node, newRoot.tmpKey, newRoot.tmpValue);
                    // 调整4-node的子节点
                    node.left = new Node<K, V>(newRoot.leftKey, newRoot.leftValue);
                    node.middleRight = new Node<K, V>(node.middle.leftKey, node.middle.leftValue);
                    node.middle = new Node<K, V>(newRoot.rightKey, newRoot.rightValue);
                    return node;
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
                    // 让4-node中键和3-node合并成为4-node
                    mergeToFourNode(node, newRoot.tmpKey, newRoot.tmpValue);
                    // 调整4-node的子节点
                    node.middleRight = new Node<K, V>(newRoot.leftKey, newRoot.leftValue);
                    node.right = new Node<K, V>(newRoot.rightKey, newRoot.rightValue);
                    return node;
                }
            }
        }



        return node;
    }

    private void mergeToThreeNode(Node<K, V> node, K key, V value) {
        if (key.compareTo(node.leftKey) < 0) { // key < node.key1
            node.rightKey = node.leftKey;
            node.rightValue = node.leftValue;
            node.leftKey = key;
            node.leftValue = value;
        } else if (key.compareTo(node.leftKey) > 0) { // key > node.key1
            node.rightKey = key;
            node.rightValue = value;
        }
    }

    private void mergeToFourNode(Node<K, V> node, K key, V value) {
        if (key.compareTo(node.leftKey) > 0 && key.compareTo(node.rightKey) < 0) {
            node.tmpKey = key;
            node.tmpValue = value;
        } else if (key.compareTo(node.leftKey) < 0) {
            node.tmpKey = node.leftKey;
            node.tmpValue = node.leftValue;
            node.leftKey = key;
            node.leftValue = value;
        } else if (key.compareTo(node.rightKey) > 0) {
            node.tmpKey = node.rightKey;
            node.tmpValue = node.rightValue;
            node.rightKey = key;
            node.rightValue = value;
        }
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

    private boolean isLeaf(Node<K, V> node) {
        return node.left == null && node.right == null;
    }

    // isTwoNode 判断节点是否是2节点
    private boolean isTwoNode(Node<K, V> node) {
        return node.leftKey != null && node.rightKey == null && node.tmpKey == null;
    }

    // isThreeNde 判断节点是否是3节点
    private boolean isThreeNode(Node<K, V> node) {
        return node.leftKey != null && node.rightKey != null && node.tmpKey == null;
    }

    private boolean isFourNode(Node<K, V> node) {
        return node.leftKey != null && node.rightKey != null && node.tmpKey != null;
    }
}
