package com.lastminute.tickets;

import java.util.Arrays;
import java.util.List;

import com.lastminute.i18n.I18n;

public class ChildTicket extends TicketType {
	
    public static final double CHILD_TICKET_FACTOR = 0.67;
	
    private final int quantity;

    public ChildTicket(int quantity, double price, TicketPolicy policy) {
        this(quantity, price, Arrays.asList(policy));
    }
    
    public ChildTicket(int quantity, double price, List<TicketPolicy> policies) {
        super(I18n.CHILD_PASSENGER_DESCRIPTION, price, CHILD_TICKET_FACTOR, I18n.CHILD_PASSENGER_PERCENTAGE, policies);
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
