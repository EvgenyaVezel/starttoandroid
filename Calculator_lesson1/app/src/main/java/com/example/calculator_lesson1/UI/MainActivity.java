package com.example.calculator_lesson1.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calculator_lesson1.Domain.AppTheme;
import com.example.calculator_lesson1.Domain.Calculator;
import com.example.calculator_lesson1.Domain.ThemeStorage;
import com.example.calculator_lesson1.R;
import com.google.android.material.switchmaterial.SwitchMaterial;

import static android.content.Context.MODE_PRIVATE;

public class MainActivity extends AppCompatActivity {

    private Calculator calculator;
    private TextView resultScreen;

    private ThemeStorage storage;


    private final static String keyCalculator = "CALCULATOR";
    private final static String KEY_SWITCH = "KEY_SWITCH";



    @Override
    public void onSaveInstanceState(Bundle instanceState) {
        super.onSaveInstanceState(instanceState);
        instanceState.putParcelable(keyCalculator, calculator);

    }

    protected void onRestoreInstanceState(Bundle instanceState) {
        super.onSaveInstanceState(instanceState);
        instanceState.putParcelable(keyCalculator, calculator);
        setTextCounters();

    }

    private void setTextCounters() {
        setTextResultScreen(Integer.toString(calculator.getValue1()));
        setTextResultScreen(Integer.toString(calculator.getValue2()));
        setTextResultScreen(Character.toString(calculator.getOperation()));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        storage = new ThemeStorage(this);

        setTheme(storage.getTheme().getResources());

        setContentView(R.layout.activity_main);
        SharedPreferences pref = getSharedPreferences(KEY_SWITCH, MODE_PRIVATE);
        SwitchMaterial switchMaterial = findViewById(R.id.switch_btn);
        switchMaterial.setChecked(pref.getBoolean(KEY_SWITCH, false));

        calculator = new Calculator();
        initView();
        initSwitch();
    }

    private void initSwitch() {
        SwitchMaterial switchMaterial = findViewById(R.id.switch_btn);
        switchMaterial.setOnCheckedChangeListener((buttonView, isChecked) -> {
            getSharedPreferences(KEY_SWITCH, MODE_PRIVATE).edit().putBoolean(KEY_SWITCH, switchMaterial.isChecked()).apply();

            if (isChecked) {

                storage.setTheme((AppTheme.DARK));

            } else {

                storage.setTheme(AppTheme.LIGHT);
            }
            recreate();
        });


    }

    private void initView() {

        resultScreen = findViewById(R.id.view_resut);
        initNumbersButtons();
        initButtonAddClickListener();
        initButtonSplitClickListener();
        initButtonDotClickListener();
        initButtonMultClickListener();
        initButtonTawayClickListener();
        initButtonEqualsClickListener();
        initButtonClearClickListener();
        initButtonDelClickListener();
        initButtonProcentClickListener();


    }

    private void initNumbersButtons() {
        int[] numberButtons = {R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5, R.id.btn_6,
                R.id.btn_7, R.id.btn_8, R.id.btn_9, R.id.btn_0};
        for (int numBtn : numberButtons) {
            findViewById(numBtn).setOnClickListener(v -> {
                Button btn = (Button) v;
                setTextResultScreen(btn.getText().toString());
            });
        }
    }

    private void initButtonDotClickListener() {
        findViewById(R.id.btn_dot).setOnClickListener(v -> {

        });
    }

    private void initButtonAddClickListener() {
        Button btnAdd = findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(v -> {

        });
    }

    private void initButtonTawayClickListener() {
        findViewById(R.id.btn_taway).setOnClickListener(v -> {

        });
    }

    private void initButtonMultClickListener() {
        findViewById(R.id.btn_mult).setOnClickListener(v -> {

        });
    }

    private void initButtonSplitClickListener() {
        findViewById(R.id.btn_split).setOnClickListener(v -> {

        });
    }

    private void initButtonDelClickListener() {
        findViewById(R.id.btn_del).setOnClickListener(v -> {
            String s = resultScreen.getText().toString();
            resultScreen.setText(s.substring(0, s.length() - 1));
        });
    }

    private void initButtonClearClickListener() {

        findViewById(R.id.btn_clear).setOnClickListener(v -> {
            resultScreen.setText("");
        });
    }

    private void initButtonProcentClickListener() {
        findViewById(R.id.btn_procent).setOnClickListener(v -> {

        });
    }

    private void initButtonEqualsClickListener() {
        findViewById(R.id.btn_equally).setOnClickListener(v -> {

        });
    }

    private void setTextResultScreen(String value1) {
        String s = resultScreen.getText().toString();
        resultScreen.setText(s += value1);

    }

}