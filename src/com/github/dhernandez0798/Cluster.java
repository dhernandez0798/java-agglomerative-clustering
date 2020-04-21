package com.github.dhernandez0798;

import java.util.Arrays;

/**
 * The Cluster class will contain a set of values, along with the
 * parents of that cluster (or null if none) and the distance between parents (0 if none).
 */
public class Cluster {
    private final double[] values;
    /**
     * An array containing the parent clusters.
     */
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

    /**
     * Given two clusters, merge the values into a new one. Also sets
     * the new cluster parents and distance between parents.
     * @param c1 the first cluster we want to merge
     * @param c2 the second cluster we want to merge
     * @param distance the distance between the two clusters we want to merge
     * @return the new cluster
     */
    public static Cluster mergeClusters(Cluster c1, Cluster c2, double distance) {
        int length = c1.getValues().length + c2.getValues().length;
        double[] combinedArray = new double[length];
        System.arraycopy(c1.getValues(), 0, combinedArray, 0, c1.getValues().length);
        System.arraycopy(c2.getValues(), 0, combinedArray, c1.getValues().length, c2.getValues().length);
        return new Cluster(combinedArray, new Cluster[]{c1, c2}, distance);
    }
}
