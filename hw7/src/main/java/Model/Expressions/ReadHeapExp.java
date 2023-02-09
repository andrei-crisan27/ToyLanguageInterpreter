package Model.Expressions;

import Exception.MyException;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Type.RefType;
import Model.Type.Type;
import Model.Value.RefValue;
import Model.Value.Value;

public class ReadHeapExp implements IExp{
    IExp expression;

    public ReadHeapExp(IExp expression){
        this.expression = expression;
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type type = expression.typeCheck(typeEnv);
        if (type instanceof RefType) {
            RefType refType = (RefType) type;
            return refType.getInner();
        } else
            throw new MyException("The rH argument is not a RefType.");
    }

    @Override
    public Value eval(MyIDictionary<String, Value> symTbl, MyIHeap heap) throws MyException{
        Value value = expression.eval(symTbl, heap);
        if(!(value instanceof RefValue))
            throw new MyException("Value is not of RefType!");
        RefValue refValue = (RefValue) value;
        return heap.get(refValue.getAddress());
    }

    @Override
    public IExp deepCopy(){
        return new ReadHeapExp(expression.deepCopy());
    }

    @Override
    public String toString(){
        return "ReadHeap(" + expression.toString() + ")";
    }
}
