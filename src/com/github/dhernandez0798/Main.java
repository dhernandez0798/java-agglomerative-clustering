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
        for (int i = 0; i < 4; i++) {
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

    public static double calculate(double c1, double c2) {
        return Math.sqrt(Math.pow(c2 - c1, 2));
    }

    public static double[] getMinimum(double[][] arr) {
        double minVal = Double.MAX_VALUE;
        double a = 0;
        double b = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] <= minVal) {
                    minVal = arr[i][j];
                    a = i;
                    b = j;
                }
            }
        }
        return new double[]{minVal, a, b};
    }

    public static int[] mergeInput(List<Cluster> input) {
        double minDistance = Double.POSITIVE_INFINITY;

        // To store the position in array of he closest pair.
        int[] closestPair = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE};
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.size(); j++) {
                // Do not calculate distance if i == j. It means they're the same item and distance will be 0.
                if (j != i) {
                    double distance = Double.NaN;
                    if (input.get(i).getValues().length > 1) {
                        double minLinkageDistance = Double.POSITIVE_INFINITY;
                        if (input.get(j).getValues().length > 1) {
                            for (int k = 0; k < input.get(i).getValues().length; k++) {
                                for (int l = 0; l < input.get(j).getValues().length; l++) {
                                    double tempDistance = calculate(input.get(i).getValues()[k], input.get(j).getValues()[l]);
                                    if (tempDistance < minLinkageDistance)
                                        minLinkageDistance = tempDistance;
                                }
                            }
                        } else {
                            for (int k = 0; k < input.get(i).getValues().length; k++) {
                                double tempDistance = calculate(input.get(i).getValues()[k], input.get(j).getValues()[0]);
                                if (tempDistance < minLinkageDistance)
                                    minLinkageDistance = tempDistance;
                            }
                        }
                        distance = minLinkageDistance;
                    } else
                        distance = calculate(input.get(i).getValues()[0], input.get(j).getValues()[0]);
                    if (!Double.isNaN(distance) && distance < minDistance) {
                        minDistance = distance;
                        closestPair[0] = i;
                        closestPair[1] = j;
                        //System.out.println("Distance between " + input.get(i).getValues()[0] + " & " + input.get(j).getValues()[0] +": " + distance);
                    }
                    //System.out.println("Distance between " + input[i] + " & " + input[j] +": " + calculate(input[i], input[j]));
                }
            }
        }
        return closestPair;
    }
}
