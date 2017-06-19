package Parser;

import Lexer.Token;
import Lexer.TokenTypes;

/**
 * Created by chusm on 6/18/2017.
 */
public class ParserUtilities {

    ParserDictionaries dictionaries;

    ParserUtilities(ParserDictionaries pDictionaries) {
        dictionaries = pDictionaries;
    };

    boolean isExpression(Token token) {
        if (dictionaries.ExpressionPossibilities.contains(token.Type))
            return true;
        return false;
    }

    boolean isAdditiveExpression(Token token) {
        if (dictionaries.AdditivePossibilities.contains(token.Type))
            return true;
        return false;
    }

    boolean isMultiplicativeExpression(Token token) {
        if (dictionaries.MultiplicativePossibilites.contains(token.Type))
            return true;
        return false;
    }

    boolean isStatement(Token token) {
        if (dictionaries.StatementPossibilites.contains(token.Type))
            return true;
        return false;
    }

    boolean isPrint(Token token) {
        if (token.getType() == TokenTypes.RW_PRINT)
            return true;
        return false;
    }

    boolean isEof(Token token) {
        if (token.getType() == TokenTypes.EOF)
            return true;
        return false;
    }

    boolean isIdentifier(Token token) {
        if (token.getType() == TokenTypes.ID)
            return true;
        return false;
    }

    boolean isOpenPar(Token token) {
        if (token.getType() == TokenTypes.OPEN_PAR)
            return true;
        return false;
    }

    boolean isMult(Token token) {
        if (token.getType() == TokenTypes.OP_MULT)
            return true;
        return false;
    }

    boolean isDiv(Token token) {
        if (token.getType() == TokenTypes.OP_DIV)
            return true;
        return false;
    }

    boolean isSum(Token token) {
        if (token.getType() == TokenTypes.OP_SUM)
            return true;
        return false;
    }

    boolean isSub(Token token) {
        if (token.getType() == TokenTypes.OP_SUB)
            return true;
        return false;
    }

    boolean isLiteralInt(Token token) {
        if (token.getType() == TokenTypes.LIT_INT)
            return true;
        return false;
    }

    boolean isType(Token token) {
        if (dictionaries.Types.contains(token.Type))
            return true;
        return false;
    }
}
