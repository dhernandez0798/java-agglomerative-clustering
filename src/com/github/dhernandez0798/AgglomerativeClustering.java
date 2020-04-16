package com.github.dhernandez0798;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AgglomerativeClustering {

    // We transform a simple array to a list here.
    public static void proccess(double[] clusters, int clustersAmount, LinkageCriterion linkageCriterion) {
        List<Cluster> tempClusters = new ArrayList<>();
        for (double cluster : clusters) {
            tempClusters.add(new Cluster(cluster));
        }
        process(tempClusters, clustersAmount, linkageCriterion);
    }

    public static void process(List<Cluster> clusters, int clustersAmount, LinkageCriterion linkageCriterion) {
        int[] clusterResult;
        while (clustersAmount < clusters.size()) {
            clusterResult = agglomerate(clusters, linkageCriterion);

            // Store the clusters so we can remove them later by element, not by position, as it will change.
            Cluster cluster1 = clusters.get(clusterResult[0]);
            Cluster cluster2 = clusters.get(clusterResult[1]);
            Cluster newCluster = Cluster.mergeClusters(cluster1, cluster2);
            clusters.remove(cluster1);
            clusters.remove(cluster2);
            clusters.add(newCluster);
        }
        System.out.println(Arrays.toString(clusters.toArray()));
    }

    private static int[] agglomerate(List<Cluster> clusters, LinkageCriterion linkageCriterion) {
        double distance = Double.POSITIVE_INFINITY;

        // To store the position in array of he closest pair.
        int[] closestPair = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE};

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
        return closestPair;
    }
}
