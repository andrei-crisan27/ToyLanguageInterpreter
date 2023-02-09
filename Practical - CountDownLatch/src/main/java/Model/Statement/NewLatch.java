package Model.Statement;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.ADT.MyILatchTable;
import Model.Expressions.IExp;
import Model.PrgState;
import Model.Type.IntType;
import Model.Type.Type;
import Exception.MyException;
import Model.Value.IntValue;
import Model.Value.Value;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewLatch implements IStmt{
    private final String var;
    private final IExp expression;
    private static final Lock lock = new ReentrantLock();

    public NewLatch(String var, IExp expression) {
        this.var = var;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        lock.lock();
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIHeap heap = state.getHeap();
        MyILatchTable latchTable = state.getLatchTable();
        if(expression.eval(symTable, heap).getType().equals(new IntType())) {
            IntValue nr = (IntValue) (expression.eval(symTable, heap));
            int number = nr.getVal();
            int freeAddress = latchTable.getFreeAddress();
            latchTable.put(freeAddress, number);
            if (symTable.isDefined(var)) {
                symTable.update(var, new IntValue(freeAddress));
            } else {
                throw new MyException("This symbol is not defined in the symTbl!");
            }
        } else {
            throw new MyException("The result of the evaluation is not an integer!");
        }
        lock.unlock();
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        if (typeEnv.lookup(var).equals(new IntType())) {
            if (expression.typeCheck(typeEnv).equals(new IntType())) {
                return typeEnv;
            } else {
                throw new MyException("Expression doesn't have the int type!");
            }
        } else {
            throw new MyException("var is not of int type!");
        }
    }

    @Override
    public IStmt deepCopy() {
        return new NewLatch(var, expression.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("newLatch(%s, %s)", var, expression);
    }
}
