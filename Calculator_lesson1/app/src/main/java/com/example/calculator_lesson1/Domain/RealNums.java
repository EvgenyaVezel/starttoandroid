package com.example.calculator_lesson1.Domain;

import java.util.ArrayList;
import java.util.List;

public class RealNums implements MakeArraysCalculatable {

    private List<Integer> realNums;

    public RealNums() {
        realNums = new ArrayList<>();
    }




    @Override
    public void addNumberArray(int num) {
        realNums.add(num);
    }

    @Override
    public float makeNumberFromArray() {
        float value = 0;

        for (int i = 0; i < realNums.size(); i++) {
            value = value * 10 + realNums.get(i);
        }
        return value;
    }

    @Override
    public void clearArray() {
        realNums.clear();
    }
}
