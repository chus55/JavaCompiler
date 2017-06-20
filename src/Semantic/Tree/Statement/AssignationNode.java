package Semantic.Tree.Statement;

import Semantic.Tree.Expression.ExpressionNode;
import Semantic.Tree.Expression.IdNode;
import Semantic.Types.Type;

/**
 * Created by chusm on 6/20/2017.
 */
public class AssignationNode extends StatementNode{

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Type type;

    public IdNode getLeftValue() {
        return LeftValue;
    }

    public void setLeftValue(IdNode leftValue) {
        LeftValue = leftValue;
    }

    public IdNode LeftValue;

    public ExpressionNode getRightValue() {
        return RightValue;
    }

    public void setRightValue(ExpressionNode rightValue) {
        RightValue = rightValue;
    }

    public ExpressionNode RightValue;

    @Override
    public void ValidateSemantic() {

    }

    @Override
    public void Interpret() {

    }
}
