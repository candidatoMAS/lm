package com.lastminute.flights;

public class Flight {
    
    private final String description;
    private final Trip trip;
    private final Airline airline;
    private double price;
	
    public Flight(String description, Trip trip, Airline airline, double price) {
	this.description = description;
	this.trip = trip;
	this.airline = airline;
	this.price = price;
    }
	
    public String description() {
	return description;
    }
	
    public double getPrice() {
	return price;
    }
	
    public void setPrice(double price) {
	this.price = price;
    }
	
    public String id() {
	return trip.id() + airline.description();
    }
	
    public Trip trip() {
	return trip;
    }
	
    public double infantPrice() {
	return airline.infantPrice();
    }
			
}
