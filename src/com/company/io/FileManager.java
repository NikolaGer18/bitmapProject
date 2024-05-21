package com.company.io;

import com.company.image.Image;
import com.company.session.SessionManager;
import com.company.transformation.Transformation;
import com.company.transformation.TransformationManager;
import com.company.utils.AppConstants;

import java.util.List;

public class FileManager {
    public static void saveAs(String newFileName) {
        if (newFileName.isEmpty()) {
            System.err.println("File name cannot be empty");
         } else if (!newFileName.endsWith(".ppm")) {
            System.err.println("File name must end with .ppm");
         } else if (SessionManager.getInstance().getCurrentSession() == null) {
            System.err.println("No current session found");
         } else {
            List<Image> images = SessionManager.getInstance().getCurrentSession().getImages();
            Image image = images.get(images.size() - 1);
            boolean first = true;
            Image newImage = new Image(AppConstants.IMAGE_DIR.getStringValue() + newFileName, newFileName);
            if (image.fileName().equalsIgnoreCase(newFileName)) {
                System.err.println("File already exists with name " + newFileName);
            } else {
                for (Transformation t: SessionManager.getInstance().getCurrentSession().getTransformations()) {
                    if (first) {
                        TransformationManager.useTransformation(t.getType(), image, newFileName);
                        first = false;
                    } else {
                        TransformationManager.useTransformation(t.getType(), newImage, newFileName);
                    }
                }
            }
         }
        System.out.println("Successfully saved the file " + newFileName);
    }

    public static void save() {
        if (SessionManager.getInstance().getCurrentSession() == null) {
            System.err.println("No current session found");
        } else {
            if (SessionManager.getInstance().getCurrentSession().getImages().isEmpty()) {
                System.err.println("No images found");
            } else {
                List<Image> images = SessionManager.getInstance().getCurrentSession().getImages();
                for (Image i: images) {
                    for (Transformation t: SessionManager.getInstance().getCurrentSession().getTransformations()) {
                        TransformationManager.useTransformation(t.getType(), i);
                    }
                }
            }
        }
    }
}
