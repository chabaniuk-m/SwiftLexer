package compilers.lexer.token.word;

import compilers.lexer.token.Token;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Common class for Keyword & Identifier
 */
public abstract class Word extends Token {

    protected Word(String type, Object value, String extra, boolean isValid) {
        super(type, value, extra, isValid);
    }

    protected Word(String type, Object value, String extra) {
        super(type, value, extra);
    }

    /**
     * Sets index to the symbol next to the last symbol of the word
     * @param index any position in word, should not point to a non-word symbol
     */
    private static void findEndOfTheWord(String code, AtomicInteger index) {
        // TODO Keyword.findEndOfTheWord
        throw new UnsupportedOperationException();
    }
}
