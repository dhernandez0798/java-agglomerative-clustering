package com.github.dhernandez0798;

public class EuclideanDistance implements DistanceFunction{
    @Override
    public double calculate(double value1, double value2) {
        return Math.sqrt(Math.pow(value1 - value2, 2));
    }
}
