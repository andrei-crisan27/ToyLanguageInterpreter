package Model.Statement;

import Exception.MyException;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIStack;
import Model.Expressions.IExp;
import Model.PrgState;
import Model.Type.BoolType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.Value;

public class IfStmt implements IStmt{
    IExp exp;
    IStmt thenS;
    IStmt elseS;

    public IfStmt(IExp e, IStmt t, IStmt el){
        exp = e;
        thenS = t;
        elseS = el;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeExpr = exp.typeCheck(typeEnv);
        if (typeExpr.equals(new BoolType())) {
            thenS.typeCheck(typeEnv.deepCopy());
            elseS.typeCheck(typeEnv.deepCopy());
            return typeEnv;
        } else
            throw new MyException("The condition of IF does not have the type Bool.");
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        Value result = exp.eval(state.getSymTable(), state.getHeap());
        if(result instanceof BoolValue b) {
            IStmt stmt;
            if (b.getVal()) {
                stmt = thenS;
            } else {
                stmt = elseS;
            }
            MyIStack<IStmt> stack = state.getStk();
            stack.push(stmt);
            state.setExeStack(stack);
            return null;
        } else{
            throw new MyException("Invalid if statement.");
        }
    }

    @Override
    public String toString(){
        return "(IF("+exp.toString()+") THEN ("+thenS.toString()+") ELSE ("+elseS.toString()+"))";
    }

    @Override
    public IStmt deepCopy() {
        return new IfStmt(exp.deepCopy(),thenS.deepCopy(),elseS.deepCopy());
    }
}
