package com.github.dhernandez0798;

import java.util.Arrays;

public class Cluster {
    private double[] values;
    
    public Cluster(double values) {
        this.values = new double[]{values};
    }

    public Cluster(double[] values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "Cluster{" +
                "values=" + Arrays.toString(values) +
                '}';
    }

    public double[] getValues() {
        return values;
    }
    
    public static Cluster mergeClusters(Cluster c1, Cluster c2) {
        int length = c1.getValues().length + c2.getValues().length;
        double[] combinedArray = new double[length];
        System.arraycopy(c1.getValues(), 0, combinedArray, 0, c1.getValues().length);
        System.arraycopy(c2.getValues(), 0, combinedArray, c1.getValues().length, c2.getValues().length);
        return new Cluster(combinedArray);
    }
}
