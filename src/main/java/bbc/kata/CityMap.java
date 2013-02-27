package bbc.kata;

import bbc.kata.citymap.Journey;
import bbc.kata.citymap.Navigator;

import java.util.*;

public class CityMap {

    public static final String JOURNEY_DELIMITER = ",";
    public static final String ROAD_DELIMITER = "-";

    private final Navigator navigator;
    private final String roadsAndLocations;
    private Map<String, Set<String>> destinationGraph = new HashMap<String, Set<String>>();

    /**
     * @param navigator
     * @param roadsAndLocations A String specification of the
     *                          connections of roads and locations. A comma-separated list
     *                          of roads between locations defined as
     *                          [startLocation]-[endLocation] e.g.
     *                          <p/>
     *                          "a-b,b-a"     represents two locations, where travel
     *                          is possible in both directions
     *                          "a-b,b-c,c-a" represents a triangular one-way road travelling
     */
    public CityMap(Navigator navigator, String roadsAndLocations) {
        this.navigator = navigator;
        this.roadsAndLocations = roadsAndLocations;
        buildGraphWithRoadsAndLocations();
    }

    protected void buildGraphWithRoadsAndLocations() {
        List<String> roads = Arrays.asList(roadsAndLocations.split(JOURNEY_DELIMITER));

        for (String road : roads) {
            List<String> roadStartAndEnd = Arrays.asList(road.split(ROAD_DELIMITER));
            String start = roadStartAndEnd.get(0);
            String end = roadStartAndEnd.get(1);

            if (start != null && end != null) {
                addDestinations(start, end);
            }

        }
    }

    /**
     * Only intended for use inside unit test
     */
    protected Map<String, Set<String>> getDestinationGraph() {
        return destinationGraph;
    }

    private void addDestinations(String start, String end) {
        Set destinationSet;
        if (destinationGraph.containsKey(start)) {
            destinationSet = destinationGraph.get(start);
            destinationSet.add(end);
        } else {
            destinationSet = new HashSet<String>();
            destinationSet.add(end);
            destinationGraph.put(start, destinationSet);
        }
    }

    /**
     * Determine whether a journey from the startLocation to the
     * destinationLocation can be made, based on the available roads
     */
    public boolean isJourneyPossible(
            String startLocation,
            String destinationLocation) {

        Journey journey = new Journey(startLocation, destinationLocation);
        navigator.setJourney(journey);
        navigator.setDestinations(destinationGraph);

        return navigator.search();
    }
}
