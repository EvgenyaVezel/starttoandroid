package com.example.calculator_lesson1.Domain;

import android.content.Context;
import android.content.SharedPreferences;
import android.icu.lang.UCharacterEnums;

import static android.content.Context.*;

public class ThemeStorage {

    private static final String KEY_THEME = "KEY_THEME";

    private SharedPreferences sharedPreferences;

    public ThemeStorage(Context context) {
        sharedPreferences = context.getSharedPreferences("app_themes", MODE_PRIVATE);
    }

    public AppTheme getTheme(){
       String key = sharedPreferences.getString(KEY_THEME, AppTheme.LIGHT.getKey());

        for (AppTheme theme: AppTheme.values() ) {
           if(theme.getKey().equals(key)){
               return theme;
           }

        }
        throw new IllegalStateException("Warning!");
    }

    public void setTheme(AppTheme theme){
        sharedPreferences.edit().putString(KEY_THEME, theme.getKey()).apply();
    }

}
