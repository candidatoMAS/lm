package com.lastminute.tickets.helpers;

import java.util.List;

import com.lastminute.flights.Flight;
import com.lastminute.tickets.TicketType;
import com.lastminute.tickets.calculi.PriceHandler;

public class TicketResponse {
    
    private final Flight flight;
    private final PriceHandler priceHandler;
	
    public TicketResponse(Flight flight, PriceHandler priceHandler) {
        this.flight = flight;
        this.priceHandler = priceHandler;
    }
	
    public String flightDescription() {
        return flight.description();
    }
	
    public double price() {
        return priceHandler.price();
    }
    
    public List<TicketType> tickets() {
        return priceHandler.tickets();
    }
    
    public int totalPassengers() {
        int total = 0;
        for(TicketType ticket :  tickets()) {
            total += ticket.passengers();
        }
        return total;
    }
	
}
