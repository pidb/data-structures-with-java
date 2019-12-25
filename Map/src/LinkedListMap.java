import java.util.TreeMap;

public class LinkedListMap<K, V> implements Map<K, V> {
    private class Node<K, V> {
        public K key;
        public V value;

        public Node<K, V> next;

        Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        Node(K key, V value) {
            this(key, value, null);
        }

        Node() {
            this(null, null, null);
        }
    }

    public int size;

    public Node<K, V> dummyHead;

    LinkedListMap() {
        this.size = 0;
        this.dummyHead = new Node<K, V>();
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
        for (Node<K, V> cur = dummyHead.next; cur != null; cur = cur.next) {
            if (cur.key.equals(key)) {
                cur.value = value;
                return;
            }
        }

        dummyHead.next = new Node<K, V>(key, value, dummyHead.next);
        size++;
    }

    @Override
    public V get(K key) {
        for (Node<K, V> cur = dummyHead.next; cur != null; cur = cur.next) {
            if (cur.key.equals(key)) {
                return cur.value;
            }
        }

        return null;
    }

    @Override
    public V remove(K key) {
        for(Node<K, V> prev = dummyHead; prev != null; prev = prev.next) {
            if (prev.next != null && prev.next.key.equals(key)) {
                V returned = prev.next.value;
                prev.next = prev.next.next;
                return returned;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        for(Node<K, V> cur = dummyHead.next; cur != null; cur = cur.next) {
            if (cur.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        for(Node<K, V> cur = dummyHead.next; cur != null; cur = cur.next) {
            if (cur.value.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append(String.format("LinkedListMap (size = %d) [", size));
        for (Node<K, V> cur = dummyHead.next; cur != null; cur = cur.next) {
            b.append("{key: ");
            b.append(cur.key);
            b.append(", value: ");
            b.append(cur.value);
            b.append("}");
            if (cur.next != null) {
                b.append(",");
            }
        }

        b.append("]");
        return b.toString();
    }
}
