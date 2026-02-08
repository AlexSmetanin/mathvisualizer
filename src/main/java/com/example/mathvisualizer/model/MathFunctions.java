package com.example.mathvisualizer.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class MathFunctions {

    public static Map<Double, Double> calculate(String functionName, double xMin, double xMax, double step) {
        Map<Double, Double> result = new LinkedHashMap<>();

        for (double x = xMin; x <= xMax; x += step) {
            double y = 0;
            switch (functionName) {
                case "sin(x)" -> y = Math.sin(x);
                case "cos(x)" -> y = Math.cos(x);
                case "exp(x)" -> y = Math.exp(x);
                case "log(x)" -> {
                    if (x > 0) y = Math.log(x);
                    else y = Double.NaN; // некоректне значення
                }
            }
            result.put(x, y);
        }
        return result;
    }
}