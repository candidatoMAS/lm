package com.lastminute.tickets;

import java.util.List;

public abstract class TicketType {
	
    final String policyDescription;
    final double originalPrice;
    final double priceFactor;
    final String priceFactorDescription;
    final List<TicketPolicy> policies;
    
    TicketType(String policyDescription, double originalPrice, double priceFactor, String priceFactorDescription, List<TicketPolicy> policies) {
        this.policyDescription = policyDescription;
        this.originalPrice = originalPrice;
        this.priceFactor = priceFactor;
        this.priceFactorDescription = priceFactorDescription;
        this.policies = policies;
    }
    
    public abstract double calculateFinalPrice();
    
    public abstract int passengers();
    
    public double originalPrice() {
        return originalPrice;
    }
    
    public double priceFactor() {
        return priceFactor;
    }
    
    public String priceFactorDescription() {
        return priceFactorDescription;
    }
    
    public List<TicketPolicy> policies() {
        return policies;
    }
    
    double calculateFinalPrice(int quantity, double price) {
        double ticketTotal = quantity * price * priceFactor;
        for(TicketPolicy policy : policies()) {
            ticketTotal *= policy.factor();
        }
	return ticketTotal;
    }
    
}
