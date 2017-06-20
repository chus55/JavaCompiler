package Semantic.Tree.Statement;

import Semantic.SemanticException;
import Semantic.Tree.Expression.ExpressionNode;
import Semantic.Tree.Expression.IdNode;
import Semantic.Types.ContextTable;
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
    public void ValidateSemantic() throws SemanticException {
        Type rightType = RightValue.ValidateSemantic();
        if(!ContextTable.Instance.VariableExist(LeftValue.Name)){
            if (getType() == null){
                throw new SemanticException("Undeclared variable " + LeftValue.Name);
            }
            if (rightType.getClass() == getType().getClass())
                ContextTable.Instance.DeclareVariable(LeftValue.Name,rightType);
            else
                throw new SemanticException("Can't assign " +  rightType.toString() + " to " + getType().toString() );
        }
        else
        {
            Type leftType = ContextTable.Instance.GetVariable(LeftValue.Name);
            if(leftType.getClass() != rightType.getClass())
                throw new SemanticException("Can't assign " +  rightType.toString() + " to " + leftType.toString() );
        }
    }

    @Override
    public void Interpret() {

    }

    @Override
    public String GenerateCode() {

        if (this.getType() == null) {
            return getLeftValue().GenerateCode() + " = " + getRightValue().GenerateCode() + ";";
        }
        return "var " + getLeftValue().GenerateCode() + " = " + getRightValue().GenerateCode() + ";";
    }
}
