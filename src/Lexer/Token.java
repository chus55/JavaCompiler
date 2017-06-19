package Lexer;

/**
 * Created by chusm on 6/18/2017.
 */
public class Token {

    public Token(TokenTypes type, String lexeme, int line, int column) {
        Type = type;
        Lexeme = lexeme;
        Line = line;
        Column = column;
    }

    public Token(TokenTypes type) {
        Type = type;
    }

    public TokenTypes getType() {
        return Type;
    }

    public void setType(TokenTypes type) {
        Type = type;
    }

    public String getLexeme() {
        return Lexeme;
    }

    public void setLexeme(String lexeme) {
        Lexeme = lexeme;
    }

    public int getLine() {
        return Line;
    }

    public void setLine(int line) {
        Line = line;
    }

    public int getColumn() {
        return Column;
    }

    public void setColumn(int column) {
        Column = column;
    }

    public TokenTypes Type;
    public String Lexeme;
    public int Line;
    public int Column;
}
