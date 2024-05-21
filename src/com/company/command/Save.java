package com.company.command;

import com.company.cli.Command;
import com.company.io.FileManager;

public class Save implements Command {
    @Override
    public void execute(String[] args)  {
        if (args.length != 0) {
            System.err.println("Command usage: save");
        } else {
            FileManager.save();
        }
    }
}
