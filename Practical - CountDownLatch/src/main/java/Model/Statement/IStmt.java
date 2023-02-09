package Model.Statement;

import Exception.MyException;
import Model.ADT.MyIDictionary;
import Model.PrgState;
import Model.Type.Type;

public interface IStmt {
    MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException;
    PrgState execute(PrgState state) throws MyException; //execution method for a statement
    IStmt deepCopy();
}

