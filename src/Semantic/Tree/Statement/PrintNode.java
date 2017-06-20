package Semantic.Tree.Statement;

import Semantic.SemanticException;
import Semantic.Tree.Expression.ExpressionNode;

/**
 * Created by chusm on 6/20/2017.
 */
public class PrintNode extends StatementNode {

    public ExpressionNode getValue() {
        return Value;
    }

    public void setValue(ExpressionNode value) {
        Value = value;
    }

    public ExpressionNode Value;

    @Override
    public void ValidateSemantic() throws SemanticException {
        Value.ValidateSemantic();
    }

    @Override
    public void Interpret() {

    }

    @Override
    public String GenerateCode() {
        return "console.log(" + getValue() + ");";
    }
}
