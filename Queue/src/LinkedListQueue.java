public class LinkedListQueue<E> implements Queue<E> {
    private class Node<E> {
        public E element;

        public Node<E> next;

        Node(E e, Node<E> next) {
            this.element = e;
            this.next = next;
        }

        Node(E e) {
            this.element = e;
            this.next = null;
        }

        Node() {
            this.element = null;
            this.next = null;
        }
    }

    private int size;

    private Node<E> head;

    private Node<E> tail;


    LinkedListQueue() {
        this.head = null;
        this.tail = null;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enqueue(E e) {
        Node<E> node = new Node<E>(e);
        if (size == 0) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    @Override
    public E dequeue() {
        if (size == 0) {
            return null;
        }

        Node<E> returnedNode = head;
        head = head.next;
        returnedNode.next = null;
        size--;
        return returnedNode.element;
    }

    @Override
    public E getFront() {
        if (size == 0) {
            return null;
        }

        return head.element;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append("LinkedListQueue [");
        Node<E> head = this.head;
        for (int i = 0; i < size; i++) {
            b.append(head.element);
            if (i != size - 1) {
                b.append(",");
            }
            head = head.next;
        }
        b.append("]");
        return b.toString();
    }
}
