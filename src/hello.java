import Lexer.Lexer;
import Lexer.Token;
import Lexer.LexerException;
import Parser.Parser;

import java.util.List;

/**
 * Created by chusm on 6/18/2017.
 */
public class hello {
    public static void main(String[] args) throws LexerException {
        testParser();
    }

    static void testLexer() throws LexerException {
        Lexer miLexer = new Lexer("int a = 10;\nprint(a);");
        List<Token> tokenList = miLexer.GetTokenList();
        for (Token token : tokenList) {
            System.out.println("Lexeme: " + token.Lexeme + ", Type: " + token.Type + ", Line: " + token.Line + ", Column: " + token.Column);
        }
    }

    static void testParser() throws LexerException {
        Lexer miLexer = new Lexer("int a = 10;\nprint(a);");
        List<Token> tokenList = miLexer.GetTokenList();
        Parser miParser = new Parser(tokenList);
        miParser.Parse();
    }
}