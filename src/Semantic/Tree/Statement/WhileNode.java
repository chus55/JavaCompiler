package Semantic.Tree.Statement;

import Semantic.SemanticException;
import Semantic.Tree.Expression.ExpressionNode;
import Semantic.Types.BooleanType;
import Semantic.Types.Type;

import java.util.List;

/**
 * Created by chusm on 6/20/2017.
 */
public class WhileNode extends StatementNode {

    public ExpressionNode getConditional() {
        return Conditional;
    }

    public void setConditional(ExpressionNode conditional) {
        Conditional = conditional;
    }

    public List<StatementNode> getStatementList() {
        return StatementList;
    }

    public void setStatementList(List<StatementNode> statementList) {
        StatementList = statementList;
    }

    public ExpressionNode Conditional;
    public List<StatementNode> StatementList;

    @Override
    public void ValidateSemantic() throws SemanticException {
        Type conditional  = Conditional.ValidateSemantic();

        if (!(conditional instanceof BooleanType))
            throw new SemanticException("Condition must be of type boolean and not " +  conditional.toString());

        for (StatementNode statement : StatementList) {
            statement.ValidateSemantic();
        }
    }

    @Override
    public void Interpret() {

    }

    @Override
    public String GenerateCode() {
        return null;
    }
}
