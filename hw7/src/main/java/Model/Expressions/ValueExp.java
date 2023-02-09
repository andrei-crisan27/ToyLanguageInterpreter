package Model.Expressions;

import Exception.MyException;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Type.Type;
import Model.Value.Value;

public class ValueExp implements IExp {
    Value e;

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return e.getType();
    }
    public ValueExp(Value value){
        this.e = value;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap heap) throws MyException{
        return this.e;
    }

    @Override
    public String toString(){
        return this.e.toString();
    }

    @Override
    public IExp deepCopy() {
        return new ValueExp(e);
    }
}
