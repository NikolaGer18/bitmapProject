package com.company.session;

import com.company.image.Image;
import com.company.transformation.Transformation;

import java.util.ArrayList;
import java.util.List;

public class Session {
    private int id;
    private final List<Image> images;
    private final List<Transformation> transformations = new ArrayList<>();

    public Session(List<Image> images) {
        this.images = new ArrayList<>(images);

        int sessions = SessionManager.getInstance().getSessions().size();

        if (sessions == 0) {
            this.id = 1;
        } else {
            this.id = sessions + 1;
        }
    }

    public int getId() {
        return id;
    }

    public List<Transformation> getTransformations() {
        return transformations;
    }

    public void addTransformation(Transformation transformation) {
        transformations.add(transformation);
    }

    public List<Image> getImages() {
        return images;
    }

    public void setId(int id) {
        this.id = id;
    }
}
