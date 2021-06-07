package com.example.calculator_lesson1.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.calculator_lesson1.R;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class SettingsActivity extends AppCompatActivity implements Constants{
    private static final String KEY_SWITCH = "KEY_SWITCH";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        SharedPreferences pref = getSharedPreferences(KEY_SWITCH, MODE_PRIVATE);
        SwitchMaterial switchMaterial = findViewById(R.id.switch_theme);
        switchMaterial.setChecked(pref.getBoolean(KEY_SWITCH, false));

        initBtn();
        initSwitch();

    }

    void initBtn() {
        Button btnReturn = findViewById(R.id.return_btn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwitchMaterial switchBtn = findViewById(R.id.switch_theme);
                Intent intentRes = new Intent();
                intentRes.putExtra(THEME, switchBtn.isChecked());
                setResult(RESULT_OK, intentRes);
                finish();
            }
        });
    }
    void initSwitch(){
        SwitchMaterial switchBtn = findViewById(R.id.switch_theme);
        getSharedPreferences(KEY_SWITCH, Context.MODE_PRIVATE).edit().putBoolean(KEY_SWITCH, switchBtn.isChecked()).apply();
        switchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(getApplicationContext(), "dark theme is " + isChecked, Toast.LENGTH_SHORT).show();

            }
        });
    }
}