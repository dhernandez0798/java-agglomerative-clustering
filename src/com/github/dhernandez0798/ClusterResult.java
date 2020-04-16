package com.github.dhernandez0798;

public class ClusterResult {
    private Cluster cluster;
    private int[] closestPair;

    public ClusterResult(Cluster cluster, int[] closestPair) {
        this.cluster = cluster;
        this.closestPair = closestPair;
    }

    public Cluster getCluster() {
        return cluster;
    }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    public int[] getClosestPair() {
        return closestPair;
    }

    public void setClosestPair(int[] closestPair) {
        this.closestPair = closestPair;
    }
}
