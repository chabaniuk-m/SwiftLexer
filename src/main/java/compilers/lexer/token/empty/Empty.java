package compilers.lexer.token.empty;

import compilers.lexer.token.Token;

public abstract class Empty extends Token {

    protected Empty(String type, Object value, String extra, boolean isValid) {
        super(type, value, extra, isValid);
    }

    protected Empty(String type, Object value, String extra) {
        super(type, value, extra);
    }
}
