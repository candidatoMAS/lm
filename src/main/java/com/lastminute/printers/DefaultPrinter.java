package com.lastminute.printers;

import java.text.DecimalFormat;
import java.util.List;

import com.lastminute.i18n.I18n;
import com.lastminute.tickets.TicketPolicy;
import com.lastminute.tickets.TicketType;
import com.lastminute.tickets.helpers.TicketResponse;

public class DefaultPrinter implements Printer {
    
    private static final DefaultPrinter _INSTANCE = new DefaultPrinter();
    
    private DefaultPrinter() {}
    
    public static DefaultPrinter getInstance() {
        return _INSTANCE;
    }
    
    @Override
    public void print(TicketResponse response) {
        String output = generateOutput(response);
		
        System.out.println(output);
    }
	
    private String generateOutput(TicketResponse response) {
        DecimalFormat doubleFormat = new DecimalFormat("#.##"); // Thread unsafe
        if (response.totalPassengers() == 1) {
            return String.format(I18n.FLIGHT_PRICE_SIMPLE_DESCRIPTION, 
                    response.flightDescription(), doubleFormat.format(response.price())); 
        }
        return String.format(I18n.FLIGHT_PRICE_COMPOSE_DESCRIPTION, 
                response.flightDescription(), 
                doubleFormat.format(response.price()), 
                generatePriceDescription(response, doubleFormat));
    }
    
    private String generatePriceDescription(TicketResponse response, DecimalFormat doubleFormat) {
        List<TicketType> tickets = response.tickets();
        
        String flightDescription = "";
        for (TicketType ticket : tickets) {
            if (ticket.passengers() == 0) {
                continue;
            }
            String ticketDescription = doubleFormat.format(ticket.originalPrice());
            boolean compose = false;
            for(TicketPolicy policy : ticket.policies()) {
                if (policy.factor() != 1.0) {
                    String policyDescription = compose ? 
                            I18n.FLIGHT_POLICY_PRICE_COMPOSE_DESCRIPTION : 
                                I18n.FLIGHT_POLICY_PRICE_SIMPLE_DESCRIPTION;
                        
                    ticketDescription = String.format(policyDescription, policy.appliedPolicy(),
                            ticketDescription);
                    compose = true;
                }
            }
            if (ticket.priceFactor() == 1.0) {
                ticketDescription = String.format(I18n.FLIGHT_TICKET_PRICE_SIMPLE_DESCRIPTION, 
                        ticketDescription);
            } else {
                compose = true;
                ticketDescription = String.format(I18n.FLIGHT_TICKET_PRICE_COMPOSE_DESCRIPTION, 
                        ticket.priceFactorDescription(), ticketDescription);
            }
            
            if (ticket.passengers() > 1) {
                String numberDescription = compose ? 
                        I18n.FLIGHT_NUMBER_PRICE_COMPOSE_DESCRIPTION : 
                            I18n.FLIGHT_NUMBER_PRICE_SIMPLE_DESCRIPTION;
                ticketDescription = String.format(numberDescription, ticket.passengers(),
                        ticketDescription);
            }
            flightDescription += (flightDescription.isEmpty() ? "" : " + ") + ticketDescription;
        }
        return flightDescription;
    }

    @Override
    public void print(List<TicketResponse> responses) {
        if (responses.isEmpty()) {
            System.out.println(I18n.NO_AVAILABLE_FLIGHTS);
        } else {
            for(TicketResponse response : responses) {
                print(response);
            }
        }
    }

}
