package com.campusNavigaton.Navigation.model;

import java.util.*;

public class Graph {
    private Map<String, List<Edge>> adjacencyList = new HashMap<>();

    public void addNode(String node) {
        adjacencyList.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge(String source, String destination, int weight) {
        adjacencyList.get(source).add(new Edge(destination, weight));
        adjacencyList.get(destination).add(new Edge(source, weight)); // Bidirectional
    }

    public List<Edge> getEdges(String node) {
        return adjacencyList.get(node);
    }

    public Set<String> getNodes() {
        return adjacencyList.keySet();
    }
}

