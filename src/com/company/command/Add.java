package com.company.command;

import com.company.cli.Command;
import com.company.session.SessionHelper;
import com.company.session.SessionManager;

public class Add implements Command {
    @Override
    public void execute(String[] args) {
        if(SessionManager.getInstance().getCurrentSession() == null){
            System.err.println("No current session");
        } else if (args.length != 1) {
            System.out.println("Usage: Add <image.ppm>");
        } else {
            SessionHelper.addImageToSession(args[0]);
        }
    }
}
