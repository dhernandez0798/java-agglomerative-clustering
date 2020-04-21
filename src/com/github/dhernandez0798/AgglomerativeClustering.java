package com.github.dhernandez0798;

import java.util.ArrayList;
import java.util.List;

public class AgglomerativeClustering {
    
    private AgglomerativeClustering() {
        throw new UnsupportedOperationException();
    }

    /**
     * Perform an agglomeration given an array of doubles
     * @param clusters array containing the clusters
     * @param clustersAmount amount of clusters we want to end up with
     * @param linkageCriterion the criteria that will be used to perform the agglomeration of two clusters
     * @return a list of agglomerated clusters
     * @throws InvalidClusterException if the clusters array are empty
     */
    public static List<Cluster> process(double[] clusters, int clustersAmount, LinkageCriterion linkageCriterion) throws InvalidClusterException {
        if (clusters == null) {
            throw new NullPointerException("Clusters were null");
        }
        List<Cluster> tempClusters = new ArrayList<>();
        for (double cluster : clusters) {
            tempClusters.add(new Cluster(cluster, null, 0));
        }
        return process(tempClusters, clustersAmount, linkageCriterion);
    }

    /**
     * Perform an agglomeration given an list of Cluster
     * @param clusters non-empty list containing Cluster objects
     * @param clustersAmount amount of clusters we want to end up with
     * @param linkageCriterion the criteria that will be used to perform the agglomeration of two clusters
     * @return a list of agglomerated clusters
     * @throws InvalidClusterException if the clusters array are empty
     */
    public static List<Cluster> process(List<Cluster> clusters, int clustersAmount, LinkageCriterion linkageCriterion) throws InvalidClusterException {
        if (clusters == null) {
            throw new NullPointerException("Clusters were null");
        } else if (clusters.isEmpty()) {
            throw new InvalidClusterException("Cluster values were less than 1");
        }
        while (clustersAmount < clusters.size()) {
            Cluster newCluster = agglomerate(clusters, linkageCriterion);
            clusters.remove(newCluster.getParents()[0]);
            clusters.remove(newCluster.getParents()[1]);
            clusters.add(newCluster);
        }
        return clusters;
    }

    /**
     * This method will perform an agglomeration given a list of clusters
     * following the specified linkage criteria.
     * @param clusters the list of clusters
     * @param linkageCriterion the linkage criterion we want to use
     * @return a merged cluster
     */
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
