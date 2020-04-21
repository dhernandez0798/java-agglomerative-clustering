package com.github.dhernandez0798;

public class EuclideanDistance implements DistanceFunction{

    /**
     * Given two points, calculate a distance between them.
     * @param value1 the first value of the Euclidean distance formula
     * @param value2 the second value of the Euclidean distance formula
     * @return the distance between two points
     */
    @Override
    public double calculate(double value1, double value2) {
        return Math.sqrt(Math.pow(value1 - value2, 2));
    }
}
