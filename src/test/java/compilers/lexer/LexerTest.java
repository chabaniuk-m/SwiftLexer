package compilers.lexer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LexerTest {
    protected static final String RESOURCE_DIRECTORY = "src/test/resources/";

    /**
     * Reads content of file filename (without extension) in the directory
     * RESOURCE_DIRECTORY as a string, appending 1 space to it.
     * Function automatically adds .txt extension to filename
     * @param filename file relative path in test resource folder without .txt extension
     * @return content of file with space character appended to the end
     */
    protected static String readTestData(String filename) {
        try {
            return Files.readString(Path.of(RESOURCE_DIRECTORY, filename + ".txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected static void printTitle(String title) {
        System.out.println("\n<<<" + title + " test>>>\n");
    }
}