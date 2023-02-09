package Model.Value;

import Model.Type.IntType;
import Model.Type.Type;

public class IntValue implements Value {
    int val;

    public IntValue(int v) {
        val = v;
    }

    public int getVal() {
        return val;
    }
    @Override
    public String toString(){
        return ""+val;
    }

    @Override
    public Type getType() {
        return new IntType();
    }

    @Override
    public boolean equals(Object anotherValue) {
        if (!(anotherValue instanceof IntValue newValue))
            return false;
        return this.val == newValue.val;
    }

    @Override
    public Value deepCopy() {
        return new IntValue(val);
    }
}
