public class BinaryIndexTreeMaxHeap<E extends Comparable<E>> implements MaxHeap<E> {
    private Array<E> data;

    static private class Array<E> {
        private E[] elements;
        private int size;

        Array(int cap) {
            this.elements = (E[]) (new Object[cap]);
            this.size = 0;
        }

        Array() {
            this(10);
        }

        public int size() {
            return size;
        }

        public E get(int index) {
            if (index < 0 || index >= size) {
                throw new IllegalArgumentException("invalid index");
            }

            return elements[index];
        }

        public void set(int index, E e) {
            if (index < 0 || index >= size) {
                throw new IllegalArgumentException("invalid index");
            }

            elements[index] = e;
        }

        public void addFist(E e) {
            add(0, e);
        }

        public void addLast(E e) {
            add(size, e);
        }

        public void add(int index, E e) {
            if (index < 0 || index > size) {
                throw new IllegalArgumentException("invalid index");
            }

            if (size == elements.length) {
                resize(2 * elements.length);
            }

            for (int i = index; i < size; i++) {
                elements[i + 1] = elements[i];
            }

            elements[index] = e;
            size++;
        }

        private void resize(int newCap) {
            E[] newElements = (E[]) (new Object[newCap]);
            for (int i = 0; i < size; i++) {
                newElements[i] = elements[i];
            }

            elements = newElements;
        }

        public void swap(int i, int j) {
            E tmp = elements[i];
            elements[i] = elements[j];
            elements[j] = tmp;
        }

        public E removeFirst() {
            return remove(0);
        }

        public E removeLast() {
            return remove(size - 1);
        }

        public E remove(int index) {
            if (index < 0 || index > size) {
                throw new IllegalArgumentException("invalid index");
            }

            E removed = elements[index];

            for (int i = index; i < size - 1; i++) {
                elements[i] = elements[i + 1];
            }

            elements[size - 1] = null;
            size--;
            if (size == elements.length / 4 && elements.length / 2 != 0) {
                resize(elements.length / 2);
            }

            return removed;
        }
    }

    BinaryIndexTreeMaxHeap() {
        data = new Array<>();
    }

    BinaryIndexTreeMaxHeap(E[] elements) {
        data = new Array<>(elements.length);
        for (int i = 0; i < elements.length; i++) {
            data.elements[i] = elements[i];
        }

        heapify(data.size - 1);
    }

    private void heapify(int index) {
        for (int i = parentIndex(index); i > 0; i--) {
            siftDown(i);
        }
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public boolean isEmpty() {
        return data.size() == 0;
    }

    @Override
    public void add(E e) {
        data.addLast(e);
        siftUp(data.size() - 1);
    }

    private void siftUp(int index) {
        while (index > 0 && data.get(index).compareTo(data.get(parentIndex(index))) > 0) {
            data.swap(index, parentIndex(index));
            index = parentIndex(index);
        }
    }

    @Override
    public E extractMax() {
        E max = data.get(0);
        data.swap(0, data.size() - 1);
        data.removeLast();
        siftDown(0);
        return max;
    }

    @Override
    public void replace(E e) {
        data.set(0, e);
        siftDown(0);
    }

    private void siftDown(int index) {
        while (index < data.size() && (
                (leftChildIndex(index) < data.size() && data.get(leftChildIndex(index)).compareTo(data.get(index)) > 0) ||
                        (rightChildIndex(index) < data.size() && data.get(rightChildIndex(index)).compareTo(data.get(index)) > 0)
        )) {
            int parentIndex = index;
            if (leftChildIndex(index) < data.size() && data.get(leftChildIndex(index)).compareTo(data.get(parentIndex)) > 0) {
                parentIndex = leftChildIndex(index);
            }

            if (rightChildIndex(index) < data.size() && data.get(rightChildIndex(index)).compareTo(data.get(parentIndex)) > 0) {
                parentIndex = rightChildIndex(index);
            }

            data.swap(index, parentIndex);
            index = parentIndex;
        }
    }

    // parentIndex 根据给定的索引获取父节点索引
    private int parentIndex(int index) {
        return (index - 1) / 2;
    }

    // leftChildIndex 根据给定的索引获取左子节点索引
    private int leftChildIndex(int index) {
        return 2 * index + 1;
    }

    // leftChildIndex 根据给定的索引获取右子节点索引
    private int rightChildIndex(int index) {
        return 2 * index + 2;
    }
}
