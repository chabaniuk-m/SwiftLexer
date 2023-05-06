package compilers.lexer.token.empty;

import compilers.lexer.LexerTest;
import compilers.lexer.token.Token;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

public class CommentTest extends LexerTest {

    private void checkSingleLine(String code, int idx) {
        AtomicInteger index = new AtomicInteger(0);
        Token token = Comment.of(code, index);
        assertNotNull(token);
        assertEquals(idx, index.get());
        assertEquals(String.format("SINGLE_LINE_COMMENT: %s", code.substring(0, code.length() - 1)),
                     token.toString(),
                     "invalid token processing");
        System.out.println(token);
    }
    @Test
    public void singleLineComment() {
        printTitle("single line comment");
        String[] comments = readTestData("empty/comment-single-line").split("\\r?\\n|\\r");
        for (var comment : comments) {
            checkSingleLine(comment + " ", comment.length() + 1);
        }
        String code = "let x = 8;\n\n// Just a comment that ends with a line feed\nvar arr = [8, 3];";
        int begin = code.indexOf('/');
        AtomicInteger index = new AtomicInteger(begin);
        Token token = Comment.of(code, index);
        assertNotNull(token);
        assertEquals(code.lastIndexOf('\n'), index.get());
        assertEquals(String.format("SINGLE_LINE_COMMENT: %s", code.substring(begin, index.get())),
                token.toString(),
                "invalid token processing");
        System.out.println(token);
    }

    private void checkMultilineCorrect(String code) {
        AtomicInteger index = new AtomicInteger(0);
        Token token = Comment.of(code, index);
        assertNotNull(token);
        assertEquals(String.format("MULTILINE_COMMENT: \n%s", code),
                     token.toString(),
                     "incorrect token processing");
        assertEquals(code.length(), index.get());
        System.out.println(token);
    }

    @Test
    public void multilineCorrectComment() {
        printTitle("multiline correct comment");
        String[] comments = readTestData("empty/comment-correct-multiline").split("#");
        for (var comment : comments) {
            checkMultilineCorrect(comment);
        }
    }

    private void checkMultilineIncorrect(String code) {
        AtomicInteger index = new AtomicInteger(0);
        Token token = Comment.of(code, index);
        assertNotNull(token);
        assertEquals(code.length(), index.get());
        assertEquals(String.format("INVALID_MULTILINE_COMMENT: \n%s (missing closing part \"*/\")",
                        code.substring(0, code.length() - 1)),
                     token.toString(),
                     "incorrect token processing");
        System.out.println(token);
    }

    @Test
    public void multilineIncorrectComment() {
        printTitle("multiline incorrect comment");
        String[] comments = readTestData("empty/comment-incorrect-multiline").split("#");
        for (var comment : comments) {
            checkMultilineIncorrect(comment + ' ');
        }
    }

    @Test
    public void practicalTest() {
        printTitle("practical comment test");
        String code = """
                import Foundation;
                
                /*
                 * 5! = 5 * 4!
                 * 4! = 4 * 3!
                 * 3! = 3 * 2!
                 * 2! = 2 * 1!
                 * 1! = 1 * 0!
                 * 0! = 1
                 */
                 
                // calculates factorial of a positive number n
                // recursive implementation
                func factorial(_ n: UInt) -> UInt64 {
                    if n == 0 {
                        return 0
                    }
                    return n * factorial(n - 1)
                }
                
                /* just
                an invalid
                comment
                at the end
                of the file ***""" + ' ';

        // first comment (multiline)
        int begin = code.indexOf("/*");
        AtomicInteger index = new AtomicInteger(begin);
        int end = code.indexOf("*/") + 2;
        Token token = Comment.of(code, index);
        assertNotNull(token);
        assertEquals(end, index.get());
        assertEquals(String.format("MULTILINE_COMMENT: \n%s", code.substring(begin, end)),
                     token.toString(),
                     "incorrect token processing");
        System.out.println(token);

        // second comment (single line)
        begin = code.indexOf("//");
        index = new AtomicInteger(begin);
        end = code.indexOf("n\n//") + 1;
        token = Comment.of(code, index);
        assertNotNull(token);
        assertEquals(end, index.get());
        assertEquals(String.format("SINGLE_LINE_COMMENT: %s", code.substring(begin, end)),
                token.toString(),
                "incorrect token processing");
        System.out.println(token);

        // third comment (single line)
        begin = code.lastIndexOf("//");
        index = new AtomicInteger(begin);
        end = code.indexOf("n\nfunc") + 1;
        token = Comment.of(code, index);
        assertNotNull(token);
        assertEquals(end, index.get());
        assertEquals(String.format("SINGLE_LINE_COMMENT: %s", code.substring(begin, end)),
                token.toString(),
                "incorrect token processing");
        System.out.println(token);

        // fourth comment (multiline incorrect)
        begin = code.lastIndexOf("/*");
        index = new AtomicInteger(begin);
        token = Comment.of(code, index);
        assertNotNull(token);
        assertEquals(code.length(), index.get());
        assertEquals(String.format("INVALID_MULTILINE_COMMENT: \n%s (missing closing part \"*/\")",
                        code.substring(begin, code.length() - 1)),
                token.toString(),
                "incorrect token processing");
        System.out.println(token);
    }
}