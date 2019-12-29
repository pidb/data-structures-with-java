public class Trie {
    static private class Node {
        public boolean isWord;

        public Map<Character, Node> next;

        Node(boolean isWord, Map<Character, Node> next) {
            this.isWord = isWord;
            this.next = next;
        }

        Node() {
            this(false, null);
        }
    }

    private int size;

    private Node root;

    Trie() {
        this.size = 0;
        this.root = new Node(false, new BinarySearchTreeMap<Character, Node>());
    }

    public int size() {
        return size;
    }

    // add 向Trie中添加元素
    public void add(String word) {
        if (word.isEmpty()) {
            return;
        }

        recursiveAdd(root, word, 0);
    }

    // addNoRecursive 非递归的添加字符串索引处的元素到trie
    private void addNoRecursive(String word) {
        Node cur = root;
        for (int i = 0, n = word.length(); i < n; i++) {
            if (cur.next.get(word.charAt(i)) == null) {
                cur.next.put(word.charAt(i), new Node(false, new BinarySearchTreeMap<Character, Node>()));
            }
            cur = cur.next.get(word.charAt(i));
        }
        cur.isWord = true;
        size++;
    }

    // recursiveAdd  递归的添加字符串索引处的元素到trie
    private void recursiveAdd(Node root, String word, int index) {
        if (index == word.length() - 1) {
            Node node = root.next.get(word.charAt(index));
            if (node == null) {
                root.next.put(word.charAt(index), new Node(true, new BinarySearchTreeMap<Character, Node>()));
            } else {
                node.isWord = true;
            }

            size++;

        } else {
            if (root.next.get(word.charAt(index)) == null) {
                root.next.put(word.charAt(index), new Node(false, new BinarySearchTreeMap<Character, Node>()));
            }

            recursiveAdd(root.next.get(word.charAt(index)), word, index + 1);
        }
    }

    // contains 查询trie中是否存在字符串
    public boolean contains(String word) {
        if (word.isEmpty()) {
            return false;
        }

        return containsNoRecursive(word);
    }

    // recursiveContains 递归的查找trie中是否存在字符串index处的元素 返回boolean值
    private boolean recursiveContains(Node root, String word, int index) {
        if (index < word.length()) {
            Node find = root.next.get(word.charAt(index));
            if (find == null) {
                return false;
            }

            if (index == word.length() - 1 && find.isWord) {
                return true;
            }

            return recursiveContains(find, word, index + 1);
        }

        return false;
    }

    // containsNoRecursive 非递归的查找trie中是否存在字符串
    private boolean containsNoRecursive(String word) {
        Node cur = root;
        for (int i = 0, n = word.length(); i < n; i++) {
            if (cur.next.get(word.charAt(i)) == null) {
                return false;
            }
            cur = cur.next.get(word.charAt(i));
        }

        return cur.isWord;
    }

    // isPrefix 查询trie中的字符串是否包含prefix
    public boolean isPrefix(String prefix) {
        if (prefix.isEmpty()) {
            return false;
        }

        return findPrefixNoRecursive(prefix);
    }

    // recursiveFindPrefix 递归查询trie中的字符串是否包含prefix对应index处的char 返回boolean值
    public boolean recursiveFindPrefix(Node root, String prefix, int index) {
        if (index >= prefix.length()) {
            return false;
        }

        Node node = root.next.get(prefix.charAt(index));
        if (node == null) {
            return false;
        }

        if (index == prefix.length() - 1) {
            return true;
        }

        return recursiveFindPrefix(root.next.get(prefix.charAt(index)), prefix, index + 1);
    }

    // findPrefixNoRecursive 非递归的查询trie中的字符串是否包含prefix
    public boolean findPrefixNoRecursive(String prefix) {
        Node cur = root;
        for (int i = 0, n = prefix.length(); i < n; i++) {
            if (root.next.get(prefix.charAt(i)) == null) {
                return false;
            }
            cur = root.next.get(prefix.charAt(i));
        }

        return true;
    }
}
