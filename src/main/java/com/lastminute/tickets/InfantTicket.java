package com.lastminute.tickets;

import java.util.Arrays;
import java.util.List;

import com.lastminute.i18n.I18n;

public class InfantTicket extends TicketType {
	
    public static final double INFANT_TICKET_FACTOR = 1.0;
    
    private final int quantity;

    public InfantTicket(int quantity, double price, TicketPolicy policy) {
        this(quantity, price, Arrays.asList(policy));
    }
    
    public InfantTicket(int quantity, double price, List<TicketPolicy> policies) {
        super(I18n.INFANT_PASSENGER_DESCRIPTION, price, INFANT_TICKET_FACTOR, I18n.INFANT_PASSENGER_PERCENTAGE, policies);
        this.quantity = quantity;
    }
    
    @Override
    public double calculateFinalPrice() {
        return super.calculateFinalPrice(quantity, this.originalPrice);
    }
    
    @Override
    public int passengers() {
        return quantity;
    }
	
}
