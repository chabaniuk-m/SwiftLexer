package compilers.lexer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Lexer {

    public static void main(String[] args) {
        /*
         * When analyzing code, always add space ' ' to the end of code (for efficiency).
         * After obtaining tokens just remove the last token
         */

        /*
         * ERROR PROCESSING
         * () {} []
         *
         * /regex/
         *
         * unknown identifier - not a problem
         *
         * invalid expression: 8 int =; - not a problem
         */


    }
}
