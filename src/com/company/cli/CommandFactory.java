package com.company.cli;


import com.company.command.*;

public class CommandFactory {
    public static Command receiveCommand(String command) {
        return switch (command.toLowerCase()) {
            case "help" -> new Help();
            case "open" -> new Open();
            case "close" -> new Close();
            case "save" -> new Save();
            case "saveas" -> new Saveas();
            case "exit" -> new Exit();
            case "grayscale" -> new Grayscale();
            case "monochrome" -> new Monochrome();
            case "negative" -> new Negative();
            case "undo" -> new Undo();
            case "load" -> new Load();
            case "add" -> new Add();
            case "switch" -> new Switch();
            case "session" -> new SessionInfo();
            default -> {
                System.out.println("Bad command. Try again or use help.");
                yield null;
            }
        };

    }
}