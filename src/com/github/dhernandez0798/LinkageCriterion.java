package com.github.dhernandez0798;

public interface LinkageCriterion {
    double calculate(DistanceFunction distanceFunction, Cluster c1, Cluster c2);
}
