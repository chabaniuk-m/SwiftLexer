package compilers.lexer.token;

public abstract class Token {

    protected final String type;
    protected final Object value;
    protected final String extra;
    protected final boolean isValid;

    public Token(String type, Object value, String extra, boolean isValid) {
        this.type = type;
        this.value = value;
        this.extra = extra;
        this.isValid = isValid;
    }

    public Token(String type, Object value, String extra) {
        this(type, value, extra, true);
    }

    public Token(String type, Object value) {
        this(type, value, "", true);
    }

    public boolean isValid() {
        return isValid;
    }

    @Override
    public String toString() {
        return String.format("%s: %s%s", type, value.toString(), extra == null || extra.isEmpty() ? "" : " (" + extra + ")");
    }
}
