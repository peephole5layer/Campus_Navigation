package com.campusNavigaton.Navigation.controller;


import com.campusNavigaton.Navigation.model.Graph;
import com.campusNavigaton.Navigation.service.DijkstraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class NavigationController {

    @Autowired
    private DijkstraService dijkstraService;

    private Graph campusMap;
    private Map<String, double[]> coordinates;

    public NavigationController() {
        campusMap = new Graph();
        coordinates = new HashMap<>();

        // Initialize the graph with nodes and edges
        campusMap.addNode("Main-Gate");
        campusMap.addNode("Library");
        campusMap.addNode("Canteen");
        campusMap.addEdge("Main-Gate", "Library", 5);
        campusMap.addEdge("Main-Gate", "Canteen", 10);
        campusMap.addEdge("Library", "Canteen", 15);

        // Add coordinates for each node
        coordinates.put("Main-Gate", new double[]{37.7749, -122.4194});
        coordinates.put("Library", new double[]{37.7789, -122.4194});
        coordinates.put("Canteen", new double[]{37.7799, -122.4234});
    }

    @PostMapping("/navigate")
    public String navigate(@RequestParam String source, @RequestParam String destination, Model model) {
        var path = dijkstraService.findShortestPath(campusMap, source, destination);
        model.addAttribute("path", path);
        model.addAttribute("coordinates", coordinates);
        return "result";
    }
}


