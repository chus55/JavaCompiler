package Lexer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chusm on 6/18/2017.
 */
public class Lexer {
    private String sourceCode;
    private int pointer;
    private int line;
    private int column;
    private LexerDictionaries lexerDics;

    public Lexer(String SourceCode)
    {
        sourceCode = SourceCode;
        pointer = 0;
        line = 1;
        column = 1;
        lexerDics = new LexerDictionaries();
    }

    private char GetCurrentSymbol()
    {
        if (pointer < sourceCode.length())
        {
            return sourceCode.charAt(pointer);
        }
        return '\0';
    }

    public List<Token> GetTokenList() throws LexerException {
        List<Token> tokenList = new ArrayList<Token>();

        Token currentToken = GetNextToken();
        while (currentToken.Type != TokenTypes.EOF)
        {
            tokenList.add(currentToken);
            currentToken = GetNextToken();
        }
        tokenList.add(currentToken);
        return tokenList;
    }

    private Token GetNextToken() throws LexerException {
        char currentSymbol = GetCurrentSymbol();

        currentSymbol = ConsumeWhiteSpace(currentSymbol);

        if (CheckIfEndOfFile(currentSymbol))
            return new Token (TokenTypes.EOF);

        if (CheckIfIdOrReservedWord(currentSymbol))
            return GetIdOrReservedWord(Character.toString(currentSymbol));

        if (CheckIfNumber(currentSymbol))
            return GetNumber(Character.toString(currentSymbol));

        if (CheckIfSymbol(currentSymbol))
            return GetSymbol(currentSymbol);

        throw new LexerException("No acceptable token found at line " + line + ", column " + column + ".");
    }

    private Token GetSymbol(char currentSymbol)
    {
        SumToPointerAndColumn(1);
        return new Token (lexerDics.Symbols.get(Character.toString(currentSymbol)), Character.toString(currentSymbol), line, (column - (Character.toString(currentSymbol).length())));
    }

    private boolean CheckIfSymbol(char currentSymbol)
    {
        return lexerDics.Symbols.containsKey(Character.toString(currentSymbol));
    }

    private Token GetNumber(String lexeme) {
        char currentSymbol = GetCurrentSymbol();
        while(Character.isDigit(currentSymbol)){
            lexeme += currentSymbol;
            SumToPointerAndColumn(1);
            currentSymbol = GetCurrentSymbol();
        }
        return new Token(TokenTypes.LIT_INT, lexeme, line, (column - (lexeme.length())));
    }

    private boolean CheckIfNumber(char currentSymbol)
    {
        if (Character.isDigit(currentSymbol))
        {
            SumToPointerAndColumn(1);
            return true;
        }
        return false;
    }

    private Token GetIdOrReservedWord(String lexeme)
    {
        char currentSymbol = GetCurrentSymbol();

        while (Character.isLetterOrDigit(currentSymbol) || currentSymbol == '_')
        {
            lexeme += currentSymbol;
            SumToPointerAndColumn(1);
            currentSymbol = GetCurrentSymbol();
        }

        return new Token (lexerDics.ReservedWords.containsKey(lexeme) ? lexerDics.ReservedWords.get(lexeme) : TokenTypes.ID,
                            lexeme,
                            line,
                            (column - (lexeme.length())));
    }

    private boolean CheckIfIdOrReservedWord(char currentSymbol)
    {
        if (Character.isLetter(currentSymbol) || currentSymbol == '_')
        {
            SumToPointerAndColumn(1);
            return true;
        }
        return false;
    }

    private boolean CheckIfEndOfFile(char currentSymbol) {
        return currentSymbol == '\0';
    }

    private char ConsumeWhiteSpace(char currentSymbol) {
        while (Character.isWhitespace(currentSymbol))
        {
            SumToPointerAndColumn(1);
            if (currentSymbol == '\n')
            {
                line++;
                column = 1;
            }
            currentSymbol = GetCurrentSymbol();
        }
        return currentSymbol;
    }

    private void SumToPointerAndColumn(int quantity)
    {
        pointer += quantity;
        column += quantity;
    }
}
