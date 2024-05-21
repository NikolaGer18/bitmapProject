package com.company.command;

import com.company.cli.Command;

public class Help implements Command {
    @Override
    public void execute(String[] args) {
            if (args.length != 0) {
                System.out.println("The command is Help");
            } else {
                System.out.println("""
                        The following commands are supported:
                        open | <file> opens <file>
                        close | closes currently opened file
                        save | saves the currently open file
                        saveas | <file> saves the currently open file in <file>
                        help | prints this information
                        exit | exists the program""");
            }
        }
    }

