package com.example.calculator_lesson1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Calculator calculator;
    private TextView resultScreen;

   private final static String keyCalculator = "CALCULATOR";
    @Override
    public void onSaveInstanceState(Bundle instanceState) {
        super.onSaveInstanceState(instanceState);
        instanceState.putParcelable(keyCalculator, calculator);
    }

    protected void onRestoreInstanceState(Bundle instanceState){
        super.onSaveInstanceState(instanceState);
        instanceState.putParcelable(keyCalculator, calculator);
       setTextCounters();
    }
    private void setTextCounters(){
        setTextResultScreen(Integer.toString(calculator.getValue1()));
        setTextResultScreen(Integer.toString(calculator.getValue2()));
        setTextResultScreen(Character.toString(calculator.getOperation()));

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculator = new Calculator();
        initView();

    }


    private void initView() {

        resultScreen = findViewById(R.id.view_resut);
        initButton1ClickListener();
       initButton2ClickListener();
        initButton3ClickListener();
        initButton4ClickListener();
        initButton5ClickListener();
        initButton6ClickListener();
        initButton7ClickListener();
        initButton8ClickListener();
        initButton9ClickListener();
        initButton0ClickListener();
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


    private void initButton1ClickListener() {
        Button btn1 = findViewById(R.id.btn_1);
        String textValueBtn1 = btn1.getText().toString();

        btn1.setOnClickListener(v -> {
            setTextResultScreen(textValueBtn1);
        });
    }


    private void initButton2ClickListener() {
        Button btn2 = findViewById(R.id.btn_2);
        String textValueBtn2 = btn2.getText().toString();

        btn2.setOnClickListener(v -> {
            setTextResultScreen(textValueBtn2);
        });
    }

    private void initButton3ClickListener() {
        Button btn3 = findViewById(R.id.btn_3);
        String textValueBtn3 = btn3.getText().toString();
        calculator.setValue1(Integer.parseInt(textValueBtn3));
        btn3.setOnClickListener(v -> {
            setTextResultScreen(textValueBtn3);
        });
    }

    private void initButton4ClickListener() {
        Button btn4 = findViewById(R.id.btn_4);
        String textValueBtn4 = btn4.getText().toString();

        btn4.setOnClickListener(v -> {
            setTextResultScreen(textValueBtn4);
        });
    }

    private void initButton5ClickListener() {
        Button btn5 = findViewById(R.id.btn_5);
        String textValueBtn5 = btn5.getText().toString();
        btn5.setOnClickListener(v -> {
            setTextResultScreen(textValueBtn5);
        });
    }

    private void initButton6ClickListener() {
        Button btn6 = findViewById(R.id.btn_6);
        String textValueBtn6 = btn6.getText().toString();
        btn6.setOnClickListener(v -> {
            setTextResultScreen(textValueBtn6);
        });
    }

    private void initButton7ClickListener() {
        Button btn7 = findViewById(R.id.btn_7);
        String textValueBtn7 = btn7.getText().toString();
        btn7.setOnClickListener(v -> {
            setTextResultScreen(textValueBtn7);
        });
    }

    private void initButton8ClickListener() {
        Button btn8 = findViewById(R.id.btn_8);
        String textValueBtn8 = btn8.getText().toString();
        btn8.setOnClickListener(v -> {
            setTextResultScreen(textValueBtn8);
        });
    }

    private void initButton9ClickListener() {
        Button btn9 = findViewById(R.id.btn_9);
        String textValueBtn9 = btn9.getText().toString();
        btn9.setOnClickListener(v -> {
            setTextResultScreen(textValueBtn9);
        });
    }

    private void initButton0ClickListener() {
        Button btn0 = findViewById(R.id.btn_0);
        String textValueBtn0 = btn0.getText().toString();
        btn0.setOnClickListener(v -> {
            setTextResultScreen(textValueBtn0);
        });
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