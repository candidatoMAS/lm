package com.lastminute.tickets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.lastminute.i18n.I18n;

public class AdultTicket extends TicketType {
	
    public static final double ADULT_TICKET_FACTOR = 1.0;
	
    private final int quantity;

    public AdultTicket(int quantity, double price, TicketPolicy policy) {
        this(quantity, price, policy == null ? new ArrayList<TicketPolicy>() : Arrays.asList(policy));
    }
    
    public AdultTicket(int quantity, double price, List<TicketPolicy> policies) {
        super(I18n.ADULT_PASSENGER_DESCRIPTION, price, ADULT_TICKET_FACTOR, 
                I18n.ADULT_PASSENGER_PERCENTAGE, policies == null ? new ArrayList<TicketPolicy>() : policies);
        this.quantity = quantity;
    }
	
    @Override
    public double calculateFinalPrice() {
        return super.calculateFinalPrice(quantity, originalPrice);
    }
    
    @Override
    public int passengers() {
        return quantity;
    }
	
}
