package com.lastminute.flights;

import org.junit.Before;
import org.junit.Test;

import com.lastminute.locations.Airport;

import static org.fest.assertions.Assertions.*;

public class TripUnitTest {
    
    private static Airport AN_AIRPORT = Airport.AMS;
    private static Airport ANOTHER_AIRPORT = Airport.BCN;
    
    private Trip trip;
    
    @Before
    public void setUp() {
        trip = new Trip(AN_AIRPORT, ANOTHER_AIRPORT);
    }
    
    @Test
    public void testOrigin() {
        assertThat(trip.origin()).isEqualTo(AN_AIRPORT);
    }
    
    @Test
    public void testDetination() {
        assertThat(trip.destination()).isEqualTo(ANOTHER_AIRPORT);
    }
    
    @Test
    public void testId() {
        String id = AN_AIRPORT.code() + ANOTHER_AIRPORT.code();
        assertThat(trip.id()).isEqualTo(id);
    }
    
    @Test
    public void testHashCode() {
        String id = AN_AIRPORT.code() + ANOTHER_AIRPORT.code();
        assertThat(trip.hashCode()).isEqualTo(id.hashCode()); 
    }
    
    @Test
    public void testEquals() {
        Trip same = new Trip(trip.origin(), trip.destination());
        assertThat(trip).isEqualTo(same);
    }
    
    @Test
    public void testNotEquals() {
        Trip notSame = new Trip(trip.destination(), trip.origin());
        assertThat(trip).isNotEqualTo(notSame);
    }

}
