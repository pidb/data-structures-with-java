public class QuickUnionSizeWeighted implements UF {
    private int count;
    private int[] size, parent;

    QuickUnionSizeWeighted(int size) {
        count = size;
        this.size = new int[size];
        this.parent = new int[size];
        for (int i = 0; i < size; i++) {
            this.size[i] = 1;
            this.parent[i] = i;
        }
    }

    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }

        if (size[pRoot] < size[qRoot]) {
            parent[pRoot] = qRoot;
            size[qRoot] += size[pRoot];
        } else {
            parent[qRoot] = pRoot;
            size[pRoot] += size[qRoot];
        }
        count--;
    }

    @Override
    public int find(int p) {
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("invalid p");
        }

        while (p != parent[p]) p = parent[p];
        return p;
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
