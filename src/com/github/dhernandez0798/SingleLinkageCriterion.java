package com.github.dhernandez0798;

public class SingleLinkageCriterion implements LinkageCriterion {
    @Override
    public double calculate(DistanceFunction distanceFunction, Cluster c1, Cluster c2) {
        double[] cluster1Values = c1.getValues();
        double[] cluster2Values = c2.getValues();
        double distance = Double.POSITIVE_INFINITY;
        if (cluster1Values.length > 0 && cluster2Values.length > 0) {
            for (int i = 0; i < cluster1Values.length; i++) {
                for (int j = 0; j < cluster2Values.length; j++) {
                    double tempDistance = distanceFunction.calculate(cluster1Values[0], cluster2Values[0]);
                    if (tempDistance < distance)
                        distance = tempDistance;
                }
            }
        } else {
            // TODO: Throw EmptyClusterValuesException
        }
        return distance;
    }
}
