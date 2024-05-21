package com.company.command;

import com.company.cli.Command;
import com.company.session.SessionHelper;

public class Switch implements Command {
    @Override
    public void execute(String[] args)  {
        if (args.length != 1) {
            System.out.println("Usage: Switch <id>");
        } else  {
            SessionHelper.switchToSession(args[0]);
        }
    }
}
