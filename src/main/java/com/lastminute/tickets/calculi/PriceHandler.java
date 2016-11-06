package com.lastminute.tickets.calculi;

import java.util.Arrays;
import java.util.List;

import com.lastminute.tickets.TicketType;

public class PriceHandler {
    
    private List<TicketType> tickets;
    
    public PriceHandler(TicketType... tickets) {
        this.tickets = Arrays.asList(tickets);
    }
    
    public double price() {
        double total = 0.0;
        
        for (TicketType ticket : tickets) {
            total += ticket.calculateFinalPrice();
        }
        
        return total;
    }
    
    public List<TicketType> tickets() {
        return tickets;
    }
	
}
