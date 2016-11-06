package com.lastminute.tickets.calculi;

import org.junit.Before;
import org.junit.Test;

import com.lastminute.tickets.TicketType;
import com.lastminute.tickets.calculi.PriceHandler;

import static org.mockito.Mockito.*;

import static org.fest.assertions.Assertions.*;

public class PriceHandlerUnitTest {
    
    private static final double TICKET1_PRICE = 16.0;
    private static final double TICKET2_PRICE = 7.0;
    private static final double TOTAL = TICKET1_PRICE + TICKET2_PRICE;
    
    private TicketType ticket1;
    private TicketType ticket2;
    
    @Before
    public void setUp() {
        ticket1 = mock(TicketType.class);
        ticket2 = mock(TicketType.class);
    }
    
    @Test
    public void testTickets() {
        PriceHandler handler = new PriceHandler(ticket1, ticket2);
        
        assertThat(handler.tickets()).hasSize(2);
        assertThat(handler.tickets()).contains(ticket1, ticket2);
    }
    
    @Test
    public void testCalculateFinalPrice() {
        when(ticket1.calculateFinalPrice()).thenReturn(TICKET1_PRICE);
        when(ticket2.calculateFinalPrice()).thenReturn(TICKET2_PRICE);
        
        PriceHandler handler = new PriceHandler(ticket1, ticket2);
        
        assertThat(handler.price()).isEqualTo(TOTAL);        
        verify(ticket1, times(1)).calculateFinalPrice();
        verify(ticket2, times(1)).calculateFinalPrice();
    }
    
    
}
