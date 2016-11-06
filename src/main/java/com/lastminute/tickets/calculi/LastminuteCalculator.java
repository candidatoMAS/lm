package com.lastminute.tickets.calculi;

import java.util.List;
import java.util.ArrayList;

import com.lastminute.db.DatabaseException;
import com.lastminute.db.FlightsDatabase;
import com.lastminute.db.FlightsFileDatabase;
import com.lastminute.flights.Flight;
import com.lastminute.printers.Printer;
import com.lastminute.tickets.helpers.TicketRequest;
import com.lastminute.tickets.helpers.TicketResponse;

public class LastminuteCalculator implements WebCalculator {
	
    private final FlightsDatabase db;
	
    public LastminuteCalculator(FlightsDatabase db) throws DatabaseException {
        this.db = db;
    }
    
    public LastminuteCalculator(String fileName) throws DatabaseException {
        this(new FlightsFileDatabase(fileName));
    }
    
    @Override
    public List<TicketResponse> getAvailable(TicketRequest request) {
        List<Flight> flights = db.getFlights(request.trip());
        List<TicketResponse> responses = new ArrayList<>();
		
        for(Flight flight : flights) {
            AirlinePriceCalculator priceCalculator = new AirlinePriceCalculator(request, flight);
            PriceHandler priceHandler = priceCalculator.createHandler();
            TicketResponse ticketResponse = new TicketResponse(flight, priceHandler);
            responses.add(ticketResponse);
	}
		
        return responses;
    }
	
    @Override
    public void printAvalaible(TicketRequest request, Printer printer) {
        List<TicketResponse> responses = getAvailable(request);
		
        printer.print(responses);
    }
			
}
