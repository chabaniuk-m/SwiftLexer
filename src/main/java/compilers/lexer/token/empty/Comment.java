package compilers.lexer.token.empty;

import java.util.concurrent.atomic.AtomicInteger;

public class Comment extends Empty {

    private enum Type {
        SINGLE_LINE_COMMENT,
        MULTILINE_COMMENT,
        INVALID_MULTILINE_COMMENT;
    }

    private Comment(Type type, String value, String extra, boolean isValid) {
        super(type.toString(), value, extra, isValid);
    }

    private Comment valueOf(Type type, String value) {
        return switch (type) {
            case SINGLE_LINE_COMMENT -> new Comment(type, value, "", true);
            case MULTILINE_COMMENT -> new Comment(type, "\n" + value, "", true);
            case INVALID_MULTILINE_COMMENT ->
                new Comment(type, "\n" + value, "missing closing part \"*/\"", false);
        };
    }

    /**
     * Converts text starting at beginIndex to a proper Comment
     * @param beginIndex must point to the beginning of the comment (either '//' or '/*')
     * @return not null Comment instance
     */
    public static Comment of(String code, AtomicInteger beginIndex) {
        // ignore last space if multiline comment is invalid
        // TODO: Comment.of()
        throw new UnsupportedOperationException();
    }
}
