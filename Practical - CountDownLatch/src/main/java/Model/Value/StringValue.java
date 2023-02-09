package Model.Value;

import Model.Type.StringType;
import Model.Type.Type;

public class StringValue implements Value {
    String val;

    public StringValue(String value) {
        this.val = value;
    }

    public String getValue() {
        return this.val;
    }

    @Override
    public String toString() {
        return "" + val;
    }

    @Override
    public Type getType() {
        return new StringType();
    }

    @Override
    public boolean equals(Object anotherValue) {
        if (!(anotherValue instanceof StringValue newString))
            return false;
        return this.val.equals(newString.val);
    }

    @Override
    public Value deepCopy() {
        return new StringValue(val);
    }
}
