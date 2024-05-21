package com.company.command;

import com.company.cli.Command;
import com.company.image.ImageManager;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Monochrome implements Command {


    @Override
    public void execute(String[] args) {
        if (args.length != 0) {
            System.err.println("Command usage: Monochrome");
        } else {
            ImageManager.convertImageToMonochrome();
        }
    }
}
