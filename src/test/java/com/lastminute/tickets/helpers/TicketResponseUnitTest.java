package com.lastminute.tickets.helpers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.lastminute.flights.Flight;
import com.lastminute.tickets.TicketType;
import com.lastminute.tickets.calculi.PriceHandler;
import com.lastminute.tickets.helpers.TicketResponse;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import static org.fest.assertions.Assertions.*;

public class TicketResponseUnitTest {
    
    private static final String A_DESCRIPTION = "description";
    private static final double A_PRICE = 1.0;
    private static final int TICKET1_PASSENGERS = 2;
    private static final int TICKET2_PASSENGERS = 3;
    private static final int TOTAL = TICKET1_PASSENGERS + TICKET2_PASSENGERS;
    
    @Mock private Flight flight;
    @Mock private PriceHandler priceHandler;
    
    private TicketResponse ticketResponse;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ticketResponse = new TicketResponse(flight, priceHandler);
    }
    
    @Test
    public void testDescription() {
        when(flight.description()).thenReturn(A_DESCRIPTION);
        
        assertThat(ticketResponse.flightDescription()).isEqualTo(A_DESCRIPTION);
    }
    
    @Test
    public void testPrice() {
        when(priceHandler.price()).thenReturn(A_PRICE);
        
        assertThat(ticketResponse.price()).isEqualTo(A_PRICE);
    }
    
    @Test
    public void testTickets() {
        List<TicketType> tickets = createTickets();
        when(priceHandler.tickets()).thenReturn(tickets);
        
        assertThat(ticketResponse.tickets()).isEqualTo(tickets);
    }
    
    @Test
    public void testPassengers() {
        List<TicketType> tickets = createTickets();
        when(tickets.get(0).passengers()).thenReturn(TICKET1_PASSENGERS);
        when(tickets.get(1).passengers()).thenReturn(TICKET2_PASSENGERS);        
        when(priceHandler.tickets()).thenReturn(tickets);
        
        assertThat(ticketResponse.totalPassengers()).isEqualTo(TOTAL);
    }
    
    private List<TicketType> createTickets() {
        TicketType ticket1 = mock(TicketType.class);
        TicketType ticket2 = mock(TicketType.class);
        return Arrays.asList(ticket1, ticket2);
        
    }
    
}
