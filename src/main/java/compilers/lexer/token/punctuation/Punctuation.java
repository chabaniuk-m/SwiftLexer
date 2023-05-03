package compilers.lexer.token.punctuation;

import compilers.lexer.token.Token;

public class Punctuation extends Token {

    protected Punctuation(String type, Object value, String extra) {
        super(type, value, extra);
    }

    protected Punctuation(String type, Object value) {
        super(type, value);
    }
}
