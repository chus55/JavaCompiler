package Lexer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

        if (CheckIfString(currentSymbol))
            return GetString();

        if (CheckIfDoubleSymbol(currentSymbol))
            return GetDoubleSymbol(currentSymbol);

        if (CheckIfSymbol(currentSymbol))
            return GetSymbol(currentSymbol);

        throw new LexerException("No acceptable token found at line " + line + ", column " + column + ".");
    }

    private Token GetString() throws LexerException {
        int tmpLine = line;
        int tmpColumn = column;
        String lexeme = Character.toString(GetCurrentSymbol());
        SumToPointerAndColumn(1);
        char currentSymbol = GetCurrentSymbol();
        while (currentSymbol != '\"')
        {
            switch (currentSymbol)
            {
                case '\0':
                    throw new LexerException("Reached end of file and did not close string starting at line " + tmpLine + ", column " + tmpColumn + ".");
                case '\n':
                    throw new LexerException("String can't contain new line at line " + line + ", column " + column + ".");
                /*case '\\':
                    String escapeCharCheck = Character.toString(currentSymbol) + GetSymbolAheadBy(1);
                    lexeme += currentSymbol + GetSymbolAheadBy(1);
                    SumToPointerAndColumn(2);
                    if (!lexerDics.EscapeChars.Contains(escapeCharCheck))
                        throw new LexerException("Invalid escape char at line " + line + ", column " + (column - 2) + ".");
                    break;*/
                default:
                    lexeme += currentSymbol;
                    SumToPointerAndColumn(1);
                    break;
            }
            currentSymbol = GetCurrentSymbol();
        }
        lexeme += currentSymbol;
        SumToPointerAndColumn(1);
        return new Token (TokenTypes.LIT_STRING, lexeme, line, (column - (lexeme.length())));
    }

    private boolean CheckIfString(char currentSymbol)
    {
        return currentSymbol == '\"';
    }

    private Token GetDoubleSymbol(char currentSymbol)
    {
        char secondCurrentSymbol = GetSymbolAheadBy(1);
        SumToPointerAndColumn(2);
        String newLexeme = Character.toString(currentSymbol) + secondCurrentSymbol;
        return new Token (lexerDics.DoubleSymbols.get(newLexeme),newLexeme, line, (column - newLexeme.length() - 2));
    }

    private boolean CheckIfDoubleSymbol(char currentSymbol)
    {
        char secondCurrentSymbol = GetSymbolAheadBy(1);
        return lexerDics.DoubleSymbols.containsKey(Character.toString(currentSymbol) + secondCurrentSymbol);
    }

    private char GetSymbolAheadBy(int quantity)
    {
        SumToPointerAndColumn(quantity);
        char currentSymbol = GetCurrentSymbol();
        SubFromPointerAndColumn(quantity);
        return currentSymbol;
    }

    private void SubFromPointerAndColumn(int quantity)
    {
        pointer -= quantity;
        column -= quantity;
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

        if (Objects.equals(lexeme, "false") || Objects.equals(lexeme, "true")){
            return new Token (TokenTypes.LIT_BOOLEAN, lexeme, line, (column - (lexeme.length())));
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
