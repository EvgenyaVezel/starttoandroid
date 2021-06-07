package com.example.calculator_lesson1.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.calculator_lesson1.Domain.AppTheme;
import com.example.calculator_lesson1.Domain.Operator;
import com.example.calculator_lesson1.Domain.ThemeStorage;
import com.example.calculator_lesson1.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textview.MaterialTextView;

public class MainActivity extends AppCompatActivity implements CalcView, Constants {

    private CalculatorPresenter calculatorPresenter;
    private MaterialTextView resultScreen;
    private ThemeStorage storage;
    private SwitchMaterial switchMaterial;

    private static final int REQUEST_CODE_SETTING_ACTIVITY = 99;
    private final static String PRESENTER = "CALCULATOR_PRESENTER";
    private final static String KEY_SWITCH = "KEY_SWITCH";

    @Override
    protected void onSaveInstanceState(@NonNull Bundle instanceState) {
        super.onSaveInstanceState(instanceState);
        instanceState.putParcelable(PRESENTER, calculatorPresenter);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle instanceState) {
        super.onRestoreInstanceState(instanceState);
        instanceState.putParcelable(PRESENTER, calculatorPresenter);
        setTextCounters();
    }


    private void setTextCounters() {
        if (calculatorPresenter.isFirstValue()) {
            calculatorPresenter.showResult(String.valueOf(calculatorPresenter.getValueOne()));
        } else {
            calculatorPresenter.showResult(String.valueOf(calculatorPresenter.getValueTwo()));
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        storage = new ThemeStorage(this);
        setTheme(storage.getTheme().getResources());

        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            calculatorPresenter = new CalculatorPresenter(MainActivity.this);
        } else {
            calculatorPresenter = savedInstanceState.getParcelable(PRESENTER);
            calculatorPresenter.setView(this);

        }
        SharedPreferences pref = getSharedPreferences(KEY_SWITCH, MODE_PRIVATE);
        SwitchMaterial switchMaterial = findViewById(R.id.switch_btn);
        switchMaterial.setChecked(pref.getBoolean(KEY_SWITCH, false));

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

        initSettingBtn();

    }

    void initSettingBtn() {
        Button btnSettings = findViewById(R.id.setting_btn);
        switchMaterial = findViewById(R.id.switch_btn);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent runSettings = new Intent(MainActivity.this, SettingsActivity.class);
                startActivityForResult(runSettings, REQUEST_CODE_SETTING_ACTIVITY);

            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != REQUEST_CODE_SETTING_ACTIVITY) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }

        if (resultCode == RESULT_OK) {
            switchMaterial.setChecked(data.getBooleanExtra(THEME, false));
            initSwitch();
        }
    }


    private void initNumbersButtons() {
        int[] numberButtons = {R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5, R.id.btn_6,
                R.id.btn_7, R.id.btn_8, R.id.btn_9, R.id.btn_0};
        for (int numBtn : numberButtons) {
            findViewById(numBtn).setOnClickListener(v -> {
                MaterialButton btn = (MaterialButton) v;

                calculatorPresenter.makeNumber(btn);

            });
        }
    }


    private void initButtonDotClickListener() {
        findViewById(R.id.btn_dot).setOnClickListener(v -> {
            MaterialButton btnDot = (MaterialButton) v;
            calculatorPresenter.dotCick(btnDot.getText().toString());
        });
    }

    private void initButtonAddClickListener() {
        MaterialButton btnAdd = findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(v -> {
            calculatorPresenter.makeOperation(Operator.PLUS);
        });
    }

    private void initButtonTawayClickListener() {
        findViewById(R.id.btn_taway).setOnClickListener(v -> {
            calculatorPresenter.makeOperation(Operator.MINUS);
        });
    }

    private void initButtonMultClickListener() {
        findViewById(R.id.btn_mult).setOnClickListener(v -> {
            calculatorPresenter.makeOperation(Operator.MULTIPLY);
        });
    }

    private void initButtonSplitClickListener() {
        findViewById(R.id.btn_split).setOnClickListener(v -> {
            calculatorPresenter.makeOperation(Operator.SPLIT);
        });
    }

    private void initButtonDelClickListener() {
        findViewById(R.id.btn_del).setOnClickListener(v -> {
            calculatorPresenter.delOneRankNumber();
        });
    }

    private void initButtonClearClickListener() {

        findViewById(R.id.btn_clear).setOnClickListener(v -> {
            calculatorPresenter.clearValues();
        });
    }

    private void initButtonProcentClickListener() {
        findViewById(R.id.btn_procent).setOnClickListener(v -> {
            calculatorPresenter.makeOperation(Operator.PROCENT);
        });
    }

    private void initButtonEqualsClickListener() {
        findViewById(R.id.btn_equally).setOnClickListener(v -> {
            calculatorPresenter.showEquals();
        });
    }

    @Override
    public void showResult(String result) {
        resultScreen = findViewById(R.id.view_resut);
        resultScreen.setText(result);
    }
}