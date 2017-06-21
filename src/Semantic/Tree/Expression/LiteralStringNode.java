package Semantic.Tree.Expression;

import Interpretation.Value;
import Semantic.SemanticException;
import Semantic.Types.StringType;
import Semantic.Types.Type;

/**
 * Created by chusm on 6/20/2017.
 */
public class LiteralStringNode extends ExpressionNode{

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public String Value;

    @Override
    public Type ValidateSemantic() throws SemanticException {
        return new StringType();
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
