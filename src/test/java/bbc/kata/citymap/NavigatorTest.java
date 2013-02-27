package bbc.kata.citymap;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class NavigatorTest {

    private Navigator navigator = new Navigator();
    private Map<String, Set<String>> graph = new HashMap<String, Set<String>>();

    @Before
    public void setUp() {
        Set<String> locationsForA = newHashSet("b","c");
        graph.put("a", locationsForA);
        Set<String> locationsForB = newHashSet("c");
        graph.put("b", locationsForB);
        navigator.setDestinations(graph);
    }

    @Test
    public void shouldSearchGivenGraphForPossibleJourney() {
        navigator.setJourney(new Journey("a", "b"));
        assertThat(navigator.search(), is(true));

        navigator.setJourney(new Journey("a", "c"));
        assertThat(navigator.search(), is(true));

        navigator.setJourney(new Journey("b", "c"));
        assertThat(navigator.search(), is(true));
    }

    @Test
    public void shouldSearchGivenGraphForImpossibleJourney() {
        navigator.setJourney(new Journey("b", "a"));
        assertThat(navigator.search(), is(false));

        navigator.setJourney(new Journey("c", "a"));
        assertThat(navigator.search(), is(false));

        navigator.setJourney(new Journey("x", "z"));
        assertThat(navigator.search(), is(false));
    }


}
