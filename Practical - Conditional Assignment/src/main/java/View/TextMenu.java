package View;

import Exception.MyException;
import Model.ADT.MyDictionary;
import Model.ADT.MyIDictionary;

import java.util.Scanner;

public class TextMenu {
    MyIDictionary<String, Command> commands;

    public TextMenu() {
        this.commands = new MyDictionary<>();
    }

    public void addCommand(Command command) {
        this.commands.put(command.getKey(), command);
    }

    public void printMenu() {
        for (Command command : commands.values()) {
            String line = String.format("%4s: %s", command.getKey(), command.getDescription());
            System.out.println(line);
        }
    }

    public void show() {
        Scanner scanner = new Scanner(System.in);
        printMenu();
        System.out.println("Give option: ");
        String key = scanner.nextLine();
        try {
            Command command = commands.lookup(key);
            command.execute();
        } catch (MyException e) {
            System.out.println("Invalid option!");
        }
    }
}
