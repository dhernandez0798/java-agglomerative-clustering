package com.github.dhernandez0798;

/**
 * Exception to be throw if the values of the clusters are not valid.
 * For example: empty cluster.
 */
public class InvalidClusterException extends Exception {
    public InvalidClusterException(String message) {
        super(message);
    }
}
