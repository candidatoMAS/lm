package com.lastminute.tickets.helpers;

import org.junit.Before;
import org.junit.Test;

import com.lastminute.flights.Trip;
import com.lastminute.tickets.helpers.TicketRequest;
import com.lastminute.tickets.helpers.TicketRequest.TicketRequestBuilder;

import static org.mockito.Mockito.*;

import static org.fest.assertions.Assertions.*;

public class TicketRequestUnitTest {
    
    private static final int A_NUMBER_OF_ADULTS = 1;
    private static final int A_NUMBER_OF_CHILDREN = 2;
    private static final int A_NUMBER_OF_INFANTS = 3;
    private static final int A_NUMBER_OF_DAYS_TO = 4;
    
    private static final Trip INVALID_TRIP = null;
    private static final int INVALID_DAYS_TO = -1;
    
    private Trip trip;
    
    @Before
    public void setUp() {
        trip = mock(Trip.class);
    }
    
    @Test
    public void testTrip() {
        TicketRequestBuilder builder = new TicketRequestBuilder(trip, 0);
        TicketRequest ticketRequest = builder.build();
        
        assertThat(ticketRequest.trip()).isEqualTo(trip);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidTrip() {
        new TicketRequestBuilder(INVALID_TRIP, 0);
    }
    
    @Test
    public void testDaysTo() {
        TicketRequestBuilder builder = new TicketRequestBuilder(trip, A_NUMBER_OF_DAYS_TO);
        TicketRequest ticketRequest = builder.build();
        
        assertThat(ticketRequest.daysTo()).isEqualTo(A_NUMBER_OF_DAYS_TO);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDaysTo() {
        new TicketRequestBuilder(trip, INVALID_DAYS_TO);
    }
    
    @Test
    public void testAdults() {
        TicketRequestBuilder builder = new TicketRequestBuilder(trip, 0).and().adults(A_NUMBER_OF_ADULTS);
        TicketRequest ticketRequest = builder.build();
        
        assertThat(ticketRequest.adults()).isEqualTo(A_NUMBER_OF_ADULTS);
    }
    
    @Test
    public void testChildren() {
        TicketRequestBuilder builder = new TicketRequestBuilder(trip, 0).with().children(A_NUMBER_OF_CHILDREN);
        TicketRequest ticketRequest = builder.build();
        
        assertThat(ticketRequest.children()).isEqualTo(A_NUMBER_OF_CHILDREN);
    }
    
    @Test
    public void testInfants() {
        TicketRequestBuilder builder = new TicketRequestBuilder(trip, 0).with().infants(A_NUMBER_OF_INFANTS);
        TicketRequest ticketRequest = builder.build();
        
        assertThat(ticketRequest.infants()).isEqualTo(A_NUMBER_OF_INFANTS);
    }
    
    @Test
    public void testSelfMethods() {
        TicketRequestBuilder builder = new TicketRequestBuilder(trip, 0);
        
        assertThat(builder.and()).isEqualTo(builder);
        assertThat(builder.with()).isEqualTo(builder);
    }
    
    @Test
    public void testAllParameters() {
        TicketRequestBuilder builder = new TicketRequestBuilder(trip, A_NUMBER_OF_DAYS_TO);
        builder.with().adults(A_NUMBER_OF_ADULTS).and().children(A_NUMBER_OF_CHILDREN).and().infants(A_NUMBER_OF_INFANTS);
        TicketRequest ticketRequest = builder.build();
        
        assertThat(ticketRequest.trip()).isEqualTo(trip);
        assertThat(ticketRequest.daysTo()).isEqualTo(A_NUMBER_OF_DAYS_TO);
        assertThat(ticketRequest.adults()).isEqualTo(A_NUMBER_OF_ADULTS);
        assertThat(ticketRequest.children()).isEqualTo(A_NUMBER_OF_CHILDREN);
        assertThat(ticketRequest.infants()).isEqualTo(A_NUMBER_OF_INFANTS);
    }
    
}
