package Lexer;

import java.util.HashMap;

/**
 * Created by chusm on 6/18/2017.
 */
public class LexerDictionaries {
    public HashMap<String, TokenTypes> ReservedWords = new HashMap<String, TokenTypes>();
    public HashMap<String, TokenTypes> Symbols = new HashMap<String, TokenTypes>();

    public LexerDictionaries() {
        FillReservedWords();
        FillSymbols();
    }

    private void FillSymbols() {
        Symbols.put("+", TokenTypes.OP_SUM);
        Symbols.put("-", TokenTypes.OP_SUB);
        Symbols.put("*", TokenTypes.OP_MULT);
        Symbols.put("/", TokenTypes.OP_DIV);
        Symbols.put("(", TokenTypes.OPEN_PAR);
        Symbols.put(")", TokenTypes.CLOSE_PAR);
        Symbols.put("=", TokenTypes.OP_ASSIGN);
        Symbols.put(";", TokenTypes.END_STATEMENT);
    }

    private void FillReservedWords() {
        ReservedWords.put("print", TokenTypes.RW_PRINT);
    }
}
