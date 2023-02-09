package Model.Statement;

import Exception.MyException;
import Model.ADT.MyIDictionary;
import Model.Expressions.IExp;
import Model.PrgState;
import Model.Type.StringType;
import Model.Type.Type;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFile implements IStmt{
    IExp exp;

    public CloseRFile(IExp expression){
        this.exp = expression;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        if (exp.typeCheck(typeEnv).equals(new StringType()))
            return typeEnv;
        else
            throw new MyException("CloseReadFile requires a string expression.");

    }

    @Override
    public PrgState execute(PrgState state) throws MyException{
        Value val = exp.eval(state.getSymTable(), state.getHeap());
        if(!val.getType().equals(new StringType()))
            throw new MyException("Current expression does not evaluate to StringValue!");
        StringValue fileName = (StringValue) val;
        MyIDictionary<String, BufferedReader> fileTable = state.getFileTable();
        if (!fileTable.isDefined(fileName.getValue()))
            throw new MyException("Key is not present in the FileTable!");
        BufferedReader buff = fileTable.lookup(fileName.getValue());
        try {
            buff.close();
        } catch (IOException e) {
            throw new MyException("Unexpected error in closing file!");
        }
        fileTable.remove(fileName.getValue());
        state.setFileTable(fileTable);
        return null;
    }

    @Override
    public String toString() {
        return String.format("CloseReadFile(%s)", exp.toString());
    }

    @Override
    public IStmt deepCopy() {
        return new CloseRFile(exp.deepCopy());
    }
}
