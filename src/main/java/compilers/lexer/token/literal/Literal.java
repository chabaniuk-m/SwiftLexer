package compilers.lexer.token.literal;

import compilers.lexer.token.Token;

public abstract class Literal extends Token {

    public Literal(String type, Object value, String extra, boolean isValid) {
        super(type, value, extra, isValid);
    }

    public Literal(String type, Object value, String extra) {
        super(type, value, extra, true);
    }

    public Literal(String type, Object value) {
        super(type, value, "", true);
    }
}
