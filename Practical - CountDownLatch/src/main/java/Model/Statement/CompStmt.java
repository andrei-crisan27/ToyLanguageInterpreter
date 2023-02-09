package Model.Statement;

import Exception.MyException;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIStack;
import Model.PrgState;
import Model.Type.Type;

public class CompStmt implements  IStmt{
    IStmt first;
    IStmt snd;

    public CompStmt(IStmt firstStatement, IStmt secondStatement){
        this.first = firstStatement;
        this.snd = secondStatement;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return snd.typeCheck(first.typeCheck(typeEnv));
    }

    @Override
    public PrgState execute(PrgState state) throws MyException{
        MyIStack<IStmt> stk = state.getStk();
        stk.push(snd);
        stk.push(first);
        state.setExeStack(stk);
        return null;
    }

    @Override
    public String toString(){
        return "("+first.toString()+";"+snd.toString()+")";
    }

    @Override
    public IStmt deepCopy() {
        return new CompStmt(first.deepCopy(),snd.deepCopy());
    }
}
