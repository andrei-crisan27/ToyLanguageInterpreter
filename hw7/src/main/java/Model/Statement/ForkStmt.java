package Model.Statement;

import Exception.MyException;
import Model.ADT.MyDictionary;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIStack;
import Model.ADT.MyStack;
import Model.PrgState;
import Model.Type.Type;
import Model.Value.Value;

import java.util.Map;

public class ForkStmt implements IStmt{
    IStmt statement;

    public ForkStmt(IStmt statement){
        this.statement = statement;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        statement.typeCheck(typeEnv.deepCopy());
        return typeEnv;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException{
        MyIStack<IStmt> newStack = new MyStack<>();
        newStack.push(statement);
        MyIDictionary<String, Value> newSymTable = new MyDictionary<>();
        for(Map.Entry<String, Value> entry: state.getSymTable().getContent().entrySet()){
            newSymTable.put(entry.getKey(), entry.getValue().deepCopy());
        }
        return new PrgState(newStack, newSymTable, state.getOut(), state.getFileTable(), state.getHeap());
    }

    @Override
    public IStmt deepCopy(){
        return new ForkStmt(statement.deepCopy());
    }

    @Override
    public String toString(){
        return "Fork("+statement.toString()+")";
    }
}
