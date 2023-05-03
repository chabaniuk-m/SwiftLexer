package compilers.lexer.token.word;

import java.util.concurrent.atomic.AtomicInteger;

public class PreprocessorDirective extends Word {

    private enum Type {
        PREPROCESSOR_DIRECTIVE,
        INVALID_PREPROCESSOR_DIRECTIVE;
    }

    private PreprocessorDirective(Type type, String value, String extra, boolean isValid) {
        super(type.toString(), value, extra, isValid);
    }

    private PreprocessorDirective validDirective(String value) {
        return new PreprocessorDirective(Type.PREPROCESSOR_DIRECTIVE, value, "", true);
    }

    private PreprocessorDirective invalidDirective(String value, String extra) {
        return new PreprocessorDirective(Type.INVALID_PREPROCESSOR_DIRECTIVE, value, extra, false);
    }

    private static void findEndOfDirective(AtomicInteger index) {
        // TODO: findEndOfDirective
        throw new UnsupportedOperationException();
    }

    /**
     * Converts code following the hash sign '#' to a PreprocessorDirective token (either valid or invalid)
     * @param hashIndex must point to '#' sign in code
     * @return not null value
     */
    public static PreprocessorDirective of(String code, AtomicInteger hashIndex) {
        // TODO: PreprocessorDirective.of()
        throw new UnsupportedOperationException();
    }
}
