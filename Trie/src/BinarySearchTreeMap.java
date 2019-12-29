public class BinarySearchTreeMap<K extends Comparable<K>, V> implements Map<K, V> {

    static private class Node<K, V> {
        public K key;
        public V value;
        public Node<K, V> left, right;

        Node(K key, V value, Node<K, V> left, Node<K, V> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        Node(K key, V value) {
            this(key, value, null, null);
        }

        Node() {
            this(null, null, null, null);
        }
    }

    private int size;

    private Node<K, V> root;

    // put 方法在二分搜索树中添加一个k,v
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    // add 方法在二分搜索树中添加一个k,v, 返回添加后的二分搜索树的根节点
    private Node<K, V> put(Node<K, V> root, K key, V value) {
        if (root == null) {
            Node<K, V> newNode = new Node<>(key, value);
            size++;
            return newNode;
        }

        if (key.compareTo(root.key) < 0) {
            root.left = put(root.left, key, value);
        } else if (key.compareTo(root.key) > 0) {
            root.right = put(root.right, key, value);
        }

        return root;
    }

    // remove 方法从二分搜索树删除指定key对应的value并返回
    public void remove(K key) {
        if (root == null) {
            throw new IllegalArgumentException("bst is empty");
        }
        root = remove(root, key);
    }

    // remove 方法从二分搜索树删除指定key对应的value, 返回删除后二分搜索树的根节点
    private Node<K, V> remove(Node<K, V> root, K key) {
        if (root == null) {
            return null;
        }

        if (key.compareTo(root.key) < 0) {
            root.left = remove(root.left, key);
            return root;
        } else if (key.compareTo(root.key) > 0) {
            root.right = remove(root.right, key);
            return root;
        } else {
            if (root.left == null) {
                Node<K, V> rightNode = root.right;
                root.right = null;
                size--;
                return rightNode;
            }

            if (root.right == null) {
                Node<K, V> leftNode = root.left;
                root.left = null;
                size--;
                return leftNode;
            }

            Node<K, V> successor = getMinNode(root.right);
            successor.left = root.left;
            successor.right = removeMinNode(root.right);
            successor.left = successor.right = null;
            return successor;
        }
    }

    // getMinNode 方法获取二分搜索树最小的节点
    private Node<K, V> getMinNode(Node<K, V> root) {
        if (root == null) {
            return null;
        }

        if (root.left != null) {
            return getMinNode(root.left);
        }

        return root;
    }

    // removeMinNode 方法删除二分搜索树的最小节点, 返回二分搜索树的根节点
    private Node<K, V> removeMinNode(Node<K, V> root) {
        if (root == null) {
            return null;
        }

        if (root.left != null) {
            root.left = removeMinNode(root.left);
            return root;
        }

        Node<K, V> rightNode = root.right;
        root.right = null;
        size--;
        return rightNode;
    }

    // get 方法根据指定k返回v
    public V get(K key) {
        if (root == null) {
            return null;
        }

        Node<K, V> find = get(root, key);
        if (find == null) {
            return null;
        }

        return find.value;
    }

    // get 方法在二分搜索树中查找指定的key对应的节点并返回该节点
    private Node<K, V> get(Node<K, V> root, K key) {
        if (root == null) {
            return null;
        }

        if (key.compareTo(root.key) < 0) {
            return get(root.left, key);
        } else if (key.compareTo(root.key) > 0) {
            return get(root.right, key);
        }

        return root;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
