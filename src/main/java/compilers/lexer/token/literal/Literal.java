package compilers.lexer.token.literal;

import compilers.lexer.token.Token;

public abstract class Literal extends Token {

    protected Literal(String type, Object value, String extra, boolean isValid) {
        super(type, value, extra, isValid);
    }

    protected Literal(String type, Object value, String extra) {
        super(type, value, extra, true);
    }

    protected Literal(String type, Object value) {
        super(type, value, "", true);
    }
}
