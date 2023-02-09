package Model.Statement;

import Exception.MyException;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIList;
import Model.Expressions.IExp;
import Model.PrgState;
import Model.Type.Type;
import Model.Value.Value;

public class PrintStmt implements IStmt{
    IExp exp;

    public PrintStmt(IExp expression){
        this.exp = expression;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        exp.typeCheck(typeEnv);
        return typeEnv;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException{
        MyIList<Value> out = state.getOut();
        out.add(exp.eval(state.getSymTable(), state.getHeap()));
        state.setOut(out);
        return null;
    }

    @Override
    public String toString(){
        return "print("+exp.toString()+")";
    }

    @Override
    public IStmt deepCopy() {
        return new PrintStmt(exp.deepCopy());
    }
}
