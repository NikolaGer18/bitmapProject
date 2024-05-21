package com.company.session;

import com.company.image.Image;
import com.company.utils.AppConstants;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SessionHelper {

    public static void loadSession(String fileName) {
            if (!fileName.endsWith(".ppm")) {
                System.out.println("File is not PPM format");
            } else {
                File file = new File(AppConstants.IMAGE_DIR.getStringValue() + fileName);
                if (!file.exists()) {
                    System.out.println("File does not exist");
                } else {
                    Image image = new Image(AppConstants.IMAGE_DIR.getStringValue() + fileName, fileName);
                    Session session = new Session(List.of(image));
                    SessionManager.getInstance().addSession(new Session(List.of(image)));
                    System.out.println("Session with ID: " + session.getId() + " started");
                    SessionManager.getInstance().setCurrentSession(session);
                }
            }
    }

    public static void addImageToSession(String fileName) {
        if (!fileName.endsWith(".ppm")) {
            System.err.println("File is not PPM format");
        } else {
            File file = new File(AppConstants.IMAGE_DIR.getStringValue() + fileName);
            if (!file.exists()) {
                System.err.println("File does not exist");
            } else {
                Image image = new Image(AppConstants.IMAGE_DIR.getStringValue() + fileName, fileName);
                Session currentSession = SessionManager.getInstance().getCurrentSession();
                if (currentSession == null) {
                    System.err.println("Please start a session first");
                } else {
                    SessionManager.getInstance().getSessions().remove(currentSession);
                    currentSession.getImages().add(image);
                    System.out.println("Image \"" + image.fileName() + "\" added");
                    SessionManager.getInstance().addSession(currentSession);
                    SessionManager.getInstance().setCurrentSession(currentSession);
                }
            }
        }
    }

    public static void switchToSession(String id) {
        try {

                 int sessionId = Integer.parseInt(id);

                Optional<Session> session = SessionManager.getInstance().getSessions().stream()
                        .filter(s -> s.getId() == sessionId).findFirst();

                if (session.isPresent()) {
                    if (SessionManager.getInstance().getCurrentSession() != null) {
                        if (session.get().getId() == SessionManager.getInstance().getCurrentSession().getId()) {
                            throw new RuntimeException();
                        }
                    }

                }

                session.ifPresentOrElse(s -> SessionManager.getInstance().setCurrentSession(s),
                        () -> session.orElseThrow(RuntimeException::new));



                System.out.println("You switched to session with ID: " + id + "!");

                System.out.print("Name of images in the session: ");
                session.ifPresent(s -> s.getImages()
                        .forEach(image -> System.out.print(image.fileName() + "; ")));

                System.out.println("\nPending transformations: ");
                session.ifPresent(s -> s.getTransformations()
                        .forEach(transformation -> System.out.print(transformation.getType() + "; ")));
                System.out.println();

        } catch (Exception e) {
            System.out.println("Invalid session ID");
        }
    }

    public static void getSessionInfo() {
        if (SessionManager.getInstance().getCurrentSession() == null) {
            System.err.println("Please start a session first");
        } else {
            Session session = SessionManager.getInstance().getCurrentSession();
            System.out.println("Session ID: " + session.getId());
            System.out.print("Name of images in the session: ");
            session.getImages().forEach(image -> System.out.print(image.fileName() + " "));
            System.out.println();
            System.out.print("Pending transformations: ");
            session.getTransformations().forEach(transformation -> System.out.print(transformation.getType() + " "));
            System.out.println();
        }
    }

}

