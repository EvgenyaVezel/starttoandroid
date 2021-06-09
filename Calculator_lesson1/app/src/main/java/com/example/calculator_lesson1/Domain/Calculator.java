package com.example.calculator_lesson1.Domain;

public class Calculator implements Calculatable {


    @Override
    public float operation(float value1, float value2, Operator operator) {
        switch (operator) {
            case PLUS:
                return value1 + value2;
            case MINUS:
                return value1 - value2;
            case SPLIT:
                return value1 / value2;
            case MULTIPLY:
                return value1 * value2;
            case PROCENT:
                return value1 / 100;
        }
        return 0.0f;
    }

}
