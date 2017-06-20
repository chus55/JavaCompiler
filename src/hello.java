import Lexer.Lexer;
import Lexer.Token;
import Lexer.LexerException;
import Parser.Parser;
import Semantic.SemanticException;
import Semantic.Tree.Statement.StatementNode;

import java.util.List;

/**
 * Created by chusm on 6/18/2017.
 */
public class hello {
    public static void main(String[] args) throws LexerException, SemanticException {
        testSemantic();
    }

    static void testLexer() throws LexerException {
        Lexer miLexer = new Lexer("int a = 10;\nprint(a);");
        List<Token> tokenList = miLexer.GetTokenList();
        for (Token token : tokenList) {
            System.out.println("Lexeme: " + token.Lexeme + ", Type: " + token.Type + ", Line: " + token.Line + ", Column: " + token.Column);
        }
    }

    static void testParser() throws LexerException {
        Lexer miLexer = new Lexer("int a = 10;\nprint(a);\na = 20;");
        List<Token> tokenList = miLexer.GetTokenList();
        Parser miParser = new Parser(tokenList);
        miParser.Parse();
        System.out.println("Parser success");
    }

    static void testArbol() throws LexerException {
        Lexer miLexer = new Lexer("int a = 10;\nprint(a);\na = 20;");
        List<Token> tokenList = miLexer.GetTokenList();
        Parser miParser = new Parser(tokenList);
        List<StatementNode> parsedCode = miParser.Parse();
        for (StatementNode statement : parsedCode) {
            System.out.println(statement);
        }
    }

    static void testSemantic() throws LexerException, SemanticException {
        Lexer miLexer = new Lexer("int a = 10;\nprint(a);\na = 20;");
        List<Token> tokenList = miLexer.GetTokenList();
        Parser miParser = new Parser(tokenList);
        List<StatementNode> parsedCode = miParser.Parse();
        for (StatementNode statement : parsedCode) {
            statement.ValidateSemantic();
        }
        System.out.println("Semantic success");
    }
}