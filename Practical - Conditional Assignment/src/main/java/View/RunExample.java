package View;

import Controller.Controller;
import Exception.MyException;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class RunExample extends Command{
    Controller ctrl;

    public RunExample(String key, String description, Controller controller){
        super(key, description);
        this.ctrl = controller;
    }

    @Override
    public void execute(){
        try{
            System.out.println("Do you want to display the steps? Y/N");
            Scanner readOption = new Scanner(System.in);
            String option = readOption.next();
            ctrl.setDisplayFlag(Objects.equals(option, "Y"));
            ctrl.allStep();
        }
        catch (MyException | IOException | InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}
