package Model.Expressions;
import Exception.MyException;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.Value;

public class NotExp implements IExp{
    private final IExp expression;

    public NotExp(IExp expression) {
        this.expression = expression;
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return expression.typeCheck(typeEnv);
    }

    @Override
    public Value eval(MyIDictionary<String, Value> table, MyIHeap heap) throws MyException {
        BoolValue value = (BoolValue) expression.eval(table, heap);
        if (!value.getVal())
            return new BoolValue(true);
        else
            return new BoolValue(false);
    }

    @Override
    public IExp deepCopy() {
        return new NotExp(expression.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("!(%s)", expression);
    }
}
