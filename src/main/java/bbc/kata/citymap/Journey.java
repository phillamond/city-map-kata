package bbc.kata.citymap;

public class Journey {
    private final String startLocation;
    private final String destinationLocation;

    public Journey(String startLocation, String destinationLocation) {
        this.startLocation = startLocation;
        this.destinationLocation = destinationLocation;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public String getDestinationLocation() {
        return destinationLocation;
    }

    public String toString() {
        return startLocation + "-" + destinationLocation;
    }
}
