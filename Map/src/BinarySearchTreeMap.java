
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

    public int size;

    public Node<K, V> root;

    BinarySearchTreeMap() {
        this.size = 0;
        this.root = null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    // put 方法在二分搜索树中存储kv, 并返回二分搜索树的根节点
    private Node<K, V> put(Node<K, V> node, K key, V value) {
        if (node == null) {
            Node<K, V> newNode = new Node<K, V>(key, value);
            size++;
            return newNode;
        }

        if (key.compareTo(node.key) < 0) {
            node.left = put(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = put(node.right, key, value);
        }

        return node;
    }

    @Override
    public V get(K key) {
        if (root == null || key == null) {
            return null;
        }

        return get(root, key).value;
    }

    // get 方法在二分搜索树中找到给定k对应的v, 返回该节点
    private Node<K, V> get(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) < 0) {
            return get(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            return get(node.right, key);
        } else {
            return node;
        }
    }

    @Override
    public V remove(K key) {
        if (root == null) {
            return null;
        }

        return remove(root, key).value;
    }

    // remove 方法从二分搜索树中删除给定k对应的v，返回删除后的二分搜索树根节点
    private Node<K, V> remove(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
        } else {
            if (node.left == null) {
                Node<K, V> rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            if (node.right == null) {
                Node<K, V> leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            Node<K, V> successor = getMinNode(node.right);
            successor.right = removeMinNode(node.right);
            successor.left = node.left;
            node.left = node.right = null;
            return successor;
        }

        return node;
    }

    private Node<K, V> getMinNode(Node<K, V> node) {
        if (node == null) {
            return null;
        }

        if (node.left != null) {
            return getMinNode(node.left);
        }

        return node;
    }

    private Node<K, V> removeMinNode(Node<K, V> node) {
        if (node == null) {
            return null;
        }

        if (node.left != null) {
            node.left = removeMinNode(node.left);
            return node;
        }

        Node<K, V> rightNode = node.right;
        node.right = null;
        size--;
        return rightNode;
    }

    @Override
    public boolean containsKey(K key) {
        return containsKey(root, key);
    }

    private boolean containsKey(Node<K, V> node, K key) {
        if (node == null) {
            return false;
        }

        if (key.compareTo(node.key) < 0) {
            return containsKey(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            return containsKey(node.right, key);
        } else {
            return true;
        }
    }

    @Override
    public boolean containsValue(V value) {
        if (root == null) {
            return false;
        }

        return containsValue(root, value);
    }

    private boolean containsValue(Node<K, V> node, V value) {
        if (node == null) {
            return false;
        }

        if (node.value.equals(value)) {
            return true;
        }

        containsValue(node.left, value);
        containsValue(node.right, value);
        return false;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append(String.format("Binary Search Tree Map (size = %d): [", size()));
        traversal(root, b);
        b.append("]");
        return b.toString();
    }

    public void traversal(Node<K, V> node, StringBuilder b) {
        if (node == null) {
            return;
        }

        Queue<Node<K, V>> q = new LinkedListQueue<Node<K, V>>();
        q.enqueue(node);
        while (!q.isEmpty()) {
            Node<K, V> root = q.dequeue();
            b.append("{");
            b.append(root.key);
            b.append(":");
            b.append(root.value);
            b.append("}");

            if (root.left != null || root.right != null) {
                b.append(", ");
            }

            if (root.left != null) {
                q.enqueue(root.left);
            }

            if (root.right != null) {
                q.enqueue(root.right);
            }
        }
    }
}
