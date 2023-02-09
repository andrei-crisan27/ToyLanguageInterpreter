package Model.Statement;

import Exception.MyException;
import Model.ADT.MyIDictionary;
import Model.PrgState;
import Model.Type.Type;
import Model.Value.Value;

public class VarDeclStmt implements IStmt{
    String name;
    Type type;

    public VarDeclStmt(String name, Type type){
        this.name = name;
        this.type = type;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        typeEnv.put(name, type);
        return typeEnv;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException{
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        if(symTbl.isDefined(name)){
            throw new MyException("Variable " + name + " already exists in the symTable.");
        }
        symTbl.put(name, type.defaultValue());
        state.setSymTable(symTbl);
        return null;
    }

    @Override
    public String toString(){
        return type.toString() + " " + name;
    }

    @Override
    public IStmt deepCopy() {
        return new VarDeclStmt(name, type);
    }
}
