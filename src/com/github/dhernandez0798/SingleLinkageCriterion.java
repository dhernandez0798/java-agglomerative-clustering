package com.github.dhernandez0798;

public class SingleLinkageCriterion implements LinkageCriterion {
    private DistanceFunction distanceFunction = null;

    public SingleLinkageCriterion(DistanceFunction distanceFunction) {
        this.distanceFunction = distanceFunction;
    }

    /**
     * Given two clusters with 1 or more points, return the distance between the closest
     * from both clusters.
     * @param c1 The first cluster we are comparing
     * @param c2 The second cluster we are comparing
     * @return the minimum distance between two clusters
     */
    @Override
    public double calculate(Cluster c1, Cluster c2) {
        double[] cluster1Values = c1.getValues();
        double[] cluster2Values = c2.getValues();
        double distance = Double.POSITIVE_INFINITY;
        for (int i = 0; i < cluster1Values.length; i++) {
            for (int j = 0; j < cluster2Values.length; j++) {
                double tempDistance = distanceFunction.calculate(cluster1Values[0], cluster2Values[0]);
                if (tempDistance < distance)
                    distance = tempDistance;
            }
        }
        return distance;
    }
}
