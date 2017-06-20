package Semantic.Tree.Expression;

import Interpretation.Value;
import Semantic.SemanticException;
import Semantic.Types.IntType;
import Semantic.Types.Type;

/**
 * Created by chusm on 6/20/2017.
 */
public class SubNode extends BinaryOperatorNode{
    @Override
    public Type ValidateSemantic() throws SemanticException {

        Type leftType = LeftOperand.ValidateSemantic();
        Type rightType = RightOperand.ValidateSemantic();
        if (leftType.getClass() == rightType.getClass())
        {
            if (leftType instanceof IntType)
                return leftType;
        }
        throw new SemanticException("Can't subtract " + leftType.toString() + " by " + rightType.toString());
    }

    @Override
    public Value Interpret() {

        return null;
    }
}
