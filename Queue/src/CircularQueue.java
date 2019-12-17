public class CircularQueue <E> implements Queue<E> {
    private E[] elements;

    private int front, rear, size, capacity;

    CircularQueue(int capacity) {
        this.elements = (E[]) new Object[capacity + 1];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
        this.capacity = capacity + 1;
    }

    CircularQueue() {
        this(10);
    }

    private int getCapacity() {
        return capacity - 1;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == rear;
    }

    private void resize(int newCapacity) {
        E[] newElements = (E[])new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[(i + front) % capacity];
        }
        this.front = 0;
        this.rear = capacity - 1;
        this.elements = newElements;
        this.capacity = newCapacity;
    }

    @Override
    public void enqueue(E e) {
        // queue is full
        if ((rear + 1) % capacity == front) {
            resize(getCapacity() * 2 + 1);
        }

        size++;
        elements[rear] = e;
        rear = (rear + 1) % capacity;
    }

    @Override
    public E dequeue() {
        // queue is empty
        if (front == rear) {
            throw new IllegalArgumentException("Queue is empty");
        }

        E returned = elements[front];
        elements[front] = null;
        front = (front + 1) % capacity;
        size--;
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }

        return returned;
    }

    @Override
    public E getFront() {
        return elements[front];
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append(String.format("CircularQueue size = %d, capacity = %d\n", getSize(), getCapacity()));
        b.append("CircularQueue [");
        for (int i = 0; i < size; i++) {
            b.append(elements[(i + front) % capacity]);
            if ((i + front) % capacity != rear - 1) {
                b.append(",");
            }
        }
        b.append("]\n");

        b.append("really storage [");
        for (int i = 0; i < capacity; i++) {
            b.append(elements[i]);
            if (i != capacity - 1) {
                b.append(",");
            }
        }
        b.append("]");

        return b.toString();
    }
}
