package bbc.kata;

import bbc.kata.helpers.CityMapNavigator;

import java.util.Arrays;
import java.util.List;

public class CityMap {

    private final CityMapNavigator cityMapNavigator;
    private final String roadsAndLocations;

    /**
     * @param cityMapNavigator
     * @param roadsAndLocations A String specification of the
     *                          connections of roads and locations. A comma-separated list
     *                          of roads between locations defined as
     *                          [startLocation]-[endLocation] e.g.
     *                          <p/>
     *                          "a-b,b-a"     represents two locations, where travel
     *                          is possible in both directions
     *                          "a-b,b-c,c-a" represents a triangular one-way road travelling
     */
    public CityMap(CityMapNavigator cityMapNavigator, String roadsAndLocations) {
        this.cityMapNavigator = cityMapNavigator;
        this.roadsAndLocations = roadsAndLocations;
    }

    /**
     * Determine whether a journey from the startLocation to the
     * destinationLocation can be made, based on the available roads
     */
    public boolean isJourneyPossible(
            String startLocation,
            String destinationLocation) {

        Journey journey = new Journey(startLocation, destinationLocation);
        List<String> roads = Arrays.asList(roadsAndLocations.split(","));
        cityMapNavigator.setJourney(journey);
        cityMapNavigator.setRoads(roads);

        if (cityMapNavigator.equalsSingleRoad()) return true;

        if (cityMapNavigator.isRoundTrip()) return true;

        return false;
    }

}
