package com.company.command;

import com.company.cli.Command;
import com.company.io.FileManager;

public class Saveas implements Command {

    @Override
    public void execute(String[] args){
        if (args.length != 1) {
            System.err.println("Command usage: saveas <filename.ppm>");
        } else {
            FileManager.saveAs(args[0]);
        }
    }
}
