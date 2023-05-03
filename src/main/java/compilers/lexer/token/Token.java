package compilers.lexer.token;

/**
 * Class that describes general token instance.
 * Contains its type, value (must be always provided)
 * and marks whether token is valid or not (appropriate type should be provided)
 * Additional has extra information that can widely describe the type
 * or explain the king of error which forced token to be invalid.
 * Extra information can be provided or not.
 */
public abstract class Token {

    /**
     * String that describes the type of token (INTEGER_CONSTANT, INVALID_IDENTIFIER, KEYWORD...)
     */
    protected final String type;
    /**
     * value of the token
     */
    protected final Object value;
    /**
     * Extra information about token. Provides to additional describe the type of
     * token or explain why token is invalid
     */
    protected final String extra;
    /**
     * Marks whether token is valid or not
     */
    protected final boolean isValid;

    protected Token(String type, Object value, String extra, boolean isValid) {
        this.type = type;
        this.value = value;
        this.extra = extra;
        this.isValid = isValid;
    }

    protected Token(String type, Object value, String extra) {
        this(type, value, extra, true);
    }

    protected Token(String type, Object value) {
        this(type, value, "", true);
    }

    /**
     * Tell whether token is valid or not.
     * If token is invalid, extra information is usually provided
     */
    public boolean isValid() {
        return isValid;
    }

    @Override
    public String toString() {
        return String.format("%s: %s%s", type, value.toString(), extra == null || extra.isEmpty() ? "" : " (" + extra + ")");
    }
}
