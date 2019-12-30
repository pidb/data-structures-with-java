public class QuickFind implements UF {
    private int[] id;

    private int count;

    QuickFind(int size) {
        count = size;
        id = new int[size];
        for (int i = 0; i < size; i++) {
            id[i] = i;
        }
    }

    @Override
    public void union(int p, int q) {
        if (find(p) == find(q)) {
            return;
        }

        for (int i = 0, n = id.length; i < n; i++) {
            if (id[i] == id[p]) {
                id[i] = id[q];
            }
        }
        count--;
    }

    @Override
    public int find(int p) {
        if (p < 0 || p >= id.length) {
            throw new IllegalArgumentException("invalid p");
        }
        return id[p];
    }

    @Override
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public int count() {
        return count;
    }
}
