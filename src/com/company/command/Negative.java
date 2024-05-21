package com.company.command;
import com.company.cli.Command;
import com.company.image.ImageManager;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class Negative implements Command {


    @Override
    public void execute(String[] args) {
        if (args.length !=0) {
            System.out.println("Command usage negative");
        } else {
            ImageManager.convertImageToNegative();
        }
    }
}
