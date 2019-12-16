public class Array<E> {
    private E[] elements;

    private int size;

    private int capacity;

    private int availableCapacity;

    Array(int capacity) {
        this.elements = (E[]) new Object[capacity];
        this.size = 0;
        this.capacity = capacity;
        this.availableCapacity = capacity;
    }

    Array() {
        this(10);
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void resize(int newCapacity) {
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
        capacity = newCapacity;
        availableCapacity = newCapacity - size;
    }

    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("invalid index");
        }

        if (size == capacity) {
            resize(capacity * 2);
        }

        for (int i = size - 1; i >= index; i--) {
            elements[i + 1] = elements[i];
        }
        elements[index] = e;
        size++;
        availableCapacity--;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("invalid index");
        }

        for (int i = index; i < size; i++) {
            elements[i] = elements[i + 1];
        }

        size--;
        availableCapacity++;
        elements[size] = null;
        if (size == capacity / 4) {
            if (capacity / 2 != 0) {
                resize(capacity / 2);
            }
        }
    }

    public void removeFirst() {
        remove(0);
    }

    public void removeLast() {
        remove(size - 1);
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("invalid index");
        }

        return elements[index];
    }

    public int getElement(E e) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(e)) {
                return i;
            }
        }

        return -1;
    }

    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("invalid index");
        }

        elements[index] = e;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append(String.format("Array size = %d, capacity = %d, available capacity = %d\n",
                size, capacity, availableCapacity));

        b.append("Array Elements [");
        for (int i = 0; i < size; i++) {
            b.append(elements[i]);
            if (i != size - 1) {
                b.append(",");
            }
        }
        b.append("];");
        return b.toString();
    }
}
