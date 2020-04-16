package com.github.dhernandez0798;

public class Main {

    public static void main(String[] args) {
        double[] inputArray = {5, 88, 326.4, 617, 8412, 71824, 412, 2};
        AgglomerativeClustering.proccess(inputArray, 4, new SingleLinkageCriterion(new EuclideanDistance()));
    }
}
