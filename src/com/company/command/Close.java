package com.company.command;

import com.company.cli.Command;
import com.company.session.SessionManager;

public class Close implements Command {
    @Override
    public void execute(String[] args) {
        if (!SessionManager.getInstance().isFileOpened()) {
            System.err.println("No file Opened");
        } else if (args.length != 0) {
            System.err.println("Command usage: close");
        } else {
            SessionManager.getInstance().setCurrentSession(null);
            SessionManager.getInstance().getSessions().clear();
            SessionManager.getInstance().setFileOpened(false);
            System.out.println("File closed");
        }
    }
}
