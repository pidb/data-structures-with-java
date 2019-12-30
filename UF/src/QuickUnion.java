public class QuickUnion implements UF {
    private int count;

    private int[] parent;

    QuickUnion(int size) {
        count = size;
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (qRoot == pRoot) {
            return;
        }

        parent[pRoot] = qRoot;
        count--;
    }

    // find 方法返回p对应的父亲节点
    @Override
    public int find(int p) {
        if (p < 0 || p > parent.length) {
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
