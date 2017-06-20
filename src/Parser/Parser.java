package Parser;

import Lexer.Token;
import Lexer.TokenTypes;
import Semantic.Tree.Expression.*;
import Semantic.Tree.Statement.*;
import Semantic.Types.IntType;
import Semantic.Types.Type;
import jdk.nashorn.internal.runtime.ParserException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public List<StatementNode> Parse() {
        List<StatementNode> code = Code();
        if (!utilities.isEof(CurrentToken))
        {
            throwParserException("Expected EOF");
        }
        return code;
    }

    private List<StatementNode> Code() {
        return StatementList();
    }

    private List<StatementNode> StatementList() {
        if (utilities.isStatement(CurrentToken)) {
            StatementNode statement = Statement();
            List<StatementNode> statementList = StatementList();
            statementList.add(0, statement);
            return statementList;
        } else {
            return new ArrayList<StatementNode>();
        }
    }

    private StatementNode Statement() {
        if (utilities.isType(CurrentToken)) {
            Type type = consumeType();
            IdNode id = consumeIdentifier();
            consumeAssign();
            ExpressionNode expression = Expression();
            consumeEndOfStatement();
            AssignationNode assignationNode = new AssignationNode();
            assignationNode.setType(type);
            assignationNode.setLeftValue(id);
            assignationNode.setRightValue(expression);
            return assignationNode;
        } else if (utilities.isIdentifier(CurrentToken)){
            IdNode id = consumeIdentifier();
            consumeAssign();
            ExpressionNode expression = Expression();
            consumeEndOfStatement();
            AssignationNode assignationNode = new AssignationNode();
            assignationNode.setLeftValue(id);
            assignationNode.setRightValue(expression);
            return assignationNode;
        } else if (utilities.isWhile(CurrentToken)){
            consumeWhile();
            consumeOpenPar();
            ExpressionNode condition = Expression();
            consumeClosePar();
            consumeOpenCurlyBrackets();
            List<StatementNode> statementList = StatementList();
            consumeCloseCurlyBrackets();
            WhileNode whileNode = new WhileNode();
            whileNode.setConditional(condition);
            whileNode.setStatementList(statementList);
            return whileNode;
        } else if (utilities.isIf(CurrentToken)){
            consumeIf();
            consumeOpenPar();
            ExpressionNode condition = Expression();
            consumeClosePar();
            consumeOpenCurlyBrackets();
            List<StatementNode> statementListTrue = StatementList();
            consumeCloseCurlyBrackets();
            IfNode ifNode = new IfNode();
            ifNode.setConditional(condition);
            ifNode.setStatementListTrue(statementListTrue);
            if (!utilities.isElse(CurrentToken))
                return ifNode;
            consumeElse();
            consumeOpenCurlyBrackets();
            List<StatementNode> statementListFalse = StatementList();
            consumeCloseCurlyBrackets();
            ifNode.setStatementListFalse(statementListFalse);
            return ifNode;
        } else if (utilities.isPrint(CurrentToken)) {
            consumePrint();
            ExpressionNode expression = Expression();
            consumeEndOfStatement();
            PrintNode printNode = new PrintNode();
            printNode.setValue(expression);
            return printNode;
        } else{
            throwParserException("Expected type, identifier, while, if, or 'print'");
        }
        return null;
    }

    private ExpressionNode Expression() {
        ExpressionNode term = Term();
        return ExpressionPrimed(term);
    }

    private ExpressionNode ExpressionPrimed(ExpressionNode param) {
        if (utilities.isSum(CurrentToken)) {
            consumeSum();
            ExpressionNode term = Term();
            SumNode sumNode = new SumNode();
            sumNode.setLeftOperand(param);
            sumNode.setRightOperand(term);
            return ExpressionPrimed(sumNode);
        } else if (utilities.isDiv(CurrentToken)) {
            consumeSub();
            ExpressionNode term = Term();
            SubNode subNode = new SubNode();
            subNode.setLeftOperand(param);
            subNode.setRightOperand(term);
            return ExpressionPrimed(subNode);
        } else {
            return param;
        }
    }

    private ExpressionNode Term() {
        ExpressionNode factor = Factor();
        return TermPrimed(factor);
    }

    private ExpressionNode TermPrimed(ExpressionNode param) {
        if (utilities.isMult(CurrentToken)) {
            consumeMult();
            ExpressionNode factor = Factor();
            MultNode multNode = new MultNode();
            multNode.setLeftOperand(param);
            multNode.setRightOperand(factor);
            return TermPrimed(multNode);
        } else if (utilities.isDiv(CurrentToken)) {
            consumeDiv();
            ExpressionNode factor = Factor();
            DivNode divNode = new DivNode();
            divNode.setLeftOperand(param);
            divNode.setRightOperand(factor);
            return TermPrimed(divNode);
        } else {
            return param;
        }
    }

    private ExpressionNode Factor() {
        if (utilities.isIdentifier(CurrentToken)) {
            IdNode id = consumeIdentifier();
            return id;
        } else if (utilities.isLiteralInt(CurrentToken)) {
            LiteralIntNode litInt = consumeLiteralInt();
            return litInt;
        } else if (utilities.isLiteralString(CurrentToken)){
            LiteralStringNode litString = consumeLiteralString();
            return litString;
        } else if (utilities.isLiteralBoolean(CurrentToken)){
            LiteralBooleanNode litBoolean = consumeLiteralBoolean();
            return litBoolean;
        } else if (utilities.isOpenPar(CurrentToken)) {
            consumeOpenPar();
            ExpressionNode expression = Expression();
            consumeClosePar();
            return expression;
        } else {
            throwParserException("Expected identifier, literal int, or '('");
        }
        return null;
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

    IdNode consumeIdentifier() {
        if (CurrentToken.getType() != TokenTypes.ID) {
            throwParserException("Expected identifier");
        }
        Token tmpToken = CurrentToken;
        consumeToken();
        IdNode returnNode = new IdNode();
        returnNode.setName(tmpToken.getLexeme());
        return returnNode;
    }

    LiteralIntNode consumeLiteralInt() {
        if (CurrentToken.getType() != TokenTypes.LIT_INT) {
            throwParserException("Expected a literal int");
        }
        Token tmpToken = CurrentToken;
        consumeToken();
        LiteralIntNode returnNode = new LiteralIntNode();
        returnNode.setValue(Integer.parseInt(tmpToken.getLexeme()));
        return returnNode;
    }

    LiteralStringNode consumeLiteralString() {
        if (CurrentToken.getType() != TokenTypes.LIT_STRING) {
            throwParserException("Expected a literal string");
        }
        Token tmpToken = CurrentToken;
        consumeToken();
        LiteralStringNode returnNode = new LiteralStringNode();
        returnNode.setValue(tmpToken.getLexeme());
        return returnNode;
    }

    LiteralBooleanNode consumeLiteralBoolean() {
        if (CurrentToken.getType() != TokenTypes.LIT_BOOLEAN) {
            throwParserException("Expected a literal boolean");
        }
        Token tmpToken = CurrentToken;
        consumeToken();
        LiteralBooleanNode returnNode = new LiteralBooleanNode();
        if (Objects.equals(tmpToken.getLexeme(), "true"))
            returnNode.setValue(true);
        else
            returnNode.setValue(false);
        return returnNode;
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

    Type consumeType() {
        if (!utilities.isType(CurrentToken)) {
            throwParserException("Expected a type");
        }
        Token tmpToken = CurrentToken;
        consumeToken();
        if (tmpToken.getType() == TokenTypes.RW_INT)
            return new IntType();
        return null;
    }

    void consumeOpenCurlyBrackets() {
        if (CurrentToken.getType() != TokenTypes.OPEN_CRLY_BRKT) {
            throwParserException("Expected '{'");
        }
        consumeToken();
    }

    void consumeCloseCurlyBrackets() {
        if (CurrentToken.getType() != TokenTypes.CLOSE_CRLY_BRKT) {
            throwParserException("Expected '}'");
        }
        consumeToken();
    }

    void consumeWhile() {
        if (CurrentToken.getType() != TokenTypes.RW_WHILE) {
            throwParserException("Expected 'while'");
        }
        consumeToken();
    }

    void consumeIf() {
        if (CurrentToken.getType() != TokenTypes.RW_IF) {
            throwParserException("Expected 'if'");
        }
        consumeToken();
    }

    void consumeElse() {
        if (CurrentToken.getType() != TokenTypes.RW_ELSE) {
            throwParserException("Expected 'else'");
        }
        consumeToken();
    }
    void throwParserException(String msg) {
        throw new ParserException(msg + " at line: "+ CurrentToken.getLine() + ", column: " + CurrentToken.getColumn());
    }
}
