package com.company.utils;

public enum AppConstants {

    IMAGE_DIR("src/com/company/images/"),
    TEMP_DIR("src/com/company/images/temp/");

    private final String stringValue;

    AppConstants(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }
}
