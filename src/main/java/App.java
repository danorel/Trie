import trie.Trie;

public class App {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("hello");
        trie.insert("world");
        System.out.print(trie);
        System.out.println(trie.search("world"));
        System.out.println(trie.startsWith("wor"));
    }
}
