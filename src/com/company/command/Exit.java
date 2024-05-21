package com.company.command;

import com.company.cli.Command;

public class Exit implements Command {
    @Override
    public void execute(String[] args) {
        if (args.length != 0) {
            System.out.println("The command is Help");
        } else {
            System.exit(0);
        }
    }
}
