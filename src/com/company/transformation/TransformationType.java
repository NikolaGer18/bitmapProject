package com.company.transformation;

public enum TransformationType {
    MONOCHROME("monochrome"),
    GRAYSCALE("grayscale"),
    NEGATIVE("negative");

    private final String name;

    TransformationType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
