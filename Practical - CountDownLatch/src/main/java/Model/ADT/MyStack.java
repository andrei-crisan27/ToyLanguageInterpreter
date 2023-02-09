package Model.ADT;

import Exception.MyException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class MyStack<T> implements MyIStack<T> {
    Stack<T> stack;

    public MyStack() {
        this.stack = new Stack<>();
    }

    @Override
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    @Override
    public T pop() throws MyException {
        if (stack.isEmpty())
            throw new MyException("Stack is empty!");
        return this.stack.pop();
    }

    @Override
    public void push(T v) {
        this.stack.push(v);
    }

    @Override
    public String toString() {
        return this.stack.toString();
    }
    @Override
    public List<T> getReversed() {
        List<T> list = Arrays.asList((T[]) stack.toArray());
        Collections.reverse(list);
        return list;
    }
}
