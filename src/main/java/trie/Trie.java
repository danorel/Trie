package trie;

import trie.exceptions.TrieEmptyInputException;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Trie {
    private Node ROOT;

    public Trie(){
        ROOT = new Node();
    }

    public void insert(String word) throws TrieEmptyInputException {
        if(word.length() == 0){
            throw new TrieEmptyInputException();
        } else {
            HashMap<Character, Node> nodes =
                    (HashMap<Character, Node>) ROOT.nodes;
            for(int index = 0; index < word.length(); index++){
                char character = word.charAt(index);
                Node node = nodes.containsKey(character)
                        ? nodes.get(character)
                        : new Node(character);
                if(!nodes.containsValue(node)){
                    nodes.put(character, node);
                }
                nodes = (HashMap<Character, Node>)
                        Objects.requireNonNull(
                                node.nodes
                        );
                node.isWord = (index == word.length() - 1);
            }
        }
    }

    public boolean search(String word){
        HashMap<Character, Node> nodes =
                (HashMap<Character, Node>) ROOT.nodes;
        Node node = searchNode(word);
        if(node == null){
            throw new NullPointerException(
                    "Fatal error. Returning the null node with the searching phrase " + word + "!"
            );
        } else if(!node.isWord) {
            throw new NoSuchElementException(
                    "Fatal error. The input word is absent in the trie."
            );
        } else {
            return true;
        }
    }

    public boolean contains(String prefix){
        HashMap<Character, Node> nodes =
                (HashMap<Character, Node>) ROOT.nodes;
        Node node = searchNode(prefix);
        if(node == null){
            throw new NullPointerException(
                    "Fatal error. Returning the null node with the searching phrase " + prefix + "!"
            );
        } else {
            return true;
        }
    }

    public boolean remove(String word){
        HashMap<Character, Node> nodes =
                (HashMap<Character, Node>) ROOT.nodes;
        Node node = searchNode(word);
        if(node == null){
            throw new NullPointerException(
                    "Fatal error. Returning the null node with the searching phrase " + word + "!"
            );
        } else if(!node.isWord){
            throw new NoSuchElementException(
                    "Fatal error. The returning node is not the end of the word!"
            );
        } else {
            for(int to = word.length() - 1; to >= 0; to--){
                if(to > 0){
                    node = searchNode(word.substring(0, to));
                    assert node != null;
                    if(node.nodes.size() == 1){
                        node.nodes.remove(word.charAt(to));
                    } else {
                        break;
                    }
                } else {
                    ROOT.nodes.remove(word.charAt(0));
                }
            }
            return true;
        }
    }

    public boolean isEmpty(){
        return ROOT.nodes.values().size() == 0;
    }

    private Node searchNode(String prefix){
        HashMap<Character, Node> nodes =
                (HashMap<Character, Node>) ROOT.nodes;
        Node node = null;
        for(int index = 0; index < prefix.length(); index++){
            char character = prefix.charAt(index);
            if(nodes.containsKey(character)){
                node = nodes.get(character);
                nodes = (HashMap<Character, Node>) node.nodes;
            } else {
                return null;
            }
        }
        return node;
    }

    @Override
    public String toString() {
        StringBuilder library = new StringBuilder();
        HashMap<Character, Node> nodes =
                (HashMap<Character, Node>) ROOT.nodes;
        library
                .append("# - starts")
                .append("\n");
        if(nodes.values().size() == 0){
            library
                    .append("--EMPTY--")
                    .append("\n");
        } else {
            for(Node node : nodes.values()){
                library
                        .append("[")
                        .append(
                                recursiveVisualisation(node, new StringBuilder())
                        )
                        .append("]")
                        .append("\n");
            }
        }
        library
                .append("# - ends")
                .append("\n");
        return library.toString();
    }

    private String recursiveVisualisation(Node current_node, StringBuilder word){
        word
                .append(current_node.character);
        if(current_node.nodes != null){
            for(Node node : current_node.nodes.values()){
                recursiveVisualisation(node, word);
            }
        }
        return word.toString();
    }

    private class Node{
        private Map<Character, Node> nodes;
        private char character;
        private boolean isWord;

        public Node(){
            this.isWord = false;
            this.nodes = new HashMap<>();
        }

        public Node(char character){
            this.character = character;
            this.nodes = new HashMap<>();
        }
    }
}
