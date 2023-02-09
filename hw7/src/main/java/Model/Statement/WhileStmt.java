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

public class WhileStmt implements IStmt{
    IExp expression;
    IStmt statement;

    public WhileStmt(IExp expression, IStmt statement){
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeExpr = expression.typeCheck(typeEnv);
        if (typeExpr.equals(new BoolType())) {
            statement.typeCheck(typeEnv.deepCopy());
            return typeEnv;
        } else
            throw new MyException("The condition of WHILE does not have the type Bool.");
    }

    @Override
    public PrgState execute(PrgState state) throws MyException{
        Value value = expression.eval(state.getSymTable(), state.getHeap());
        MyIStack<IStmt> stack = state.getStk();
        if(!value.getType().equals(new BoolType()))
            throw new MyException(value + "is not of BoolType!");
        BoolValue boolValue = (BoolValue) value;
        if(boolValue.getVal()){
            stack.push(this);
            stack.push(statement);
        }
        return null;
    }

    @Override
    public IStmt deepCopy(){
        return new WhileStmt(expression.deepCopy(), statement.deepCopy());
    }

    @Override
    public String toString(){
        return "while(" + expression + ") {" + statement + "}";
    }
}
