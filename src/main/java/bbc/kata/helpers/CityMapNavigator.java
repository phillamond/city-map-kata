package bbc.kata.helpers;

import bbc.kata.Journey;

import java.util.List;

public class CityMapNavigator {

    private final Journey journey;
    private final List<String> roads;

    public CityMapNavigator(Journey journey, List<String> roads) {
        this.journey = journey;
        this.roads = roads;
    }

    public boolean equalsSingleRoad() {
        for (String road : roads) {
            if (journey.toString().equals(road)) return true;
        }
        return false;
    }

    public boolean isRoundTrip() {
        boolean matchesStartLocation = false;
        boolean matchesEndLocation = false;
        for (String road : roads) {
            if (road.startsWith(journey.getStartLocation())) matchesStartLocation = true;
            if (road.endsWith(journey.getDestinationLocation())) matchesEndLocation = true;
        }
        if (matchesStartLocation && matchesEndLocation) return true;
        return false;
    }
}