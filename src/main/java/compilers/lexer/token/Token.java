package compilers.lexer.token;

public abstract class Token {

    protected final String type;
    protected final Object value;
    protected final String extra;

    public Token(String type, Object value, String extra) {
        this.type = type;
        this.value = value;
        this.extra = extra;
    }

    @Override
    public String toString() {
        return String.format("%s: %s%s", type, value.toString(), extra == null || extra.isEmpty() ? "" : " (" + extra + ")");
    }
}
