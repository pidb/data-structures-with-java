public interface MaxHeap<E> {
    int size();
    boolean isEmpty();
    void add(E e);
    E extractMax();
    void replace(E e);
}
