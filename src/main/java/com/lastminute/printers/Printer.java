package com.lastminute.printers;

import java.util.List;

import com.lastminute.tickets.helpers.TicketResponse;

public interface Printer {

    public void print(TicketResponse response);
	
    public void print(List<TicketResponse> responses);
}
