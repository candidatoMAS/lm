package com.lastminute.db;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import com.lastminute.flights.Airline;
import com.lastminute.flights.Flight;
import com.lastminute.flights.Trip;
import com.lastminute.locations.Airport;

public class FlightsFileDatabase implements FlightsDatabase{
    
    private enum Header {
        ORIGIN("origin", 0),
        DESTINATION("destination", 1),
        FLIGHT_CODE("airline", 2),
        FLIGHT_PRICE("base price (€)", 3);
        
        final String name;
        final int position;
        public static final String SEPARATOR = ",";
        
        private Header(String name, int position) {
            this.name = name;
            this.position = position;
        }
        
        private static int fields() {
            return values().length;
        }
    }
	
    private Map<Trip, List<Flight>> flights = new HashMap<>();
    private List<Flight> EMPTY_LIST = new ArrayList<>();

    public FlightsFileDatabase() {}
    
    public FlightsFileDatabase(String fileName) throws DatabaseException {
        try{
            loadDb(fileName);
        } catch(IOException ioe) {
            throw new DatabaseException(ioe);
        }
    }
    
    private void loadDb(String fileName) throws IOException {
	try(FileReader fr = new FileReader(fileName);
	        BufferedReader br = new BufferedReader(fr);) {
			
	    checkHeader(br.readLine());
	    String line = null;
	    while ((line = br.readLine()) != null) {
		addFlight(line.split(Header.SEPARATOR));
	    }
			
	}
    }
    
    private void checkHeader(String headerLine) {
        String[] headers = headerLine.split(Header.SEPARATOR);
        
        if (!Header.ORIGIN.name.equalsIgnoreCase(headers[Header.ORIGIN.position])) {
            throw new IllegalArgumentException("Missed " + Header.ORIGIN.name);
        }
        
        if (!Header.DESTINATION.name.equalsIgnoreCase(headers[Header.DESTINATION.position])) {
            throw new IllegalArgumentException("Missed " + Header.DESTINATION.name);
        }
        
        if (!Header.FLIGHT_CODE.name.equalsIgnoreCase(headers[Header.FLIGHT_CODE.position])) {
            throw new IllegalArgumentException("Missed " + Header.FLIGHT_CODE.name);
        }
        
        if (!Header.FLIGHT_PRICE.name.equalsIgnoreCase(headers[Header.FLIGHT_PRICE.position])) {
            throw new IllegalArgumentException("Missed " + Header.FLIGHT_PRICE.name);
        }
    }
    
    private void checkRecord(String[] fields) {
        if (fields.length != Header.fields()) {
            throw new IllegalArgumentException("Wrong fields number");
        }
    }
	
    private void addFlight(String[] fields) {
        checkRecord(fields);
        
        Airport origin = Airport.fromCode(fields[Header.ORIGIN.position]);
        Airport destination = Airport.fromCode(fields[Header.DESTINATION.position]);
	Trip trip = new Trip(origin, destination);
		
	String flightDescription = fields[Header.FLIGHT_CODE.position];
	Airline airline = Airline.fromFlightCode(flightDescription);
	double price = Double.parseDouble(fields[Header.FLIGHT_PRICE.position]);
	Flight flight = new Flight(flightDescription, trip, airline, price);
	addFlight(flight);
    }

    @Override
    public void addFlight(Flight flight) {
	Trip trip = flight.trip();
	List<Flight> trips = flights.containsKey(trip) ? flights.get(trip) : new ArrayList<Flight>();
		
	trips.add(flight);
		
	flights.put(trip, trips);
    }
	
    @Override
    public List<Flight> getFlights(Trip trip) {
	return flights.containsKey(trip) ? flights.get(trip) : EMPTY_LIST;
    }

}
