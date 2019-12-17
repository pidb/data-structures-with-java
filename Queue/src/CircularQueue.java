public class CircularQueue <E> implements Queue<E> {
    private E[] elements;

    private int front, rear, size, capacity, availableCapacity;

    CircularQueue(int capacity) {
        this.elements = (E[]) new Object[capacity + 1];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
        this.capacity = capacity + 1;
        this.availableCapacity = capacity;
    }

    CircularQueue() {
        this(10);
    }

    private int getCapacity() {
        return capacity - 1;
    }

    private int getAvailableCapacity() {
        return availableCapacity;
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
        for (int i = 0; i < capacity; i++) {
            newElements[i] = elements[(i + front) % capacity];
        }
        this.front = 0;
        this.rear = capacity - 1;
        this.elements = newElements;
        this.capacity = newCapacity;
        this.availableCapacity = capacity - (front + rear) - 1;
    }

    @Override
    public void enqueue(E e) {
        // queue is full
        if ((rear + 1) % capacity == front) {
            resize(getCapacity() * 2 + 1);
        }

        elements[rear] = e;
        rear = (rear + 1) % capacity;
    }

    @Override
    public E dequeue() {
        if (front == rear) {
            throw new IllegalArgumentException("Queue is empty");
        }


        E returned = elements[front];
        elements[front] = null;
        front = (front + 1) % capacity;
        return returned;
    }

    @Override
    public E getFront() {
        return null;
    }
}
