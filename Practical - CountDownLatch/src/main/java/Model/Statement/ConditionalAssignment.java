package Model.Statement;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIStack;
import Model.Expressions.IExp;
import Model.Expressions.VarExp;
import Model.PrgState;
import Model.Type.BoolType;
import Model.Type.Type;
import Exception.MyException;

public class ConditionalAssignment implements IStmt{
    private final String variable;
    private final IExp expression1;
    private final IExp expression2;
    private final IExp expression3;

    public ConditionalAssignment(String v, IExp exp1, IExp exp2, IExp exp3){
        this.variable = v;
        this.expression1 = exp1;
        this.expression2 = exp2;
        this.expression3 = exp3;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type variableType = new VarExp(variable).typeCheck(typeEnv);
        Type type1 = expression1.typeCheck(typeEnv);
        Type type2 = expression2.typeCheck(typeEnv);
        Type type3 = expression3.typeCheck(typeEnv);
        if (type1.equals(new BoolType()) && type2.equals(variableType) && type3.equals(variableType))
            return typeEnv;
        else
            throw new MyException("The conditional assignment is invalid!");
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> exeStack = state.getStk();
        IStmt converted = new IfStmt(expression1, new AssignStmt(variable, expression2), new AssignStmt(variable, expression3));
        exeStack.push(converted);
        state.setExeStack(exeStack);
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new ConditionalAssignment(variable, expression1.deepCopy(), expression2.deepCopy(), expression3.deepCopy());
    }

    @Override
    public String toString(){
        return String.format("%s=(%s)? %s: %s", variable, expression1, expression2, expression3);
    }
}
