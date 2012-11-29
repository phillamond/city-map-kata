package bbc.kata.helpers;

import bbc.kata.Journey;

import java.util.List;

public class CityMapNavigator {

    private Journey journey;
    private List<String> roads;

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

    public void setJourney(Journey journey) {
        this.journey = journey;
    }

    public void setRoads(List<String> roads) {
        this.roads = roads;
    }
}