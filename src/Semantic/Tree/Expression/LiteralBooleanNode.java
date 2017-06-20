package Semantic.Tree.Expression;

import Interpretation.Value;
import Semantic.SemanticException;
import Semantic.Types.Type;

/**
 * Created by chusm on 6/20/2017.
 */
public class LiteralBooleanNode extends ExpressionNode {

    public boolean isValue() {
        return Value;
    }

    public void setValue(boolean value) {
        Value = value;
    }

    public boolean Value;

    @Override
    public Type ValidateSemantic() throws SemanticException {
        return null;
    }

    @Override
    public Value Interpret() {
        return null;
    }

    @Override
    public String GenerateCode() {
        return null;
    }
}
