package Semantic.Tree.Expression;

import Interpretation.Value;
import Semantic.SemanticException;
import Semantic.Types.Type;

/**
 * Created by chusm on 6/20/2017.
 */
public abstract class ExpressionNode {
    public abstract Type ValidateSemantic() throws SemanticException;
    public abstract Value Interpret();
}
