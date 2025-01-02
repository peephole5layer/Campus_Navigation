package com.campusNavigaton.Navigation.model;

public class Edge {
    private String destination;
    private int weight;

    public Edge(String destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }

    public String getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }
}
