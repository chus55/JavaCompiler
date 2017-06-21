package Semantic.Types;

import Interpretation.Value;

/**
 * Created by chusm on 6/20/2017.
 */
public class BooleanType extends Type {

    @Override
    public String toString()
    {
        return "Boolean";
    }

    @Override
    public Value GetDefaultValue() {
        return null;
    }
}
