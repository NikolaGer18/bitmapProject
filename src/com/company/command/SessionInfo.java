package com.company.command;

import com.company.cli.Command;
import com.company.session.SessionHelper;

public class SessionInfo implements Command {
    @Override
    public void execute(String[] args)  {
        if(args.length != 1) {
            System.err.println("Command usage: session info");
        } else if (!args[0].equals("info")) {
            System.err.println("Command usage: session info");
        } else  {
            SessionHelper.getSessionInfo();
        }
    }
}
