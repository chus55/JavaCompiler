package Semantic.Types;

import Interpretation.Value;

/**
 * Created by chusm on 6/20/2017.
 */
public class StringType extends Type {

    @Override
    public String toString()
    {
        return "String";
    }

    @Override
    public Value GetDefaultValue() {
        return null;
    }
}
