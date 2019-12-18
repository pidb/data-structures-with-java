
public class LinkedListStack<E> implements Stack<E> {
    private LinkedList<E> linkedList = new LinkedList<E>();

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public void push(E e) {
        linkedList.addFirst(e);
    }

    @Override
    public E pop() {
        return linkedList.removeFirst();
    }

    @Override
    public E peek() {
        return linkedList.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append("LikedList Stack: top -> [");

        for (int i = 0, n = getSize(); i < n; i++) {
            b.append(linkedList.get(i));
            if (i != n - 1) {
                b.append(",");
            }
        }
        b.append("]\n");
        return b.toString();
    }
}

