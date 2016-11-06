package com.lastminute.flights;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.fest.assertions.Assertions.*;

public class FlightUnitTest {
    
    private static final String A_DESCRIPTION = "description";
    private static final double A_PRICE = 1.0;
    private static final double ANOTHER_PRICE = 2.0;
    private static final String AN_ID = "id";
    private static final Airline AN_AIRLINE = Airline.IBERIA;
    
    private Trip trip;
    
    private Flight flight;
    
    @Before
    public void setUp() {
        trip = mock(Trip.class);
        flight = new Flight(A_DESCRIPTION, trip, AN_AIRLINE, A_PRICE);
    }
    
    @Test
    public void testDescription() {
        assertThat(flight.description()).isEqualTo(A_DESCRIPTION);
    }
    
    @Test
    public void testGetPrice() {
        assertThat(flight.getPrice()).isEqualTo(A_PRICE);
    }
    
    @Test
    public void testSetPrice() {
        flight.setPrice(ANOTHER_PRICE);
        assertThat(flight.getPrice()).isEqualTo(ANOTHER_PRICE);
    }
    
    @Test
    public void testId() {
        when(trip.id()).thenReturn(AN_ID);
        
        assertThat(flight.id()).isEqualTo(AN_ID + AN_AIRLINE.description());
    }
    
    @Test
    public void testTrip() {
        assertThat(flight.trip()).isEqualTo(trip);
    }
    
    @Test
    public void testInfantPrice() {
        assertThat(flight.infantPrice()).isEqualTo(AN_AIRLINE.infantPrice());
    }
}
