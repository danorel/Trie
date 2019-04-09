package trie.exceptions;

public class TrieEmptyInputException extends Exception {
    private static final StringBuilder EMPTY_INPUT =
            new StringBuilder(
                    "Fatal error. The input string is empty!"
            );

    public TrieEmptyInputException(){
        super(EMPTY_INPUT.toString());
    }
}
