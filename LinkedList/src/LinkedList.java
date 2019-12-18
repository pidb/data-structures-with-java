public class LinkedList<E> {

    private class Node {
        private Node next;

        private E element;

        Node(E e, Node next) {
            this.element = e;
            this.next = next;
        }

        Node(E e) {
            this.element = e;
            this.next = null;
        }

        Node() {
            this.next = null;
        }
    }

    private int size;

    private Node dummyHead;

    LinkedList() {
        this.size = 0;
        this.dummyHead = new Node();
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E e, int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("invalid index");
        }

        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        Node node = new Node(e);
        node.next = prev.next;
        prev.next = node;
        size++;
    }

    public void addFirst(E e) {
        add(e, 0);
    }

    public void addLast(E e) {
        add(e, size);
    }

    public E remove(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("invalid index");
        }

        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        Node returnedNode = prev.next;
        prev.next = returnedNode.next;
        returnedNode.next = null;
        size--;
        return returnedNode.element;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public E get(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("invalid index");
        }

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.element;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("invalid index");
        }

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.element = e;
    }

    public boolean contains(E e) {
        for (Node head = dummyHead.next; head != null; head = head.next) {
            if (head.element.equals(e)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append("LinkedList Head: ");
        Node head = dummyHead.next;
        for (int i = 0; i < size; i++) {
            b.append(head.element);
            if (head.next != null) {
                b.append(" ->");
            }
            head = head.next;
        }
        return b.toString();
    }
}
