package Model.Type;

import Model.Value.IntValue;
import Model.Value.Value;

public class IntType implements Type{
    @Override
    public boolean equals(Type anotherType) {
        return anotherType instanceof IntType;
    }
    @Override
    public Value defaultValue() {
        return new IntValue(0);
    }

    @Override
    public String toString(){
        return "int";
    }

    @Override
    public Type deepCopy() {
        return new IntType();
    }
}
