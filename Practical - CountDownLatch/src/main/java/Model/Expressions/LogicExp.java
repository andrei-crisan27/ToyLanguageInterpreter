package Model.Expressions;

import Exception.MyException;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Type.BoolType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.Value;

public class LogicExp implements IExp {
    IExp e1;
    IExp e2;
    String op;

    public LogicExp(IExp exp1, IExp exp2, String operation){
        this.e1 = exp1;
        this.e2 = exp2;
        this.op = operation;
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type type1, type2;
        type1 = e1.typeCheck(typeEnv);
        type2 = e2.typeCheck(typeEnv);
        if (type1.equals(new BoolType())) {
            if (type2.equals(new BoolType())) {
                return new BoolType();
            } else
                throw new MyException("Second operand is not a boolean.");
        } else
            throw new MyException("First operand is not a boolean.");

    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap heap) throws MyException{
        Value v1;
        Value v2;
        v1 = e1.eval(tbl, heap);
        if(v1.getType().equals(new BoolType())){
            v2 = this.e2.eval(tbl, heap);
            if(v2.getType().equals(new BoolType())) {
                BoolValue bool1 = (BoolValue) v1;
                BoolValue bool2 = (BoolValue) v2;
                boolean b1, b2;
                b1 = bool1.getVal();
                b2 = bool2.getVal();
                if (op.equals("and")) {
                    return new BoolValue(b1 && b2);
                } else if (op.equals("or")) {
                    return new BoolValue(b1 || b2);
                }
            }else{
                    throw new MyException("Second operand is not of bool type.");
            }
        } else{
            throw new MyException("First operand is not of bool type.");
        }
        return null;
    }

    @Override
    public String toString(){
        return e1.toString() + " " + op + " " + e2.toString();
    }

    @Override
    public IExp deepCopy() {
        return new LogicExp(e1.deepCopy(), e2.deepCopy(), op);
    }
}
