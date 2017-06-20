package Interpretation;

/**
 * Created by chusm on 6/20/2017.
 */
public class IntValue extends Value{

    public int getValue() {
        return Value;
    }

    public void setValue(int value) {
        Value = value;
    }

    public int Value;

    @Override
    public Value Clone() {
        return null;
    }
}
