package bbc.kata.citymap;

import java.util.*;

public class Navigator {

    private Journey journey;
    private Map<String, Set<String>> graph;

    /**
     * Search the provided destinations graph, breadth-first:
     * http://en.wikipedia.org/wiki/Breadth-first_search#Pseudocode
     */
    public boolean search() {
        Set<String> visited = new HashSet<String>();
        final Queue<String> queue = new LinkedList<String>();
        String startLocation = journey.getStartLocation();
        queue.add(startLocation);
        visited.add(startLocation);

        while (!queue.isEmpty()) {
            String currentLocation = queue.poll();
            if (currentLocation.equals(journey.getDestinationLocation())) {
                return true;
            }
            Set<String> neighbouringLocations = graph.get(currentLocation);
            addUnvisitedNeigboursToQueue(visited, queue, neighbouringLocations);
        }

        return false;
    }

    private void addUnvisitedNeigboursToQueue(Set<String> visited, Queue<String> queue, Set<String> neighbouringLocations) {
        if (neighbouringLocations != null) {
            for (String neighbouringLocation : neighbouringLocations) {
                if (!visited.contains(neighbouringLocation)) {
                    queue.add(neighbouringLocation);
                }
            }
        }
    }

    public void setJourney(Journey journey) {
        this.journey = journey;
    }

    public void setDestinations(Map<String, Set<String>> destinations) {
        this.graph = destinations;
    }
}