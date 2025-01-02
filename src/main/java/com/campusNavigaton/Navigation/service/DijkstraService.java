package com.campusNavigaton.Navigation.service;


import com.campusNavigaton.Navigation.model.Edge;
import com.campusNavigaton.Navigation.model.Graph;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DijkstraService {

    public List<String> findShortestPath(Graph graph, String start, String end) {
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previousNodes = new HashMap<>();
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));
        Set<String> visited = new HashSet<>();

        for (String node : graph.getNodes()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(start, 0);
        queue.add(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            if (visited.contains(currentNode.name)) continue;

            visited.add(currentNode.name);

            for (Edge edge : graph.getEdges(currentNode.name)) {
                if (visited.contains(edge.getDestination())) continue;

                int newDistance = distances.get(currentNode.name) + edge.getWeight();
                if (newDistance < distances.get(edge.getDestination())) {
                    distances.put(edge.getDestination(), newDistance);
                    previousNodes.put(edge.getDestination(), currentNode.name);
                    queue.add(new Node(edge.getDestination(), newDistance));
                }
            }
        }

        return reconstructPath(previousNodes, start, end);
    }

    private List<String> reconstructPath(Map<String, String> previousNodes, String start, String end) {
        List<String> path = new LinkedList<>();
        for (String at = end; at != null; at = previousNodes.get(at)) {
            path.add(0, at);
        }
        return path.get(0).equals(start) ? path : Collections.emptyList();
    }

    private static class Node {
        String name;
        int distance;

        public Node(String name, int distance) {
            this.name = name;
            this.distance = distance;
        }
    }
}
