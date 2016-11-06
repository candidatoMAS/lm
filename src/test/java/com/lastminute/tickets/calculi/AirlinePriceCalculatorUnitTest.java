package com.lastminute.tickets.calculi;

import org.junit.Before;
import org.junit.Test;

import com.lastminute.flights.Flight;
import com.lastminute.tickets.DaysPolicy;
import com.lastminute.tickets.TicketType;
import com.lastminute.tickets.calculi.PriceHandler;
import com.lastminute.tickets.helpers.TicketRequest;

import static org.mockito.Mockito.*;

import static org.fest.assertions.Assertions.*;

public class AirlinePriceCalculatorUnitTest {
    
    private static final double A_PRICE = 7.0;
    private static final double INFANT_PRICE = 5.0;
    private static final int ADULTS = 1;
    private static final int CHILDREN = 2;
    private static final int INFANTS = 3;
    private static final int DAYS_TO = 17;
    
    private TicketRequest ticketRequest;
    private Flight flight;
    
    @Before
    public void setUp() {
        ticketRequest = mock(TicketRequest.class);
        flight = mock(Flight.class);
    }
    
    @Test
    public void testCreateHandler() {
        when(flight.getPrice()).thenReturn(A_PRICE);
        when(flight.infantPrice()).thenReturn(INFANT_PRICE);
        when(ticketRequest.daysTo()).thenReturn(DAYS_TO);
        when(ticketRequest.adults()).thenReturn(ADULTS);
        when(ticketRequest.children()).thenReturn(CHILDREN);
        when(ticketRequest.infants()).thenReturn(INFANTS);
        
        AirlinePriceCalculator calculator = new AirlinePriceCalculator(ticketRequest, flight);
        PriceHandler handler = calculator.createHandler();
        
        TicketType ticket1 = handler.tickets().get(0);
        TicketType ticket2 = handler.tickets().get(1);
        TicketType ticket3 = handler.tickets().get(2);
        
        assertThat(ticket1.passengers()).isEqualTo(ADULTS);
        assertThat(ticket2.passengers()).isEqualTo(CHILDREN);
        assertThat(ticket3.passengers()).isEqualTo(INFANTS);
        
        assertThat(ticket1.originalPrice()).isEqualTo(A_PRICE);
        assertThat(ticket2.originalPrice()).isEqualTo(A_PRICE);
        assertThat(ticket3.originalPrice()).isEqualTo(INFANT_PRICE);

        assertThat(ticket1.policies()).hasSize(1);
        assertThat(ticket2.policies()).hasSize(1);
        assertThat(ticket3.policies()).hasSize(1);
        
        assertThat(ticket1.policies()).contains(DaysPolicy.fromDaysTo(DAYS_TO));
        assertThat(ticket2.policies()).contains(DaysPolicy.fromDaysTo(DAYS_TO));
        assertThat(ticket3.policies()).contains(DaysPolicy.NO_POLICY);
    }
    
}
