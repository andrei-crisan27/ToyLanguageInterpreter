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

public class NewStmt implements IStmt{
    String varName;
    IExp expression;

    public NewStmt(String varName, IExp expression){
        this.varName = varName;
        this.expression = expression;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeVar = typeEnv.lookup(varName);
        Type typeExpr = expression.typeCheck(typeEnv);
        if (typeVar.equals(new RefType(typeExpr)))
            return typeEnv;
        else
            throw new MyException("NEW statement: right hand side and left hand side have different types.");
    }

    @Override
    public PrgState execute(PrgState state) throws MyException{
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap heap = state.getHeap();
        if(!symTbl.isDefined(varName))
            throw new MyException(varName + " is not in SymTable!");
        Value varValue = symTbl.lookup(varName);
        if(!(varValue.getType() instanceof RefType))
            throw new MyException(varName + "is not of RefType!");
        Value evaluated = expression.eval(symTbl, heap);
        Type locationType = ((RefValue)varValue).getLocationType();
        if(!locationType.equals(evaluated.getType()))
            throw new MyException(varName + " not of type " + evaluated.getType().toString());
        int newPosition = heap.add(evaluated);
        symTbl.put(varName, new RefValue(newPosition, locationType));
        state.setSymTable(symTbl);
        state.setHeap(heap);
        return null;
    }

    @Override
    public IStmt deepCopy(){
        return new NewStmt(varName, expression.deepCopy());
    }

    @Override
    public String toString(){
        return "New(" + varName + ", " + expression.toString() + ")";
    }
}
