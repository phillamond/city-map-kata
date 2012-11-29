package bbc.kata.helpers;

import bbc.kata.Journey;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CityMapNavigatorTest {

    private CityMapNavigator cityMapNavigator = new CityMapNavigator();

    @Test
    public void shouldReturnTrueWhereJourneyEqualsSingleRoad() {
        cityMapNavigator.setRoads(Arrays.asList("a-b","b-a"));
        cityMapNavigator.setJourney(new Journey("a","b"));

        assertThat(cityMapNavigator.equalsSingleRoad(), is(true));
    }

    @Test
    public void shouldReturnTrueWhereJourneyIsRoundTrip() {
        cityMapNavigator.setRoads(Arrays.asList("a-b","b-c","c-a"));
        cityMapNavigator.setJourney(new Journey("a","c"));

        assertThat(cityMapNavigator.isRoundTrip(), is(true));

        cityMapNavigator.setJourney(new Journey("a","a"));
        assertThat(cityMapNavigator.isRoundTrip(), is(true));

        assertThat(cityMapNavigator.equalsSingleRoad(), is(false));
    }
}
