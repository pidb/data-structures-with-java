public class ArrayQueue<E> implements Queue<E>{
    private Array<E> elements;

    ArrayQueue(int capacity) {
        elements = new Array<E>(capacity);
    }

    ArrayQueue() {
        this(10);
    }

    @Override
    public int getSize() {
        return elements.getSize();
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        elements.addLast(e);
    }

    @Override
    public E dequeue() {
        return elements.removeFirst();
    }

    @Override
    public E getFront() {
        return elements.get(0);
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append(String.format("ArrayQueue size = %d, capacity = %d\n",
                elements.getSize(), elements.getCapacity()));
        b.append("Array Queue: [");
        for (int i = 0, n = elements.getSize(); i < n; i++) {
            b.append(elements.get(i));
            if (i != n - 1) {
                b.append(",");
            }
        }
        b.append("]\n");
        return b.toString();
    }
}
