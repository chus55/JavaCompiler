package Parser;

import Lexer.Token;
import Lexer.TokenTypes;
import jdk.nashorn.internal.runtime.ParserException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chusm on 6/18/2017.
 */
public class Parser {

    List<Token> TokenList;
    int TokenIndex;
    Token CurrentToken;
    ParserUtilities utilities;

    public Parser(List<Token> tokenList) {
        TokenList = tokenList;
        TokenIndex = 0;
        CurrentToken = TokenList.get(TokenIndex);
        utilities = new ParserUtilities(new ParserDictionaries());
    }
    //-----------------Parser-----------------//

    public void Parse() {
        Code();
        if (!utilities.isEof(CurrentToken))
        {
            throwParserException("Expected EOF");
        }
    }

    private void Code() {
        StatementList();
    }

    private void StatementList() {
        if (utilities.isStatement(CurrentToken)) {
            Statement();
            StatementList();
        } else {
            //Epsilon
        }
    }

    private void Statement() {
        if (utilities.isType(CurrentToken)) {
            consumeType();
            consumeIdentifier();
            consumeAssign();
            Expression();
            consumeEndOfStatement();
        } else if (utilities.isPrint(CurrentToken)) {
            consumePrint();
            Expression();
            consumeEndOfStatement();
        } else{
            throwParserException("Expected identifier or 'print'");
        }
    }

    private void Expression() {
        Term();
        ExpressionPrimed();
    }

    private void ExpressionPrimed() {
        if (utilities.isSum(CurrentToken)) {
            consumeSum();
            Term();
            ExpressionPrimed();
        } else if (utilities.isDiv(CurrentToken)) {
            consumeSub();
            Term();
            ExpressionPrimed();
        } else {
            //Epsilon
        }
    }

    private void Term() {
        Factor();
        TermPrimed();
    }

    private void TermPrimed() {
        if (utilities.isMult(CurrentToken)) {
            consumeMult();
            Factor();
            TermPrimed();
        } else if (utilities.isDiv(CurrentToken)) {
            consumeDiv();
            Factor();
            TermPrimed();
        } else {
            //Epsilon
        }
    }

    private void Factor() {
        if (utilities.isIdentifier(CurrentToken)) {
            consumeIdentifier();
        } else if (utilities.isLiteralInt(CurrentToken)) {
            consumeLiteralInt();
        } else if (utilities.isOpenPar(CurrentToken)) {
            consumeOpenPar();
            Expression();
            consumeClosePar();
        } else {
            throwParserException("Expected identifier, literal int, or '('");
        }
    }

    //-----------------Consumers-----------------//

    void consumeOpenPar() {
        if (CurrentToken.getType() != TokenTypes.OPEN_PAR) {
            throwParserException("Expected '('");
        }
        consumeToken();
    }

    void consumeClosePar() {
        if (CurrentToken.getType() != TokenTypes.CLOSE_PAR) {
            throwParserException("Expected ')'");
        }
        consumeToken();
    }

    void consumeEndOfStatement() {
        if (CurrentToken.getType() != TokenTypes.END_STATEMENT) {
            throwParserException("Expected ';'");
        }
        consumeToken();
    }

    void consumePrint() {
        if (CurrentToken.getType() != TokenTypes.RW_PRINT) {
            throwParserException("Expected 'print'");
        }
        consumeToken();
    }

    void consumeAdditiveOperator() {
        if (utilities.isAdditiveExpression(CurrentToken)) {
            throwParserException("Expected '+' or '-'");
        }
        consumeToken();
    }

    void consumeMultiplicativeOperator() {
        if (utilities.isMultiplicativeExpression(CurrentToken)) {
            throwParserException("Expected '*' or '/'");
        }
        consumeToken();
    }

    void consumeAssign() {
        if (CurrentToken.getType() != TokenTypes.OP_ASSIGN) {
            throwParserException("Expected '='");
        }
        consumeToken();
    }

    void consumeIdentifier() {
        if (CurrentToken.getType() != TokenTypes.ID) {
            throwParserException("Expected identifier");
        }
        consumeToken();
    }

    void consumeLiteralInt() {
        if (CurrentToken.getType() != TokenTypes.LIT_INT) {
            throwParserException("Expected a literal int");
        }
        consumeToken();
    }

    void consumeMult() {
        if (CurrentToken.getType() != TokenTypes.OP_MULT) {
            throwParserException("Expected op mult");
        }
        consumeToken();
    }

    void consumeDiv() {
        if (CurrentToken.getType() != TokenTypes.OP_DIV) {
            throwParserException("Expected op div");
        }
        consumeToken();
    }

    void consumeSum() {
        if (CurrentToken.getType() != TokenTypes.OP_SUM) {
            throwParserException("Expected op sum");
        }
        consumeToken();
    }

    void consumeSub() {
        if (CurrentToken.getType() != TokenTypes.OP_SUB) {
            throwParserException("Expected op sub");
        }
        consumeToken();
    }

    void consumeToken() {
        if (TokenList.size() < TokenIndex)
            throw new ParserException("Reached end of token list (including EOF token)");
        TokenIndex++;
        CurrentToken = TokenList.get(TokenIndex);
    }

    void consumeType() {
        if (!utilities.isType(CurrentToken)) {
            throwParserException("Expected a type");
        }
        consumeToken();
    }

    void throwParserException(String msg) {
        throw new ParserException(msg + " at line: "+ CurrentToken.getLine() + ", column: " + CurrentToken.getColumn());
    }
}
