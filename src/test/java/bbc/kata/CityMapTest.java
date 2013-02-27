package bbc.kata;

import bbc.kata.citymap.Navigator;
import org.junit.Test;

import java.util.*;

import static com.google.common.collect.Sets.newHashSet;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class CityMapTest {

    private CityMap cityMap;
    private Navigator navigator = new Navigator();

    @Test
    public void shouldBuildGraphCorrectly() {
        buildTestGraph();
        cityMap = new CityMap(navigator, "a-b,b-a,a-c");
        assertGraphIsValid(cityMap.getDestinationGraph());
    }


    @Test
    public void shouldReturnTrueForPossibleLinearJourney() {
        cityMap = new CityMap(navigator, "a-b,b-a");
        assertThat(cityMap.isJourneyPossible("a","b"), is(true));
        assertThat(cityMap.isJourneyPossible("b","a"), is(true));
    }

    @Test
    public void shouldReturnFalseForJourneyWhereThereIsNoRoad() {
        cityMap = new CityMap(navigator, "a-b,b-a");
        assertThat(cityMap.isJourneyPossible("c","d"), is(false));
    }

    @Test
    public void shouldReturnTrueForOneWayRoundTrip() {
        cityMap = new CityMap(navigator, "a-b,b-c,c-a");
        assertThat(cityMap.isJourneyPossible("a","b"), is(true));
        assertThat(cityMap.isJourneyPossible("a","c"), is(true));
        assertThat(cityMap.isJourneyPossible("a","a"), is(true));
    }

    @Test
    public void shouldReturnFalseForJourneyOutsideOfRoundTrip() {
        cityMap = new CityMap(navigator, "a-b,b-c,c-a");
        assertThat(cityMap.isJourneyPossible("c","d"), is(false));
    }

    @Test
    public void graphSpike() {
        Map<String, Set<String>> map = new HashMap<String, Set<String>>();
        Set aSet = new HashSet<String>();
        aSet.addAll(Arrays.asList("b, c"));
        map.put("a", aSet);
        Set bSet = new HashSet<String>();
        bSet.add("c");
        map.put("b", bSet);

        // overwrite
        bSet.add("c");

        System.out.println(map);
    }

    private void assertGraphIsValid(Map<String, Set<String>> destinationGraph) {
        assertThat(destinationGraph.get("a"), is(notNullValue()));
        assertThat(destinationGraph.get("a").contains("b"), is(true));
        assertThat(destinationGraph.get("a").contains("c"), is(true));
        assertThat(destinationGraph.get("b"), is(notNullValue()));
        assertThat(destinationGraph.get("b").contains("a"), is(true));
    }

    private void buildTestGraph() {
        Map<String, Set<String>> testGraph = new HashMap<String, Set<String>>();
        testGraph.put("a", newHashSet("b", "c"));
        testGraph.put("b", newHashSet("a"));
    }
}
