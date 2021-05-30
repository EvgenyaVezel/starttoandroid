package com.example.calculator_lesson1;

import android.os.Parcel;
import android.os.Parcelable;

public class Calculator implements Calculatable, Parcelable {

    private int value1;
    private int value2;
    private char operation;

    public Calculator() {
        this.value1 = 0;
        this.value2 = 0;
    }

    protected Calculator(Parcel in) {
        value1 = in.readInt();
        value2 = in.readInt();
        operation = (char) in.readInt();
    }

    public static final Creator<Calculator> CREATOR = new Creator<Calculator>() {
        @Override
        public Calculator createFromParcel(Parcel in) {
            return new Calculator(in);
        }

        @Override
        public Calculator[] newArray(int size) {
            return new Calculator[size];
        }
    };

    public int getValue1() {
        return value1;
    }

    public int getValue2() {
        return value2;
    }

    public char getOperation() {
        return operation;
    }

    public void setValue1(int value1) {
        this.value1 = value1;
    }

    public void setValue2(int value2) {
        this.value2 = value2;
    }

    public void setOperation(char operation) {
        this.operation = operation;
    }

    @Override
    public int addition() {
        return value1 + value2;
    }

    @Override
    public int subtraction() {
        return value1 - value2;
    }

    @Override
    public int multiplication() {
        return value1 * value2;
    }

    @Override
    public int division() {
        return value1 / value2;
    }

    @Override
    public int procent() {
        return value1 / 100;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(value1);
        dest.writeInt(value2);
        dest.writeInt((int) operation);
    }
}
