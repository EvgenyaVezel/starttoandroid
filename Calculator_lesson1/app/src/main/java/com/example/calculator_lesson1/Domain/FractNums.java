package com.example.calculator_lesson1.Domain;

import java.util.ArrayList;
import java.util.List;

public class FractNums implements MakeArraysCalculatable {

    private List<Integer> fractNums;

    public FractNums() {
        fractNums = new ArrayList<>();
    }

    @Override
    public void addNumberArray(int num) {
        fractNums.add(num);
    }

    @Override
    public float makeNumberFromArray() {
        float value = 0;

        for (int i = 0; i < fractNums.size(); i++) {
            value = value + (float) fractNums.get(i) / 10;
        }
        return value;
    }


    @Override
    public void clearArray() {
        fractNums.clear();
    }
}
