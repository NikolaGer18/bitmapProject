package com.company.transformation;

import com.company.image.Image;
import com.company.image.ImageManager;
import com.company.session.Session;
import com.company.session.SessionManager;

public class TransformationManager {

    public static void undoTransformation() {
        if (SessionManager.getInstance().getCurrentSession() == null) {
            System.err.println("No current session");
        } else {
            Session currentSession = SessionManager.getInstance().getCurrentSession();
            if (currentSession.getTransformations().isEmpty()) {
                System.err.println("No transformations used in this session!");
            } else {
                currentSession.getTransformations().remove(currentSession.getTransformations().size() - 1);
                SessionManager.getInstance().setCurrentSession(currentSession);
                System.out.println("Successfully undo transformation");
            }
        }
    }

    public static void useTransformation(String transformationType, Image image, String fileName) {
        switch (transformationType) {
            case "monochrome" -> ImageManager.convertImageToMonochrome(image, fileName);
            case "grayscale" -> ImageManager.convertImageToGrayscale(image, fileName);
            case "negative" -> ImageManager.convertImageToNegative(image, fileName);
            default -> System.out.println("Invalid transformation type");
        }
    }

    public static void useTransformation(String transformationType, Image image) {
        switch (transformationType) {
            case "monochrome" -> ImageManager.convertImageToMonochrome(image);
            case "grayscale" -> ImageManager.convertImageToGrayscale(image);
            case "negative" -> ImageManager.convertImageToNegative(image);
            default -> System.out.println("Invalid transformation type");
        }
    }
}
