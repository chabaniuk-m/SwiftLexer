package compilers.lexer.token.empty;

import java.util.concurrent.atomic.AtomicInteger;

public class Comment extends Empty {

    private enum Type {
        SINGLE_LINE_COMMENT,
        MULTILINE_COMMENT,
        INVALID_MULTILINE_COMMENT
    }

    private Comment(Type type, String value, String extra, boolean isValid) {
        super(type.toString(), value, extra, isValid);
    }

    private static Comment valueOf(Type type, String value) {
        return switch (type) {
            case SINGLE_LINE_COMMENT -> new Comment(type, value, "", true);
            case MULTILINE_COMMENT -> new Comment(type, "\n" + value, "", true);
            case INVALID_MULTILINE_COMMENT ->
                new Comment(type, "\n" + value, "missing closing part \"*/\"", false);
        };
    }

    /**
     * Converts text starting at beginIndex to a proper Comment
     * @param beginIndex must point to the '/' sign
     * @return Comment instance if text is comment, null otherwise
     */
    public static Comment of(String code, AtomicInteger beginIndex) {
        int index = beginIndex.get();
        int begin = index;
        if (code.charAt(index) != '/') {
            throw new RuntimeException("beginIndex must point to the '/' character");
        }
        char c = code.charAt(++index);
        int lastIndex = code.length() - 1;
        switch (c) {
            case '/' -> {           // single line comment
                boolean isEndOfCode = false;
                do {
                    if (c == ' ' && index == lastIndex) {
                        ++index;
                        isEndOfCode = true;
                        break;
                    }
                } while ((c = code.charAt(++index)) != '\n');

                beginIndex.set(index);
                if (isEndOfCode)
                    --index;
                return Comment.valueOf(Type.SINGLE_LINE_COMMENT, code.substring(begin, index));
            }
            case '*' -> {           // multiline comment
                boolean isPrevStar;
                boolean isValid = true;
                ++index;
                do {
                    if (c == ' ' && index == lastIndex) {
                        isValid = false;
                        break;
                    }
                    isPrevStar = c == '*';
                    c = code.charAt(++index);
                } while (!(isPrevStar && c == '/'));
                ++index;
                beginIndex.set(index);
                if (!isValid) --index;
                return isValid ?
                        Comment.valueOf(Type.MULTILINE_COMMENT, code.substring(begin, index)) :
                        Comment.valueOf(Type.INVALID_MULTILINE_COMMENT, code.substring(begin, index));
            }
            default -> {
                return null;
            }
        }
    }
}
