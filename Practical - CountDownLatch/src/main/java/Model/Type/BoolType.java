package Model.Type;

import Model.Value.BoolValue;
import Model.Value.Value;

public class BoolType implements Type{
    @Override
    public boolean equals(Type anotherType) {
        return anotherType instanceof BoolType;
    }
    @Override
    public Value defaultValue() {
        return new BoolValue(false);
    }

    @Override
    public String toString(){
        return "bool";
    }

    @Override
    public Type deepCopy() {
        return new BoolType();
    }
}
