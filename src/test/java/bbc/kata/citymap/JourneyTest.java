package bbc.kata.citymap;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class JourneyTest {

    private Journey journey = new Journey("a", "b");

    @Test
    public void shouldReturnStartLocation() {
        assertThat(journey.getStartLocation(), is("a"));
    }

    @Test
    public void shouldReturnEndLocation() {
        assertThat(journey.getDestinationLocation(), is("b"));
    }

    @Test
    public void hasCorrectStringRepresentation() {
        assertThat(journey.toString(), is("a-b"));
    }
}
