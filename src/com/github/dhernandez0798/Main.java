package com.github.dhernandez0798;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        double[] inputArray = {5, 88, 326.4, 617, 8412, 71824, 412, 2};
        List<Cluster> clusters = null;
        try {
            clusters = AgglomerativeClustering.process(inputArray, 4, new SingleLinkageCriterion(new EuclideanDistance()));
            for (Cluster cluster : clusters) {
                System.out.println(cluster);
            }
        } catch (InvalidClusterException e) {
            // If the clusters array we passed were empty.
            e.printStackTrace();
        }
    }
}
