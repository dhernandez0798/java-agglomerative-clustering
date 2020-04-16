package com.github.dhernandez0798;

import java.util.ArrayList;
import java.util.List;

public class AgglomerativeClustering {

    // We transform a simple array to a list here.
    public static List<Cluster> process(double[] clusters, int clustersAmount, LinkageCriterion linkageCriterion) {
        List<Cluster> tempClusters = new ArrayList<>();
        for (double cluster : clusters) {
            tempClusters.add(new Cluster(cluster, new Cluster[]{null, null}, 0));
        }
        return process(tempClusters, clustersAmount, linkageCriterion);
    }

    public static List<Cluster> process(List<Cluster> clusters, int clustersAmount, LinkageCriterion linkageCriterion) {
        Cluster newCluster;
        while (clustersAmount < clusters.size()) {
            newCluster = agglomerate(clusters, linkageCriterion);
            clusters.remove(newCluster.getParents()[0]);
            clusters.remove(newCluster.getParents()[1]);
            clusters.add(newCluster);
        }
        return clusters;
    }

    private static Cluster agglomerate(List<Cluster> clusters, LinkageCriterion linkageCriterion) {
        double distance = Double.POSITIVE_INFINITY;

        // To store the position in array of he closest pair.
        int[] closestPair = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};

        for (int i = 0; i < clusters.size(); i++) {
            for (int j = 0; j < clusters.size(); j++) {

                // Do not calculate distance if i == j. It means they're the same item and distance will be 0.
                if (j != i) {
                    double tempDistance = linkageCriterion.calculate(clusters.get(i), clusters.get(j));
                    if (tempDistance < distance) {
                        distance = tempDistance;
                        closestPair[0] = i;
                        closestPair[1] = j;
                    }
                }
            }
        }

        Cluster c1 = clusters.get(closestPair[0]);
        Cluster c2 = clusters.get(closestPair[1]);
        return Cluster.mergeClusters(c1, c2, distance);
    }
}
