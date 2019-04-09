import trie.Trie;
import trie.exceptions.TrieEmptyInputException;

public class App {
    public static void main(String[] args) {
        Trie trie = new Trie();

        try {
            trie.insert("hello");
            trie.insert("world");
        } catch (TrieEmptyInputException exception) {
            exception.printStackTrace();
        }

        System.out.print(trie);

        System.out.println(trie.search("world"));
        System.out.println(trie.contains("wor"));

        System.out.println(trie.remove("hello"));
        System.out.println(trie);

    }
}
