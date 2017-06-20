package Semantic.Tree.Expression;

import Interpretation.Value;
import Semantic.SemanticException;
import Semantic.Types.ContextTable;
import Semantic.Types.Type;

/**
 * Created by chusm on 6/20/2017.
 */
public class IdNode extends ExpressionNode{

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String Name;

    @Override
    public Type ValidateSemantic() throws SemanticException {
        if (!ContextTable.Instance.VariableExist(Name))
            throw new SemanticException("Variable " + Name + " does not exist.");
        return ContextTable.Instance.GetVariable(Name);
    }

    @Override
    public Value Interpret() {

        return null;
    }
}
