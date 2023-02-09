package Model.Expressions;

import Exception.MyException;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Type.Type;
import Model.Value.Value;

public class VarExp implements IExp {
    String id;

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv.lookup(id);
    }

    public VarExp(String str) {
        id = str;
    }
    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap heap) throws MyException{
        return tbl.lookup(id);
    }

    @Override
    public String toString(){
        return id;
    }

    @Override
    public IExp deepCopy() {
        return new VarExp(id);
    }
}
