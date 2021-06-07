package com.example.calculator_lesson1.UI;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.TextView;

import com.example.calculator_lesson1.Domain.Calculator;
import com.example.calculator_lesson1.Domain.FractNums;
import com.example.calculator_lesson1.Domain.Operator;
import com.example.calculator_lesson1.Domain.RealNums;
import com.example.calculator_lesson1.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class CalculatorPresenter  implements Parcelable {

    private Operator operator;
    private Calculator calculator;
    private MainActivity view;
    private FractNums fractNums;
    private RealNums realNums;
    private boolean clickDotBtn = false;
    private boolean isFirstValue = true;
    private float valueOne;
    private float valueTwo;


    public CalculatorPresenter(MainActivity mainActivity) {

        operator = null;
        this.view = mainActivity;
        realNums = new RealNums();
        fractNums = new FractNums();
        calculator = new Calculator();
    }

    public void setView(MainActivity view) {
        this.view = view;
    }

    public float getValueOne() {
        return valueOne;
    }

    public float getValueTwo() {
        return valueTwo;
    }

    protected CalculatorPresenter(Parcel in) {
        clickDotBtn = in.readByte() != 0;
        isFirstValue = in.readByte() != 0;
        valueOne = in.readFloat();
        valueTwo = in.readFloat();
    }

    public static final Creator<CalculatorPresenter> CREATOR = new Creator<CalculatorPresenter>() {
        @Override
        public CalculatorPresenter createFromParcel(Parcel in) {
            return new CalculatorPresenter(in);
        }

        @Override
        public CalculatorPresenter[] newArray(int size) {
            return new CalculatorPresenter[size];
        }
    };

    public boolean isFirstValue() {
        return isFirstValue;
    }

    public void makeOperation(Operator operator) {

        isFirstValue = false;
        clickDotBtn = false;

        if (this.operator != null) {
            showResult("");
            float resultValue = calculator.operation(valueOne, valueTwo, this.operator);

            showResult(String.valueOf(resultValue));
            valueOne = resultValue;
            valueTwo = 0;

        }
        this.operator = operator;
        realNums.clearArray();
        fractNums.clearArray();
    }


    public void addFractNums(Integer valueOf) {
        fractNums.addNumberArray(valueOf);
    }

    public void addRealNums(Integer valueOf) {

        realNums.addNumberArray(valueOf);
    }

    public float composeNumber() {

        float partOne = realNums.makeNumberFromArray();
        float partTwo = fractNums.makeNumberFromArray();

        return partOne + partTwo;

    }

    private void publishArgument() {

        if (isFirstValue()) {
            valueOne = composeNumber();
            showResult(String.valueOf(valueOne));
        } else {
            valueTwo = composeNumber();
            showResult(String.valueOf(valueTwo));

        }

    }

    public void makeNumber(MaterialButton btn) {
        if (clickDotBtn) {//если введена точка, то заполняем список с дробной частью
            addFractNums(Integer.valueOf(btn.getText().toString()));
        } else {
            addRealNums(Integer.valueOf(btn.getText().toString()));
        }
        publishArgument();

    }

    public void clearValues() {
        operator = null;
        isFirstValue = true;
        clickDotBtn = false;
        valueOne = 0;
        valueTwo = 0;
        showResult("");
        realNums.clearArray();
        fractNums.clearArray();
    }

    public void showResult(String result) {
        MaterialTextView resultScreen = view.findViewById(R.id.view_resut);

        if (result.equals(".")) {
            if (resultScreen.getText().toString().contains(".") && result.equals(".")) {//запрет на ввод двух и более точек
                result = "";
            }
            showResult(resultScreen.getText() + result);
        } else {

            if (result.substring(result.indexOf(".") + 1).equals("0")) {
                result = result.substring(0, result.indexOf("."));
            }

            view.showResult(result);
        }

    }

    public void dotCick(String dotText) {
        clickDotBtn = true;

        showResult(dotText);
    }

    public void delOneRankNumber() {//удаляем разряд числа

        if (isFirstValue) {
            valueOne = (int) valueOne / 10;
            showResult(String.valueOf(valueOne));
        } else {
            valueTwo = (int) valueTwo / 10;

            showResult(String.valueOf(valueTwo));
        }


    }

    public void showEquals() {//при нажатии кнопки "равно"
        makeOperation(operator);
        showResult(String.valueOf(valueOne));
        this.operator = null;
        isFirstValue = true;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (clickDotBtn ? 1 : 0));
        dest.writeByte((byte) (isFirstValue ? 1 : 0));
        dest.writeFloat(valueOne);
        dest.writeFloat(valueTwo);
    }
}

