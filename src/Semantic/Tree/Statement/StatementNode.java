package Semantic.Tree.Statement;

import Semantic.SemanticException;

/**
 * Created by chusm on 6/20/2017.
 */
public abstract class StatementNode {
    public abstract void ValidateSemantic() throws SemanticException;
    public abstract void Interpret();
    public abstract String GenerateCode();
}
