package com.lastminute.tickets.calculi;

import org.junit.Before;
import org.junit.Test;

import com.lastminute.db.DatabaseException;
import com.lastminute.db.FlightsDatabase;
import com.lastminute.db.FlightsFileDatabase;
import com.lastminute.flights.Flight;
import com.lastminute.flights.Trip;
import com.lastminute.printers.Printer;
import com.lastminute.tickets.calculi.LastminuteCalculator;
import com.lastminute.tickets.helpers.TicketRequest;
import com.lastminute.tickets.helpers.TicketResponse;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import static org.fest.assertions.Assertions.*;

public class LastminuteCalculatorUnitTest {
    
    private static final String FLIGHT1_DESCRIPTION = "description1";
    private static final String FLIGHT2_DESCRIPTION = "description2";
    private static final double FLIGHT1_PRICE = 10.0;
    private static final double FLIGHT2_PRICE = 20.0;
    private static final double RESPONSE1_PRICE = FLIGHT1_PRICE * 1.5;
    private static final double RESPONSE2_PRICE = FLIGHT2_PRICE * 1.5;
    private static final int DAYS_TO = 2;
    private static final int ADULTS = 1;
    
    
    private FlightsDatabase db;
    
    private LastminuteCalculator calculator;
    
    @Before
    public void setUp() throws DatabaseException {
        db = spy(new FlightsFileDatabase());
        calculator = new LastminuteCalculator(db);
    }
    
    @Test
    public void testGetAvailable() {
        Flight flight1 = mock(Flight.class);
        Flight flight2 = mock(Flight.class);
        when(flight1.description()).thenReturn(FLIGHT1_DESCRIPTION);
        when(flight2.description()).thenReturn(FLIGHT2_DESCRIPTION);
        when(flight1.getPrice()).thenReturn(FLIGHT1_PRICE);
        when(flight2.getPrice()).thenReturn(FLIGHT2_PRICE);
        
        Trip trip = mock(Trip.class);
        TicketRequest request = mock(TicketRequest.class);
        when(request.trip()).thenReturn(trip);
        when(request.adults()).thenReturn(ADULTS);
        when(request.daysTo()).thenReturn(DAYS_TO);
        
        when(db.getFlights(trip)).thenReturn(Arrays.asList(flight1, flight2));
        
        List<TicketResponse> responses = calculator.getAvailable(request);
        assertThat(responses).hasSize(2);
        
        TicketResponse response1 = responses.get(0);
        TicketResponse response2 = responses.get(1);
        
        assertThat(response1.flightDescription()).isEqualTo(FLIGHT1_DESCRIPTION);
        assertThat(response2.flightDescription()).isEqualTo(FLIGHT2_DESCRIPTION);
        
        assertThat(response1.price()).isEqualTo(RESPONSE1_PRICE);
        assertThat(response2.price()).isEqualTo(RESPONSE2_PRICE);

        assertThat(response1.totalPassengers()).isEqualTo(ADULTS);
        assertThat(response2.totalPassengers()).isEqualTo(ADULTS);
    }
    
    @Test
    public void testPrintAvailable() {
        TicketRequest request = mock(TicketRequest.class);
        Printer printer = mock(Printer.class);
        
        List<TicketResponse> responses = calculator.getAvailable(request);
        calculator.printAvalaible(request, printer);
        
        verify(printer, times(1)).print(responses);
    }
}
