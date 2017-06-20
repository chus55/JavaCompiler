package Semantic.Tree.Expression;

/**
 * Created by chusm on 6/20/2017.
 */
public class LiteralIntNode extends ExpressionNode {

    public int getValue() {
        return Value;
    }

    public void setValue(int value) {
        Value = value;
    }

    public int Value;

    @Override
    public void ValidateSemantic() {

    }

    @Override
    public void Interpret() {

    }
}
