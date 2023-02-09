package Model.ADT;

import Exception.MyException;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface MyIDictionary<T1,T2>{
    boolean isDefined(T1 key);
    void put(T1 key, T2 value);
    T2 lookup(T1 key) throws MyException;
    void update(T1 key, T2 value) throws MyException;
    Collection<T2> values();
    void remove(T1 key) throws MyException;
    Map<T1, T2> getContent();
    Set<T1> keySet();
    MyIDictionary<T1, T2> deepCopy() throws MyException;
}
