package com.lastminute.flights;

import com.lastminute.locations.Airport;

public class Trip {
    
    private final Airport origin;
    private final Airport destination;
	
    public Trip(Airport origin, Airport destination) {
	this.origin = origin;
	this.destination = destination;
    }
	
    public Airport origin() {
	return origin;
    }
	
    public Airport destination() {
	return destination;
    }
	
    public String id() {
	return origin.code() + destination.code();
    }
	
    @Override
    public int hashCode() {
	return id().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	
	if (obj == null) {
	    return false;
	}
		
	if (getClass() != obj.getClass()) {
	    return false;
	}
	
	Trip other = (Trip) obj;
	if (destination != other.destination) {
	    return false; 
	}
	return (origin == other.origin);
    }
}
