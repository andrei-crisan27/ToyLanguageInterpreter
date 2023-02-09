package Model;

import Exception.MyException;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.ADT.MyIList;
import Model.ADT.MyIStack;
import Model.Statement.IStmt;
import Model.Value.Value;

import java.io.BufferedReader;

public class PrgState {
    MyIStack<IStmt> exeStack;
    MyIDictionary<String, Value> symTable;
    MyIList<Value> out;
    MyIDictionary<String, BufferedReader> fileTable;
    MyIHeap heap;
    IStmt originalProgram;
    int id;
    static int lastId = 0;

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Value> symtbl, MyIList<Value> ot, IStmt program, MyIDictionary<String, BufferedReader> fileT, MyIHeap h) {
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        fileTable = fileT;
        heap = h;
        this.originalProgram = program.deepCopy();
        this.exeStack.push(this.originalProgram);
        this.id = setId();
    }

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Value> symtbl, MyIList<Value> ot, MyIDictionary<String, BufferedReader> fileT, MyIHeap h) {
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        fileTable = fileT;
        heap = h;
        this.id = setId();
    }

    public synchronized int setId() {
        lastId++;
        return lastId;
    }

    public void setExeStack(MyIStack<IStmt> newStack) {
        this.exeStack = newStack;
    }

    public void setSymTable(MyIDictionary<String, Value> newSymTable) {
        this.symTable = newSymTable;
    }

    public void setOut(MyIList<Value> newOut) {
        this.out = newOut;
    }

    public void setFileTable(MyIDictionary<String, BufferedReader> newFileTable) {
        this.fileTable = newFileTable;
    }

    public void setHeap(MyIHeap heap) {
        this.heap = heap;
    }

    public MyIStack<IStmt> getStk() {
        return exeStack;
    }

    public MyIDictionary<String, Value> getSymTable() {
        return symTable;
    }

    public MyIList<Value> getOut() {
        return out;
    }

    public MyIDictionary<String, BufferedReader> getFileTable() {
        return fileTable;
    }

    public MyIHeap getHeap() {
        return heap;
    }

    public boolean isNotCompleted() {
        return exeStack.isEmpty();
    }

    public PrgState oneStep() throws MyException {
        if (exeStack.isEmpty())
            throw new MyException("Program state stack is empty!");
        IStmt currentStatement = exeStack.pop();
        return currentStatement.execute(this);
    }

    @Override
    public String toString() {
        return "ID: " + this.id + "\nExecution stack: " + exeStack.toString() + "\nSymbol table: " + symTable.toString() + "\nOutput list: " + out.toString()+"\nFile table:" + fileTable.toString()+"\nHeap memory:" + heap.toString()+"\n";
    }

    public int getId() {
        return this.id;
    }
}
