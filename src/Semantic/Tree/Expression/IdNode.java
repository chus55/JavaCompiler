package Semantic.Tree.Expression;

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
    public void ValidateSemantic() {

    }

    @Override
    public void Interpret() {

    }
}
