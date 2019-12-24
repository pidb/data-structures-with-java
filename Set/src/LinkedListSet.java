public class LinkedListSet<E> implements Set<E> {
    private class Node<E> {
        public E element;
        public Node<E> next;

        Node(E e, Node<E> next) {
            this.element = e;
            this.next = next;
        }

        Node(E e) {
            this(e, null);
        }

        Node() {
            this(null, null);
        }
    }

    private int size;

    private Node<E> dummyHead;

    LinkedListSet() {
        this.size = 0;
        this.dummyHead = new Node<E>(null);
    }

    @Override
    public void add(E e) {
        if (contains(e)) {
            return;
        }

        Node<E> node = new Node(e);
        Node<E> prev = dummyHead;
        node.next = prev.next;
        prev.next = node;
        size++;
    }

    @Override
    public void remove(E e) {
        if (size == 0) {
            return;
        }

        for(Node<E> prev = dummyHead; prev != null; prev = prev.next) {
            if (prev.next != null && prev.next.element.equals(e)) {
                prev.next = prev.next.next;
                size--;
                return;
            }
        }
    }

    @Override
    public boolean contains(E e) {
        for (Node<E> cur = dummyHead.next; cur != null; cur = cur.next) {
            if (cur.element.equals(e)) {
                return true;
            }
        }

        return false;
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
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append(String.format("LinkedListSet (size = %d): [", getSize()));
        for (Node<E> cur = dummyHead.next; cur != null; cur = cur.next) {
            b.append(cur.element);
            if (cur.next != null) {
                b.append(",");
            }
        }
        b.append("]");
        return b.toString();
    }
}
