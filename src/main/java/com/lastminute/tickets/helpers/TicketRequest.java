package com.lastminute.tickets.helpers;

import com.lastminute.commons.Builder;
import com.lastminute.flights.Trip;

public class TicketRequest {
    
    private final int adults;
    private final int children;
    private final int infants;
    private final Trip trip;
    private final int daysTo;
	
    private TicketRequest(Trip trip, int daysTo, int adults, int children, int infants) {
        this.adults = adults;
        this.children = children;
        this.infants = infants;
        this.daysTo = daysTo;
        this.trip = trip;
    }
	
    public int adults() {
        return adults;
    }
	
    public int children() {
        return children;
    }
	
    public int infants() {
        return infants;
    }
	
    public int daysTo() {
        return daysTo;
    }
	
    public Trip trip() {
        return trip;
    }
	
    public static class TicketRequestBuilder implements Builder<TicketRequest> {
        private int adults = 0;
        private int children = 0;
        private int infants = 0;
		
        private final int daysTo;
        private final Trip trip;
        
        public TicketRequestBuilder and() {
            return this;
        }
        
        public TicketRequestBuilder with() {
            return this;
        }
        
        public TicketRequestBuilder(Trip trip, int daysTo) {
            if (trip == null) {
                throw new IllegalArgumentException("trip cannot be null");
            } else if (daysTo < 0) {
                throw new IllegalArgumentException("daysTo cannot be negative");
            }
            this.trip = trip;
            this.daysTo = daysTo;
	}
		
        public TicketRequestBuilder adults(int adults) {
            this.adults = adults;
            return this;
	}
		
        public TicketRequestBuilder children(int children) {
            this.children = children;
            return this;
	}
		
        public TicketRequestBuilder infants(int infants) {
            this.infants = infants;
            return this;
	}
		
        @Override
        public TicketRequest build() {
            return new TicketRequest(trip, daysTo, adults, children, infants);
	}
    }

}
