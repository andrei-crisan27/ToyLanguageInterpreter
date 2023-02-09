package Model.Expressions;


import Exception.MyException;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Type.Type;
import Model.Value.Value;

public interface IExp {
    Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException;
    Value eval(MyIDictionary<String, Value> tbl, MyIHeap heap) throws MyException;
    IExp deepCopy();
}
