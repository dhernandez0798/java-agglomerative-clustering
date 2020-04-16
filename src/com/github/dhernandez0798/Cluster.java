package com.github.dhernandez0798;

import java.util.Arrays;

public class Cluster {
    private final double[] values;
    private Cluster[] parents;
    private double distance;
    
    public Cluster(double values, Cluster[] parents, double distance) {
        this.values = new double[]{values};
        this.parents = parents;
        this.distance = distance;
    }

    public Cluster(double[] values, Cluster[] parents, double distance) {
        this.values = values;
        this.parents = parents;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Cluster{" +
                "values=" + Arrays.toString(values) +
                ", parents=" + Arrays.toString(parents) +
                ", distance=" + distance +
                '}';
    }

    public double[] getValues() {
        return values;
    }

    public Cluster[] getParents() {
        return parents;
    }

    public double getDistance() {
        return distance;
    }

    public static Cluster mergeClusters(Cluster c1, Cluster c2, double distance) {
        int length = c1.getValues().length + c2.getValues().length;
        double[] combinedArray = new double[length];
        System.arraycopy(c1.getValues(), 0, combinedArray, 0, c1.getValues().length);
        System.arraycopy(c2.getValues(), 0, combinedArray, c1.getValues().length, c2.getValues().length);
        return new Cluster(combinedArray, new Cluster[]{c1, c2}, distance);
    }
}
