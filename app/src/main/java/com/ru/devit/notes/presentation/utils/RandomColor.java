package com.ru.devit.notes.presentation.utils;

import android.graphics.Color;
import android.util.SparseIntArray;

import java.util.Random;

public class RandomColor {
    private static final SparseIntArray colors = new SparseIntArray(5);
    static {
        colors.put(0 , Color.RED);
        colors.put(1 , Color.BLUE);
        colors.put(2 , Color.GREEN);
        colors.put(3 , Color.YELLOW);
        colors.put(4 , Color.BLACK);
    }

    public static int generetaRandomColor(){
        Random random = new Random();
        return random.nextInt(colors.size());
    }

    public static int getColor(int colorId){
        return colors.get(colorId);
    }
}
