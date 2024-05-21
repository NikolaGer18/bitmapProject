package com.company.command;

import com.company.cli.Command;
import com.company.io.XmlRead;
import com.company.session.SessionManager;

public class Open implements Command {

    @Override
    public void execute(String[] args) {
        if (SessionManager.getInstance().isFileOpened()) {
            System.err.println("File already opened!");
        } else if (args.length != 1) {
            System.err.println("Usage: open <file_name.ppm>");
        } else {
            XmlRead.read(args[0]);
        }
    }
}
