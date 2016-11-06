package com.lastminute.tickets.calculi;

import java.util.List;

import com.lastminute.printers.Printer;
import com.lastminute.tickets.helpers.TicketRequest;
import com.lastminute.tickets.helpers.TicketResponse;

public interface WebCalculator {

    public List<TicketResponse> getAvailable(TicketRequest request);
	
    public void printAvalaible(TicketRequest request, Printer printer);
			
}
