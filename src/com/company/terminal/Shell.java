package com.company.terminal;

import com.company.cli.Command;
import com.company.cli.CommandFactory;

import java.util.Scanner;

public class Shell {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome");
        System.out.println("Input open <file> down below!");
        while(true){
            System.out.print(">");
            String userInput = scanner.nextLine();
            String[] splitInput = userInput.split(" ");
            String commandName = splitInput[0];
            String[] commandArgs = new String[splitInput.length-1];
            System.arraycopy(splitInput,1,commandArgs,0,commandArgs.length);
            if (commandArgs.length == 0) {
                commandArgs = new String[]{};
            }
            Command command = CommandFactory.receiveCommand(commandName);
            if(command!=null) {
                command.execute(commandArgs);
            }
            else {
                System.out.println("Bad command. Try again or use help.");
            }
        }
    }
}




