package compilers.lexer.token.word;

import java.util.concurrent.atomic.AtomicInteger;

public class Keyword extends Word {

    private Keyword(String value, String extra) {
        super("KEYWORD", value, extra);
    }

    /**
     * Finite Automata that convert word to Keyword instance.
     * Always set beginIndex to the next symbol after end of the word
     * @param beginIndex beginning of the word
     * @return Keyword instance or null
     */
    public static Keyword of(String code, AtomicInteger beginIndex) {

        String keyword = null;
        String extra = null;

        int index = beginIndex.get();

        // code is always ends with ' '
        switch (code.charAt(index)) {
            // TODO: Keyword.of()
        }

        return keyword == null ? null : new Keyword(keyword, extra);
    }
}
