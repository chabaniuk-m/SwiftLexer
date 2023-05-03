package compilers.lexer.token.word;

public class Identifier extends Word {

    private enum Type {
        IDENTIFIER,
        INVALID_IDENTIFIER
    }

    private Identifier(Type type, String value, String extra, boolean isValid) {
        super(type.toString(), value, extra, isValid);
    }

    private Identifier validIdentifier(String value) {
        return new Identifier(Type.IDENTIFIER, value, "", true);
    }

    private Identifier invalidIdentifier(String value, String extra) {
        return new Identifier(Type.INVALID_IDENTIFIER, value, extra, false);
    }

    /**
     * FA that value to IDENTIFIER or INVALID_IDENTIFIER with extra information describing type of
     * problem if identifier is invalid
     * @return not null Identifier instance
     */
    public static Identifier of(String value) {
        throw new UnsupportedOperationException();
    }
}
