package compilers.lexer.token.empty;

import compilers.lexer.LexerTest;
import compilers.lexer.token.Token;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

public class WhitespaceTest extends LexerTest {

    @Test
    public void singleWhitespaceCharacter() {
        printTitle("single whitespace character");
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
    public void sequenceOfWhiteSpaceCharacters() {
        printTitle("sequence of whitespace characters");
        // three spaces
        String code = "   ";
        AtomicInteger index = new AtomicInteger(0);
        Token token = Whitespace.of(code, index);
        assertEquals(new Token("WHITESPACE_SEQUENCE", "\"\\s\\s\\s\"", "", true),
                token, "incorrect whitespace sequence processing");
        assertEquals(3, index.get(), "function must properly increase the index");
        System.out.println(token);

        // combined
        code = "let \t\f\r  \t x: Int ";
        index.set(code.indexOf(' '));
        token = Whitespace.of(code, index);
        assertEquals(new Token("WHITESPACE_SEQUENCE", "\"\\s\\t\\f\\r\\s\\s\\t\\s\"", "", true),
                token, "incorrect whitespace sequence processing");
        assertEquals(code.indexOf('x'), index.get(), "function must properly increase the index");
        System.out.println(token);

        // combined till end
        code = "return 1;\t\t\r" + (char)11 + "  \f ";
        index.set(code.indexOf(';') + 1);

        token = Whitespace.of(code, index);
        assertEquals(new Token("WHITESPACE_SEQUENCE", "\"\\t\\t\\r\\v\\s\\s\\f\\s\"", "", true),
                token, "incorrect whitespace sequence processing");
        assertEquals(code.length(), index.get(), "function must properly increase the index");
        System.out.println(token);
    }

    @Test
    public void cloneWithoutLastSpace() {
        printTitle("clone without last space");
        // single space
        String code = " ";
        Token clone;
        AtomicInteger index = new AtomicInteger(0);
        Whitespace token = Whitespace.of(code, index);
        assertNotNull(token);
        assertNull(token.cloneWithoutLastSpace(), "single whitespace must convert into null");
        System.out.println("null");

        // 2 symbols
        code = "\t ";
        index.set(0);
        token = Whitespace.of(code, index);
        assertNotNull(token);
        assertEquals("WHITESPACE_SYMBOL: '\\t' (tab)",
                      (clone = token.cloneWithoutLastSpace()).toString(),
                     "must remove only last space");
        System.out.println(clone);

        // 2 symbols
        code = "\f ";
        index.set(0);
        token = Whitespace.of(code, index);
        assertNotNull(token);
        assertEquals("WHITESPACE_SYMBOL: '\\f' (form feed)",
                      (clone = token.cloneWithoutLastSpace()).toString(),
                     "must remove only last space");
        System.out.println(clone);

        // 2 symbols
        code = "\r ";
        index.set(0);
        token = Whitespace.of(code, index);
        assertNotNull(token);
        assertEquals("WHITESPACE_SYMBOL: '\\r' (carriage return)",
                      (clone = token.cloneWithoutLastSpace()).toString(),
                     "must remove only last space");
        System.out.println(clone);

        // 2 symbols
        code = (char)11 + " ";
        index.set(0);
        token = Whitespace.of(code, index);
        assertNotNull(token);
        assertEquals("WHITESPACE_SYMBOL: '\\v' (vertical tab)",
                      (clone = token.cloneWithoutLastSpace()).toString(),
                     "must remove only last space");
        System.out.println(clone);

        // 2 symbols
        code = "  ";
        index.set(0);
        token = Whitespace.of(code, index);
        assertNotNull(token);
        assertEquals("WHITESPACE_SYMBOL: '\\s' (space)",
                      (clone = token.cloneWithoutLastSpace()).toString(),
                     "must remove only last space");
        System.out.println(clone);

        // several symbols
        code = "let x \t\f\r\t ";
        index.set(code.indexOf('x') + 1);
        token = Whitespace.of(code, index);
        assertNotNull(token);
        assertEquals("WHITESPACE_SEQUENCE: \"\\s\\t\\f\\r\\t\"",
                     (clone = token.cloneWithoutLastSpace()).toString(),
                     "must remove only last space");
        System.out.println(clone);

        // several symbols with preceding space
        code = " \t \f\r" + (char)11 + "\t  ";
        index.set(0);
        token = Whitespace.of(code, index);
        assertNotNull(token);
        assertEquals("WHITESPACE_SEQUENCE: \"\\s\\t\\s\\f\\r\\v\\t\\s\"",
                     (clone = token.cloneWithoutLastSpace()).toString(),
                     "must remove only last space");
        System.out.println(clone);
    }
}