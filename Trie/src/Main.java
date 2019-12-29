public class Main {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.add("do");
        trie.add("ao");
        trie.add("ab");
        trie.add("ac");
        trie.add("ad");
        trie.add("battle");
        trie.add("apple");
        System.out.println(trie.size());
        System.out.println(trie.isPrefix("bp"));
        System.out.println(trie.contains("do"));
        System.out.println(trie.contains("ao"));
        System.out.println(trie.contains("ab"));
        System.out.println(trie.contains("ac"));
        System.out.println(trie.contains("ad"));
        System.out.println(trie.contains("battle"));
        System.out.println(trie.contains("apple"));
        System.out.println(trie.contains("apples"));

    }
}
