package Model.Statement;

import Exception.MyException;
import Model.ADT.MyIDictionary;
import Model.PrgState;
import Model.Type.Type;

public class NopStmt implements IStmt{
    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public PrgState execute(PrgState state){
        return null;
    }

    @Override
    public String toString(){
        return "NopStatement";
    }

    @Override
    public IStmt deepCopy() {
        return new NopStmt();
    }
}
