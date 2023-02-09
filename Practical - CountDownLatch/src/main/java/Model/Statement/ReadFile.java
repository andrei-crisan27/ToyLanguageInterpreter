package Model.Statement;

import Exception.MyException;
import Model.ADT.MyIDictionary;
import Model.Expressions.IExp;
import Model.PrgState;
import Model.Type.IntType;
import Model.Type.StringType;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile implements IStmt{
    IExp exp;
    String varName;

    public ReadFile(IExp expression, String name){
        this.exp = expression;
        this.varName = name;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        if (exp.typeCheck(typeEnv).equals(new StringType()))
            if (typeEnv.lookup(varName).equals(new IntType()))
                return typeEnv;
            else
                throw new MyException("ReadFile requires an int as its variable parameter.");
        else
            throw new MyException("ReadFile requires a string as es expression parameter.");
    }

    @Override
    public PrgState execute(PrgState state) throws MyException{
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIDictionary<String, BufferedReader> fileTable = state.getFileTable();
        if(symTable.isDefined(varName)){
            Value val = symTable.lookup(varName);
            if(val.getType().equals(new IntType())){
                val = exp.eval(symTable, state.getHeap());
                if(val.getType().equals(new StringType())){
                    StringValue castValue = (StringValue) val;
                    if(fileTable.isDefined(castValue.getValue())){
                        BufferedReader buff = fileTable.lookup(castValue.getValue());
                        try {
                            String line = buff.readLine();
                            if (line == null)
                                line = "0";
                            symTable.put(varName, new IntValue(Integer.parseInt(line)));
                        } catch (IOException e){
                            throw new MyException("Can't read from this file!");
                        }
                    } else {
                        throw new MyException("The file table does not contain this value!");
                    }
                } else {
                    throw new MyException("This does not evaluate to StringType!");
                }
            } else {
                throw new MyException("The value is not of IntType!");
            }
        } else {
            throw new MyException("The key is not found in the symTable!");
        }
        return null;
    }

    @Override
    public String toString(){
        return String.format("ReadFile(%s, %s)", exp.toString(), varName);
    }

    @Override
    public IStmt deepCopy() {
        return new ReadFile(exp.deepCopy(), varName);
    }
}


