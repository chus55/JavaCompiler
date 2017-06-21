package Semantic.Tree.Statement;

import Semantic.SemanticException;
import Semantic.Tree.Expression.ExpressionNode;
import Semantic.Types.BooleanType;
import Semantic.Types.Type;

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
        Type conditional = Conditional.ValidateSemantic();

        if (!(conditional instanceof BooleanType))
            throw new SemanticException("Condition must be of type boolean and not " +  conditional.toString());

        for (StatementNode statement : StatementListTrue) {
            statement.ValidateSemantic();
        }

        if (StatementListFalse != null){
            for (StatementNode statement : StatementListFalse) {
                statement.ValidateSemantic();
            }
        }
    }

    @Override
    public void Interpret() {

    }

    @Override
    public String GenerateCode() {

        String condition = "if( "+ getConditional().GenerateCode() +" ) {\n" ;

        String body = "";

        for (StatementNode stm : StatementListTrue) {
            body += "\t" + stm.GenerateCode();
        }

        if (getStatementListFalse() != null) {
            body += "\n} \nelse {\n";

            for (StatementNode stm : StatementListFalse) {
                body  += "\t" + stm.GenerateCode();
            }
        }

        body += "\n}";

        return condition + body;
    }
}
