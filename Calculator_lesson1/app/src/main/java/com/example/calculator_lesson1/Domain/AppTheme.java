package com.example.calculator_lesson1.Domain;

import com.example.calculator_lesson1.R;

public enum AppTheme {
    LIGHT(R.style.AppLightTheme, "light"),
    DARK(R.style.AppThemeNight, "dark");

    private int resources;
    private String key;

    AppTheme(int resources, String key) {
        this.resources = resources;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public int getResources() {
        return resources;
    }
}
