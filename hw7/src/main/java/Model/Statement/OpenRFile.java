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
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenRFile implements IStmt {
    IExp exp;

    public OpenRFile(IExp expression) {
        this.exp = expression;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        if (exp.typeCheck(typeEnv).equals(new StringType()))
            return typeEnv;
        else
            throw new MyException("OpenReadFile requires a string expression.");
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        Value val = exp.eval(state.getSymTable(), state.getHeap());
        if (val.getType().equals(new StringType())) {
            StringValue fileName = (StringValue) val;
            MyIDictionary<String, BufferedReader> fileTable = state.getFileTable();
            if (!fileTable.isDefined(fileName.getValue())) {
                BufferedReader buff;
                try {
                    buff = new BufferedReader(new FileReader(fileName.getValue()));
                } catch (FileNotFoundException e) {
                    throw new MyException("The file can't be opened!");
                }
                fileTable.put(fileName.getValue(), buff);
                state.setFileTable(fileTable);
            } else {
                throw new MyException("The file is already opened!");
            }
        } else {
            throw new MyException("Type not matched! It should have a StringType!");
        }
        return null;
    }

    @Override
    public String toString(){
        return String.format("OpenRFile(%s)", exp.toString());
    }

    @Override
    public IStmt deepCopy() {
        return new OpenRFile(exp.deepCopy());
    }
}
