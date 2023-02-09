package Repository;

import Exception.MyException;
import Model.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repo implements IRepo {
    List<PrgState> programStates;
    String logFilePath;

    public Repo(PrgState state, String filePath) throws IOException {
        this.logFilePath = filePath;
        this.programStates = new ArrayList<>();
        this.addProgram(state);
        this.emptyLogFile();
    }

    @Override
    public void logPrgStateExec(PrgState programState) throws MyException, IOException {
        PrintWriter logFile;
        logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        logFile.println(programState.toString());
        logFile.close();
    }

    @Override
    public void emptyLogFile() throws IOException {
        PrintWriter logFile;
        logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, false)));
        logFile.close();
    }

    @Override
    public void addProgram(PrgState program) {
        this.programStates.add(program);
    }

    @Override
    public List<PrgState> getProgramList() {
        return this.programStates;
    }

    @Override
    public void setProgramStates(List<PrgState> programStates) {
        this.programStates = programStates;
    }
}
