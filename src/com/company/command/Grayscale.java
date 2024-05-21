package com.company.command;

import com.company.cli.Command;
import com.company.image.ImageManager;


public class Grayscale implements Command {
    @Override
    public void execute(String[] args) {
            if (args.length != 0) {
                System.out.println("The command is grayscale");
            } else {
                ImageManager.convertImageToGrayscale();
            }
    }
}
