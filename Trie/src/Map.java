public interface Map<K extends Comparable<K>, V> {
    void put(K key, V value);
    void remove(K key);
    V get(K key);
    int size();
    boolean isEmpty();
}
