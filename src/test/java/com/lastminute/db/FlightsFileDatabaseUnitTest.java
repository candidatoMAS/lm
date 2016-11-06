package com.lastminute.db;

import static org.mockito.Mockito.*;

import java.util.List;

import static org.fest.assertions.Assertions.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.lastminute.flights.Flight;
import com.lastminute.flights.Trip;
import com.lastminute.locations.Airport;

public class FlightsFileDatabaseUnitTest {
    
    @Rule public ExpectedException thrown = ExpectedException.none();
    
    private static final String A_VALID_DATABASE = "validdbfile.csv";
    private static final String DATABASE_INVALID_HEADER =  "invaliddatabaseheader.csv";
    private static final String DATABASE_INVALID_RECORD =  "invaliddatabaserecord.csv";
    
    private static final Trip TRIP1 = new Trip(Airport.CPH, Airport.FRA);
    private static final double FLIGHT1_PRICE = 186;
    private static final String FLIGHT1_DESCRIPTION = "IB2818";
    
    private static final Trip TRIP2 = new Trip(Airport.CPH, Airport.LHR);
    private static final double FLIGHT2_PRICE = 152;
    private static final String FLIGHT2_DESCRIPTION = "U23631";
    
    private static String HEADER_ERR_MSG = "Missed";
    private static String RECORD_ERR_MSG = "fields";
    
    private static final Trip NON_EXISTING_TRIP = new Trip(Airport.LHR, Airport.CPH);
    
    private FlightsFileDatabase db;
    
    @Test
    public void testRetrieveFromFile() throws DatabaseException {
        String validFileNamePath = getClass().getClassLoader().getResource(A_VALID_DATABASE).getPath();
        db = new FlightsFileDatabase(validFileNamePath);
        
        List<Flight> flightsTrip1 = db.getFlights(TRIP1);
        assertThat(flightsTrip1).hasSize(1);
        assertThat(flightsTrip1.get(0).getPrice()).isEqualTo(FLIGHT1_PRICE);
        assertThat(flightsTrip1.get(0).description()).isEqualTo(FLIGHT1_DESCRIPTION);
        
        List<Flight> flightsTrip2 = db.getFlights(TRIP2);
        assertThat(flightsTrip2).hasSize(1);
        assertThat(flightsTrip2.get(0).getPrice()).isEqualTo(FLIGHT2_PRICE);
        assertThat(flightsTrip2.get(0).description()).isEqualTo(FLIGHT2_DESCRIPTION);
        
        assertThat(db.getFlights(NON_EXISTING_TRIP)).isEmpty();
    }
    
    @Test
    public void testExceptionIsThrownWhenWrongHeader() throws DatabaseException {
        String invalidHeaderPath = getClass().getClassLoader().getResource(DATABASE_INVALID_HEADER).getPath();
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(HEADER_ERR_MSG);
        db = new FlightsFileDatabase(invalidHeaderPath);
    }
    
    @Test
    public void testExceptionIsThrownWhenWrongRecord() throws DatabaseException {
        String invalidRecordPath = getClass().getClassLoader().getResource(DATABASE_INVALID_RECORD).getPath();
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(RECORD_ERR_MSG);
        db = new FlightsFileDatabase(invalidRecordPath);
    }
    
    @Test
    public void testAddingAndRetrievingFlights() {
        Trip trip1 = mock(Trip.class);
        Trip trip2 = mock(Trip.class);
        
        Flight flight1 = mockFlight(trip1);
        Flight flight2 = mockFlight(trip2);
        Flight flight3 = mockFlight(trip1);
        
        db = new FlightsFileDatabase();
        
        db.addFlight(flight1);
        db.addFlight(flight2);
        db.addFlight(flight3);
        
        List<Flight> flightsTrip1 = db.getFlights(flight1.trip());
        List<Flight> flightsTrip2 = db.getFlights(flight2.trip());
        
        assertThat(flightsTrip1).hasSize(2);
        assertThat(flightsTrip2).hasSize(1);
        
        assertThat(flightsTrip1).contains(flight1);
        assertThat(flightsTrip2).contains(flight2);
        assertThat(flightsTrip1).contains(flight3);
    }
    
    @Test
    public void testGetFlightsWhenNotExisting() {
        Trip trip1 = mock(Trip.class);
        Trip trip2 = mock(Trip.class);
        
        Flight flight1 = mockFlight(trip1);
        
        db = new FlightsFileDatabase();
        
        db.addFlight(flight1);
        
        assertThat(db.getFlights(trip1)).isNotEmpty();
        assertThat(db.getFlights(trip2)).isEmpty();
    }
    
    private Flight mockFlight(Trip trip) {
        Flight flight = mock(Flight.class);
        when(flight.trip()).thenReturn(trip);
        
        return flight;
    }
    
}
