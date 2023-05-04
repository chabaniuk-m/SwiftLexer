package compilers.lexer.token.empty;

import compilers.lexer.LexerTest;
import compilers.lexer.token.Token;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

public class EmptyTest extends LexerTest {

    @Test
    public void testSingleWhitespaceCharacter() {
        // space
        String code = " ";
        AtomicInteger index = new AtomicInteger(0);
        Token token = Whitespace.of(code, index);
        assertEquals(new Token("WHITESPACE_SYMBOL", "'\\s'", "space", true),
                token, "Incorrect token obtained");
        assertEquals(index.get(), 1, "expected function to increase an index");
        System.out.println(token);

        // tab
        code = "\t8";
        index.set(0);
        token = Whitespace.of(code, index);
        assertEquals(new Token("WHITESPACE_SYMBOL", "'\\t'", "tab", true),
                token, "Incorrect token obtained");
        assertEquals(index.get(), 1, "expected function to increase an index");
        System.out.println(token);

        // carriage return
        code = "\rvar t = 8";
        index.set(0);
        token = Whitespace.of(code, index);
        assertEquals(new Token("WHITESPACE_SYMBOL", "'\\r'", "carriage return", true),
                token, "Incorrect token obtained");
        assertEquals(index.get(), 1, "expected function to increase an index");
        System.out.println(token);

        // form feed
        code = "\fok";
        index.set(0);
        token = Whitespace.of(code, index);
        assertEquals(new Token("WHITESPACE_SYMBOL", "'\\f'", "form feed", true),
                token, "Incorrect token obtained");
        assertEquals(index.get(), 1, "expected function to increase an index");
        System.out.println(token);

        //vertical tab
        code = "" + (char)11 + "let ";
        index.set(0);
        token = Whitespace.of(code, index);
        assertEquals(new Token("WHITESPACE_SYMBOL", "'\\v'", "vertical tab", true),
                token, "Incorrect token obtained");
        assertEquals(index.get(), 1, "expected function to increase an index");
        System.out.println(token);
    }

    @Test
    public void testCombinedWhitespaceSequence() {
        String code = readTestData("empty/test1");
        System.out.println("<" + code + ">");
    }
}