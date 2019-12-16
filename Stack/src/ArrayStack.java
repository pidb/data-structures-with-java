public class ArrayStack<E> implements Stack<E> {
    private Array<E> elements;

    ArrayStack(int capacity) {
        elements = new Array<E>(capacity);
    }

    ArrayStack() {
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
    public void push(E e) {
        elements.addFirst(e);
    }

    @Override
    public E pop() {
        return elements.removeFirst();
    }

    @Override
    public E peek() {
        return elements.get(0);
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append(String.format("ArrayStack size = %d, capacity = %d\n",
                elements.getSize(), elements.getCapacity()));
        b.append("Array Stack: top -> [");

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
