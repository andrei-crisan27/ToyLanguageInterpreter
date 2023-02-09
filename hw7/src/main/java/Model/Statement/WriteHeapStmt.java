package Model.Statement;

import Exception.MyException;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Expressions.IExp;
import Model.PrgState;
import Model.Type.RefType;
import Model.Type.Type;
import Model.Value.RefValue;
import Model.Value.Value;

public class WriteHeapStmt implements IStmt{
    String varName;
    IExp expression;

    public WriteHeapStmt(String varName, IExp expression){
        this.varName = varName;
        this.expression = expression;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        if (typeEnv.lookup(varName).equals(new RefType(expression.typeCheck(typeEnv))))
            return typeEnv;
        else
            throw new MyException("WriteHeap: right hand side and left hand side have different types.");
    }

    @Override
    public PrgState execute(PrgState state) throws MyException{
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap heap = state.getHeap();
        if(!symTbl.isDefined(varName))
            throw new MyException(varName + " is not present in the SymTable!");
        Value value = symTbl.lookup(varName);
        if(!(value instanceof RefValue))
            throw new MyException(value.toString() + " is not of RefType!");
        RefValue refValue = (RefValue) value;
        Value evaluated = expression.eval(symTbl, heap);
        if(!evaluated.getType().equals(refValue.getLocationType()))
            throw new MyException(evaluated + " not of type " + refValue.getLocationType());
        heap.update(refValue.getAddress(), evaluated);
        state.setHeap(heap);
        return null;
    }

    @Override
    public IStmt deepCopy(){
        return new WriteHeapStmt(varName, expression.deepCopy());
    }

    @Override
    public String toString(){
        return "WriteHeap(" + varName + ", " + expression + ")";
    }
}
