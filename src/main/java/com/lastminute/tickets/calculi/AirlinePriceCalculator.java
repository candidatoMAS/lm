package com.lastminute.tickets.calculi;

import com.lastminute.flights.Flight;
import com.lastminute.tickets.AdultTicket;
import com.lastminute.tickets.ChildTicket;
import com.lastminute.tickets.DaysPolicy;
import com.lastminute.tickets.InfantTicket;
import com.lastminute.tickets.TicketPolicy;
import com.lastminute.tickets.TicketType;
import com.lastminute.tickets.helpers.TicketRequest;

public class AirlinePriceCalculator {
    
    private final TicketRequest ticketRequest;
    private final Flight flight;
    private final TicketPolicy policy;
	
    public AirlinePriceCalculator(TicketRequest ticketRequest, Flight flight) {
        this.ticketRequest = ticketRequest;
        this.flight = flight;
        this.policy = DaysPolicy.fromDaysTo(ticketRequest.daysTo());
    }
	
    public PriceHandler createHandler() {
        TicketType adultTicket = new AdultTicket(ticketRequest.adults(), flight.getPrice(), policy);
        TicketType childTicket = new ChildTicket(ticketRequest.children(), flight.getPrice(), policy);
        TicketType infantType = new InfantTicket(ticketRequest.infants(), flight.infantPrice(), DaysPolicy.NO_POLICY);
        
        PriceHandler handler = new PriceHandler(adultTicket, childTicket, infantType);
        
        return handler;
    }
	
}
