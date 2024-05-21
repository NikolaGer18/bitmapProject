package com.company.command;
import com.company.cli.Command;
import com.company.transformation.TransformationManager;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class Undo implements Command {


    @Override
    public void execute(String[] args) {
        if (args.length != 0) {
            System.out.println("Command usage undo");
        } else {
            TransformationManager.undoTransformation();
        }
    }
}