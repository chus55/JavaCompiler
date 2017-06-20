package Semantic.Tree.Expression;

import Semantic.Types.IntType;
import Semantic.Types.Type;

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
    public Type ValidateSemantic() {

        return new IntType();
    }

    @Override
    public Interpretation.Value Interpret() {

        return null;
    }
}
