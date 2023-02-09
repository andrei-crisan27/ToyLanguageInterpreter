package View;

import Controller.Controller;
import Exception.MyException;
import Model.ADT.*;
import Model.Expressions.*;
import Model.PrgState;
import Model.Statement.*;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.RefType;
import Model.Type.StringType;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Repository.IRepo;
import Repository.Repo;

import java.io.IOException;


public class Interpreter {
    public static void main(String[] args) throws IOException, MyException {
        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));

        IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new
                        VarExp("v"))));
        try {
            ex1.typeCheck(new MyDictionary<>());
            PrgState prg1 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex1, new MyDictionary<>(), new MyHeap(), new MyLatchTable());
            IRepo repo1;
            repo1 = new Repo(prg1, "log1.txt");
            Controller controller1 = new Controller(repo1);
            menu.addCommand(new RunExample("1", ex1.toString(), controller1));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        IStmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()),
                new CompStmt(new VarDeclStmt("b", new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithExp('+', new ValueExp(new IntValue(2)), new
                                ArithExp('*', new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                new CompStmt(new AssignStmt("b", new ArithExp('+', new VarExp("a"), new ValueExp(new
                                        IntValue(1)))), new PrintStmt(new VarExp("b"))))));
            try {
                ex2.typeCheck(new MyDictionary<>());
                PrgState prg2 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex2, new MyDictionary<>(), new MyHeap(), new MyLatchTable());
                IRepo repo2;
                repo2 = new Repo(prg2, "log2.txt");
                Controller controller2 = new Controller(repo2);
                menu.addCommand(new RunExample("2", ex2.toString(), controller2));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        IStmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"),
                                        new AssignStmt("v", new ValueExp(new IntValue(2))),
                                        new AssignStmt("v", new ValueExp(new IntValue(3)))),
                                        new PrintStmt(new VarExp("v"))))));
            try {
                ex3.typeCheck(new MyDictionary<>());
                PrgState prg3 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex3, new MyDictionary<>(), new MyHeap(), new MyLatchTable());
                IRepo repo3;
                repo3 = new Repo(prg3, "log3.txt");
                Controller controller3 = new Controller(repo3);
                menu.addCommand(new RunExample("3", ex3.toString(), controller3));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        IStmt ex4 = new CompStmt(new VarDeclStmt("varf", new StringType()),
                new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("test.txt"))),
                        new CompStmt(new OpenRFile(new VarExp("varf")),
                                new CompStmt(new VarDeclStmt("varc", new IntType()),
                                        new CompStmt(new ReadFile(new VarExp("varf"), "varc"),
                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                        new CompStmt(new ReadFile(new VarExp("varf"), "varc"),
                                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                                        new CloseRFile(new VarExp("varf"))))))))));

            try {
                ex4.typeCheck(new MyDictionary<>());
                PrgState prg4 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex4, new MyDictionary<>(), new MyHeap(), new MyLatchTable());
                IRepo repo4;
                repo4 = new Repo(prg4, "log4.txt");
                Controller controller4 = new Controller(repo4);
                menu.addCommand(new RunExample("4", ex4.toString(), controller4));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        //Example: int v; v=4; (while (v>0) print(v);v=v-1);print(v)
        IStmt ex5 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(4))),
                        new CompStmt(new WhileStmt(new RelationalExpression(new VarExp("v"), new ValueExp(new IntValue(0)), ">"),
                                new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v",new ArithExp('-', new VarExp("v"), new ValueExp(new IntValue(1)))))),
                                new PrintStmt(new VarExp("v")))));
            try {
                ex5.typeCheck(new MyDictionary<>());
                PrgState prg5 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex5, new MyDictionary<>(), new MyHeap(), new MyLatchTable());
                IRepo repo5;
                repo5 = new Repo(prg5, "log5.txt");
                Controller controller5 = new Controller(repo5);
                menu.addCommand(new RunExample("5", ex5.toString(), controller5));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        //Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)))
        IStmt ex6 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewStmt("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new VarExp("v")),
                                                new PrintStmt(new VarExp("a")))))));
            try {
                ex6.typeCheck(new MyDictionary<>());
                PrgState prg6 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex6, new MyDictionary<>(), new MyHeap(), new MyLatchTable());
                IRepo repo6;
                repo6 = new Repo(prg6, "log6.txt");
                Controller controller6 = new Controller(repo6);
                menu.addCommand(new RunExample("6", ex6.toString(), controller6));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        //Ref int v;new(v,20);Ref Ref int a; new(a,v);print(v);print(a)
        //At the end of the execution: Heap={1->20, 2->(1,int)}, SymTable={v->(1,int), a->(2,Ref int)} and Out={(1,int),(2,Ref int)}
        IStmt ex7 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewStmt("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new VarExp("v")),
                                                new PrintStmt(new VarExp("a")))))));
        try {
            ex7.typeCheck(new MyDictionary<>());
            PrgState prg7 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex7, new MyDictionary<>(), new MyHeap(), new MyLatchTable());
            IRepo repo7 = new Repo(prg7, "log7.txt");
            Controller controller7 = new Controller(repo7);
            menu.addCommand(new RunExample("7", ex7.toString(), controller7));
        } catch(IOException e){
            System.out.println(e.getMessage());
        }

        //Ref int v;new(v,20);Ref Ref int a; new(a,v);print(rH(v));print(rH(rH(a))+5)
        //At the end of the execution: Heap={1->20, 2->(1,int)}, SymTable={v->(1,int), a->(2,Ref int)} and Out={20, 25}
        IStmt ex8=new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewStmt("a",new VarExp("v")),
                                        new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                                                new PrintStmt(new ArithExp('+',new ReadHeapExp(new ReadHeapExp(new VarExp("a"))), new ValueExp(new IntValue(5)))))))));
        try {
            ex8.typeCheck(new MyDictionary<>());
            PrgState prg8 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex8, new MyDictionary<>(), new MyHeap(), new MyLatchTable());
            IRepo repo8 = new Repo(prg8, "log8.txt");
            Controller controller8 = new Controller(repo8);
            menu.addCommand(new RunExample("8", ex8.toString(), controller8));
        } catch(IOException e)
        {
            System.out.println(e.getMessage());
        }

        //Ref int v;new(v,20);print(rH(v)); wH(v,30);print(rH(v)+5);
        //At the end of the execution: Heap={1->30}, SymTable={v->(1,int)} and Out={20, 35}
        IStmt ex9 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt( new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                                new CompStmt(new WriteHeapStmt("v", new ValueExp(new IntValue(30))),
                                        new PrintStmt(new ArithExp('+', new ReadHeapExp(new VarExp("v")), new ValueExp(new IntValue(5))))))));
        try {
            ex9.typeCheck(new MyDictionary<>());
            PrgState prg9 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex9, new MyDictionary<>(), new MyHeap(), new MyLatchTable());
            IRepo repo9 = new Repo(prg9, "log9.txt");
            Controller controller9 = new Controller(repo9);
            menu.addCommand(new RunExample("9", ex9.toString(), controller9));
        } catch (IOException e){
            System.out.println(e.getMessage());
        }

        //int v; Ref int a; v=10;new(a,22);
        // fork(wH(a,30);v=32;print(v);print(rH(a)));
        // print(v);print(rH(a))
        //At the end:
        //Id=1
        //SymTable_1={v->10,a->(1,int)}
        //Id=10
        //SymTable_10={v->32,a->(1,int)}
        //Heap={1->30}
        //Out={10,30,32,30}
        IStmt ex10 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),
                                new CompStmt(new NewStmt("a", new ValueExp(new IntValue(22))),
                                        new CompStmt(new ForkStmt(new CompStmt(new WriteHeapStmt("a", new ValueExp(new IntValue(30))),
                                                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))),
                                                        new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp(new VarExp("a"))))))),
                                                new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp(new VarExp("a")))))))));
        try {
            ex10.typeCheck(new MyDictionary<>());
            PrgState prg10 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex10, new MyDictionary<>(), new MyHeap(), new MyLatchTable());
            IRepo repo10;
            repo10 = new Repo(prg10, "log10.txt");
            Controller controller10 = new Controller(repo10);
            menu.addCommand(new RunExample("10", ex10.toString(), controller10));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        menu.show();
    }
}
