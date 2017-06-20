package Lexer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by chusm on 6/18/2017.
 */
public class LexerDictionaries {
    public HashMap<String, TokenTypes> ReservedWords = new HashMap<String, TokenTypes>();
    public HashMap<String, TokenTypes> Symbols = new HashMap<String, TokenTypes>();
    public HashMap<String, TokenTypes> DoubleSymbols = new HashMap<String, TokenTypes>();
    //public List<String> EscapeChars = new ArrayList<String>();

    public LexerDictionaries() {
        FillReservedWords();
        FillSymbols();
        FillDoubleSymbols();
        //FillEscapeChars();
    }
/*
    private void FillEscapeChars() {
        EscapeChars.add("\'");
        EscapeChars.add("\\\"");
        EscapeChars.add("\\");
        EscapeChars.add("\?");
        EscapeChars.add("\0");
        EscapeChars.add("\a");
        EscapeChars.add("\b");
        EscapeChars.add("\f");
        EscapeChars.add("\n");
        EscapeChars.add("\r");
        EscapeChars.add("\r\n");
        EscapeChars.add("\t");
        EscapeChars.add("\v");
    }
*/
    private void FillDoubleSymbols() {
        Symbols.put("==", TokenTypes.OP_EQUALS);
        Symbols.put("!=", TokenTypes.OP_DIFFERS);
        Symbols.put(">=", TokenTypes.OP_GREATER_EQUAL);
        Symbols.put("<=", TokenTypes.OP_LESS_EQUAL);
    }

    private void FillSymbols() {
        Symbols.put("+", TokenTypes.OP_SUM);
        Symbols.put("-", TokenTypes.OP_SUB);
        Symbols.put("*", TokenTypes.OP_MULT);
        Symbols.put("/", TokenTypes.OP_DIV);
        Symbols.put("(", TokenTypes.OPEN_PAR);
        Symbols.put(")", TokenTypes.CLOSE_PAR);
        Symbols.put("{", TokenTypes.OPEN_CRLY_BRKT);
        Symbols.put("}", TokenTypes.CLOSE_CRLY_BRKT);
        Symbols.put("=", TokenTypes.OP_ASSIGN);
        Symbols.put(";", TokenTypes.END_STATEMENT);
        Symbols.put(">", TokenTypes.OP_GREATER_THAN);
        Symbols.put("<", TokenTypes.OP_LESS_THAN);
    }

    private void FillReservedWords() {
        ReservedWords.put("print", TokenTypes.RW_PRINT);
        ReservedWords.put("int", TokenTypes.RW_INT);
        ReservedWords.put("boolean", TokenTypes.RW_BOOLEAN);
        ReservedWords.put("string", TokenTypes.RW_STRING);
        ReservedWords.put("while", TokenTypes.RW_WHILE);
        ReservedWords.put("if", TokenTypes.RW_IF);
    }
}
