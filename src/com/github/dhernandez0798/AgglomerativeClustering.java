package com.github.dhernandez0798;

import java.util.ArrayList;
import java.util.List;

public class AgglomerativeClustering {
    List<Cluster> clusters = new ArrayList<>();

    public AgglomerativeClustering(List<Cluster> clusters) {
        this.clusters.addAll(clusters);
    }

    public AgglomerativeClustering(double[] clusters) {
        for (double cluster : clusters) {
            this.clusters.add(new Cluster(cluster));
        }
    }

    public void process(LinkageCriterion linkageCriterion) {
        
    }

    private ClusterResult agglomerate(LinkageCriterion linkageCriterion) {
        double distance = Double.POSITIVE_INFINITY;
        int[] closestPair = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE};
        for (int i = 0; i < clusters.size(); i++) {
            for (int j = 0; j < clusters.size(); j++) {
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
        Cluster mergedCluster = Cluster.mergeClusters(
                clusters.get(closestPair[0]),
                clusters.get(closestPair[1])
        );
        return new ClusterResult(mergedCluster, closestPair);
    }
}
