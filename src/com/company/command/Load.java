package com.company.command;

import com.company.cli.Command;
import com.company.session.SessionHelper;

public class Load implements Command {
    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: load <file.ppm>");
        } else  {
            SessionHelper.loadSession(args[0]);
        }
    }
}
