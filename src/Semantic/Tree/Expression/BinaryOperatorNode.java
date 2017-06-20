package Semantic.Tree.Expression;

/**
 * Created by chusm on 6/20/2017.
 */
public abstract class BinaryOperatorNode extends ExpressionNode{
    public ExpressionNode getLeftOperand() {
        return LeftOperand;
    }

    public void setLeftOperand(ExpressionNode leftOperand) {
        LeftOperand = leftOperand;
    }

    public ExpressionNode LeftOperand;

    public ExpressionNode getRightOperand() {
        return RightOperand;
    }

    public void setRightOperand(ExpressionNode rightOperand) {
        RightOperand = rightOperand;
    }

    public ExpressionNode RightOperand;
}
