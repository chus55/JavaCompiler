package Semantic.Types;

import Interpretation.Value;

import java.util.HashMap;

/**
 * Created by chusm on 6/20/2017.
 */
public class ContextTable {
    private static ContextTable _Instance;
    private HashMap<String, Type> Variables;
    private  HashMap<String, Value> Values;

    private ContextTable(){
        Variables = new HashMap<String, Type>();
        Values = new HashMap<String, Value>();
    }

    //public static ContextTable Instance => _Instance ?? (_Instance = new ContextTable()); //  Fix this bitch

    //public static ContextTable Instance = _Instance == null? new ContextTable() : _Instance;

    public static ContextTable Instance = _Instance == null ? _Instance = new ContextTable() : _Instance;

    public void DeclareVariable(String name, Type type)
    {
        Variables.put(name, type);
        Values.put(name,type.GetDefaultValue());
    }

    public Type GetVariable(String name)
    {
        return Variables.get(name);
    }

    public void SetVariableValue(String name, Value value)
    {
        Values.replace(name, value);
    }

    public Value GetVariableValue(String name)
    {
        return Values.get(name);
    }

    public boolean VariableExist(String name)
    {
        return Variables.containsKey(name);
    }
}
