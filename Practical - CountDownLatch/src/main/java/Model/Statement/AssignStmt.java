package Model.Statement;

import Exception.MyException;
import Model.ADT.MyIDictionary;
import Model.Expressions.IExp;
import Model.PrgState;
import Model.Type.Type;
import Model.Value.Value;

public class AssignStmt implements IStmt {
    String id;
    IExp expression;

    public AssignStmt(String id, IExp expression){
        this.id = id;
        this.expression = expression;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeVar =  typeEnv.lookup(id);
        Type typeExpr = expression.typeCheck(typeEnv);
        if (typeVar.equals(typeExpr))
            return typeEnv;
        else
            throw new MyException("Assignment: right hand side and left hand side have different types.");
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        if (symTbl.isDefined(id)) {
            Value val = expression.eval(symTbl, state.getHeap());
            Type typId = (symTbl.lookup(id)).getType();
            if (val.getType().equals(typId)) {
                symTbl.update(id, val);
            } else
                    throw new MyException("Declared type of variable " + id + " and type of the assigned expression do not match");
        } else throw new MyException("The used variable " + id + "was not declared before");
        state.setSymTable(symTbl);
        return null;
    }

    @Override
    public String toString() {
        return id + "=" + expression.toString();
    }

    @Override
    public IStmt deepCopy() {
        return new AssignStmt(id, expression.deepCopy());
    }
}
