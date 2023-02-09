package Repository;

import Exception.MyException;
import Model.PrgState;

import java.io.IOException;
import java.util.List;

public interface IRepo {
    void logPrgStateExec(PrgState programState) throws MyException, IOException;
    void emptyLogFile() throws IOException;
    void addProgram(PrgState program);
    List<PrgState> getProgramList();
    void setProgramStates(List<PrgState> programStates);
}
