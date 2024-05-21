package com.company.image;

import com.company.converter.NegativeConverter;
import com.company.converter.PPMToGrayscale;
import com.company.converter.PPMToMonochrome;
import com.company.session.Session;
import com.company.session.SessionManager;
import com.company.transformation.Transformation;
import com.company.transformation.TransformationType;
import com.company.utils.AppConstants;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class ImageManager {

    public static void convertImageToGrayscale() {

        if (SessionManager.getInstance().getCurrentSession() == null) {
            System.out.println("Please start the session first");
        } else {
            Session currentSession = SessionManager.getInstance().getCurrentSession();
            List<Image> filteredImages = currentSession.getImages();
            Random random = new Random();
            try {
                for (Image image : filteredImages) {
                    PPMToGrayscale.convertToGrayscale(image.path(),
                            AppConstants.TEMP_DIR.getStringValue() + "t_" + random.nextInt(99999)
                                    + image.fileName());
                }
                currentSession.getTransformations().add(new Transformation(TransformationType.GRAYSCALE.getName()));
                SessionManager.getInstance().setCurrentSession(currentSession);
                System.out.println("Great Success!");
            } catch (IOException e) {
                System.out.println("Error occurred: " + e.getMessage());
            }
        }
    }

    public static void convertImageToGrayscale(Image image, String fileName) {
            try {
                PPMToGrayscale.convertToGrayscale2(image.path(),
                            AppConstants.IMAGE_DIR.getStringValue() + fileName);
                System.out.println("Great Success!");
            } catch (IOException e) {
                System.out.println("Error occurred: " + e.getMessage());
            }
    }

    public static void convertImageToGrayscale(Image image) {
        try {
            PPMToGrayscale.convertToGrayscale2(image.path(),
                    image.path());
            System.out.println("Great Success!");
        } catch (IOException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }

    public static void convertImageToMonochrome() {
        if (SessionManager.getInstance().getCurrentSession() == null) {
            System.out.println("Please start the session first");
        } else {
            Session currentSession = SessionManager.getInstance().getCurrentSession();
            List<Image> filteredImages = currentSession.getImages();
            try {
                Random random = new Random();
                for (Image image : filteredImages) {
                    PPMToMonochrome.convertToMonochrome(image.path(), AppConstants.TEMP_DIR.getStringValue()
                            + "t_" + random.nextInt(99999) +
                                    image.fileName());
                }
                currentSession.getTransformations().add(new Transformation(TransformationType.MONOCHROME.getName()));
                SessionManager.getInstance().setCurrentSession(currentSession);
            } catch (IOException e) {
                System.out.println("Error occurred: " + e.getMessage());
            }
        }
    }

    public static void convertImageToMonochrome(Image image, String fileName) {
            try {
                    PPMToMonochrome.convertToMonochrome2(image.path(),
                            AppConstants.IMAGE_DIR.getStringValue() + fileName);
                    System.out.println("Successfully added monochrome filter");
            } catch (IOException e) {
                System.out.println("Error occurred: " + e.getMessage());
            }

    }

    public static void convertImageToMonochrome(Image image) {
        try {
            PPMToMonochrome.convertToMonochrome2(image.path(), image.path());
            System.out.println("Successfully added monochrome filter");
        } catch (IOException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }

    public static void convertImageToNegative() {
        if (SessionManager.getInstance().getCurrentSession() == null) {
            System.out.println("Please start the session first");
        } else {
            Session currentSession = SessionManager.getInstance().getCurrentSession();
            List<Image> filteredImages = currentSession.getImages();
            Random random = new Random();
            for (Image image : filteredImages) {
                NegativeConverter.convertToNegative(image.path(),
                        AppConstants.TEMP_DIR.getStringValue() + "t_" + random.nextInt(99999) + image.fileName());
            }
            currentSession.getTransformations().add(new Transformation(TransformationType.NEGATIVE.getName()));
            SessionManager.getInstance().setCurrentSession(currentSession);
            System.out.println("Great Success!");
        }
    }

    public static void convertImageToNegative(Image image, String fileName) {
        NegativeConverter.convertToNegative(image.path(),
                AppConstants.IMAGE_DIR.getStringValue() + fileName);
        System.out.println("Great Success!");
    }

    public static void convertImageToNegative(Image image) {
        NegativeConverter.convertToNegative(image.path());
        System.out.println("Great Success!");
    }
}
