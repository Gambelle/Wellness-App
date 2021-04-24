package com.example.test.ui.home;

import java.util.ArrayList;

public class FoodItem {
    private String mName;
    private String mQuantity;

    public FoodItem(String name, String quantity) {
        mName = name;
        mQuantity = quantity;
    }

    public String getName() {
        return mName;
    }

    public String getQuantity() {
        return mQuantity;
    }

}
