package com.company.session;

import java.util.ArrayList;
import java.util.List;

public class SessionManager {
    private static SessionManager instance = null;
    private boolean fileOpened = false;
    private final List<Session> sessions = new ArrayList<>();
    private Session currentSession = null;

    private SessionManager() {
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public void addSession(Session session) {
        sessions.add(session);
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public boolean isFileOpened() {
        return fileOpened;
    }

    public void setFileOpened(boolean fileOpened) {
        this.fileOpened = fileOpened;
    }
}
