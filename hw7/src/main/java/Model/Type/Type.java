package Model.Type;

import Model.Value.Value;

public interface Type {
    Value defaultValue();
    boolean equals(Type anotherType);
    Type deepCopy();
}
