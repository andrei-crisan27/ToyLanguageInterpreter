package Model.Expressions;
import Exception.MyException;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.Value;

import java.util.Objects;

public class RelationalExpression implements IExp{
    IExp e1;
    IExp e2;
    String op;

    public RelationalExpression(IExp exp1, IExp exp2, String operator){
        this.e1 = exp1;
        this.e2 = exp2;
        this.op = operator;
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type type1, type2;
        type1 = e1.typeCheck(typeEnv);
        type2 = e2.typeCheck(typeEnv);
        if (type1.equals(new IntType())) {
            if (type2.equals(new IntType())) {
                return new BoolType();
            } else
                throw new MyException("Second operand is not an integer.");
        } else
            throw new MyException("First operand is not an integer.");

    }

    @Override
    public Value eval(MyIDictionary<String, Value> symTable, MyIHeap heap) throws MyException{
        Value value1;
        Value value2;
        value1 = this.e1.eval(symTable, heap);
        if(value1.getType().equals(new IntType())){
            value2 = this.e2.eval(symTable, heap);
            if(value2.getType().equals(new IntType())){
                IntValue val1 = (IntValue) value1;
                IntValue val2 = (IntValue) value2;
                int v1, v2;
                v1 = val1.getVal();
                v2 = val2.getVal();
                if (Objects.equals(this.op, "<"))
                    return new BoolValue(v1 < v2);
                else if (Objects.equals(this.op, "<="))
                    return new BoolValue(v1 <= v2);
                else if (Objects.equals(this.op, "=="))
                    return new BoolValue(v1 == v2);
                else if (Objects.equals(this.op, "!="))
                    return new BoolValue(v1 != v2);
                else if (Objects.equals(this.op, ">"))
                    return new BoolValue(v1 > v2);
                else if (Objects.equals(this.op, ">="))
                    return new BoolValue(v1 >= v2);
            } else
                throw new MyException("Second operand is not an integer!");
        } else
            throw new MyException("First operand is not an integer!");
        return null;
    }

    @Override
    public String toString() {
        return this.e1.toString() + " " + this.op + " " + this.e2.toString();
    }

    @Override
    public IExp deepCopy() {
        return new RelationalExpression(e1.deepCopy(), e2.deepCopy(), op);
    }
}
