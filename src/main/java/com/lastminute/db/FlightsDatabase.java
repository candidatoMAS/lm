package com.lastminute.db;

import java.util.List;

import com.lastminute.flights.Flight;
import com.lastminute.flights.Trip;

public interface FlightsDatabase {
    public void addFlight(Flight flight);

    public List<Flight> getFlights(Trip trip);
}
