package Model.Value;

import Model.Type.BoolType;
import Model.Type.Type;

public class BoolValue implements Value{
    boolean val;

    public BoolValue(boolean v) {
        val = v;
    }

    public boolean getVal() {
        return val;
    }
    @Override
    public String toString(){
        return ""+val;
    }

    @Override
    public Type getType() {
        return new BoolType();
    }

    @Override
    public boolean equals(Object anotherValue) {
        if (!(anotherValue instanceof BoolValue newValue))
            return false;
        return this.val == newValue.val;
    }

    @Override
    public Value deepCopy() {
        return new BoolValue(val);
    }
}
