package Semantic.Tree.Statement;

import Semantic.SemanticException;
import Semantic.Tree.Expression.ExpressionNode;

import java.util.List;

/**
 * Created by chusm on 6/20/2017.
 */
public class IfNode extends StatementNode {

    public ExpressionNode getConditional() {
        return Conditional;
    }

    public void setConditional(ExpressionNode conditional) {
        Conditional = conditional;
    }

    public List<StatementNode> getStatementListTrue() {
        return StatementListTrue;
    }

    public void setStatementListTrue(List<StatementNode> statementListTrue) {
        StatementListTrue = statementListTrue;
    }

    public List<StatementNode> getStatementListFalse() {
        return StatementListFalse;
    }

    public void setStatementListFalse(List<StatementNode> statementListFalse) {
        StatementListFalse = statementListFalse;
    }

    public ExpressionNode Conditional;
    public List<StatementNode> StatementListTrue;
    public List<StatementNode> StatementListFalse;

    @Override
    public void ValidateSemantic() throws SemanticException {

    }

    @Override
    public void Interpret() {

    }

    @Override
    public String GenerateCode() {
        return null;
    }
}
