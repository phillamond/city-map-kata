package bbc.kata;

import bbc.kata.helpers.CityMapNavigator;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class CityMapTest {

    private CityMap cityMap;
    private CityMapNavigator cityMapNavigator = mock(CityMapNavigator.class);

    @Test
    public void shouldReturnTrueForPossibleLinearJourney() {
        cityMap = new CityMap("a-b,b-a");
        given(cityMapNavigator.equalsSingleRoad()).willReturn(true);

        assertThat(cityMap.isJourneyPossible("a","b"), is(possible()));
        assertThat(cityMap.isJourneyPossible("b","a"), is(possible()));
    }

    @Test
    public void shouldReturnFalseForJourneyWhereThereIsNoRoad() {
        cityMap = new CityMap("a-b,b-a");
        given(cityMapNavigator.equalsSingleRoad()).willReturn(false);

        assertThat(cityMap.isJourneyPossible("c","d"), is(impossible()));
    }

    @Test
    public void shouldReturnTrueForOneWayRoundTrip() {
        cityMap = new CityMap("a-b,b-c,c-a");
        given(cityMapNavigator.isRoundTrip()).willReturn(true);

        assertThat(cityMap.isJourneyPossible("a","b"), is(possible()));
        assertThat(cityMap.isJourneyPossible("a","c"), is(possible()));
        assertThat(cityMap.isJourneyPossible("a","a"), is(possible()));

    }

    private boolean possible() {
        return true;
    }

    private boolean impossible() {
        return false;
    }
}
