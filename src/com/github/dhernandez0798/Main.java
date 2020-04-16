package com.github.dhernandez0798;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        double[] inputArray = {5, 88, 326.4, 617, 8412, 71824, 412, 2};
        List<Cluster> input = new ArrayList<>();
        for (double element : inputArray) {
            input.add(new Cluster(element));
        }
        /*while (input.size() > ) {
            int[] closestPair = mergeInput(input);
            Cluster c1 = input.get(closestPair[0]);
            Cluster c2 = input.get(closestPair[1]);
            Cluster newCluster = Cluster.mergeClusters(c1, c2);
            input.remove(c1);
            input.remove(c2);
            input.add(newCluster);
        }*/
        for (int i = 0; i < 3; i++) {
            int[] closestPair = mergeInput(input);
            Cluster c1 = input.get(closestPair[0]);
            Cluster c2 = input.get(closestPair[1]);
            Cluster newCluster = Cluster.mergeClusters(c1, c2);
            input.remove(c1);
            input.remove(c2);
            input.add(newCluster);
        }
        for (Cluster cluster: input) {
            System.out.println(cluster);
        }
    }

    public static int[] mergeInput(List<Cluster> input) {
        double minDistance = Double.POSITIVE_INFINITY;
        LinkageCriterion linkageCriterion = new SingleLinkageCriterion(new EuclideanDistance());
        // To store the position in array of he closest pair.
        int[] closestPair = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE};
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.size(); j++) {
                // Do not calculate distance if i == j. It means they're the same item and distance will be 0.
                if (j != i) {
                    double distance = linkageCriterion.calculate(input.get(i), input.get(j));
                    if (distance < minDistance) {
                        minDistance = distance;
                        closestPair[0] = i;
                        closestPair[1] = j;
                    }
                }
            }
        }
        return closestPair;
    }
}
