package com.lastminute;

import com.lastminute.db.DatabaseException;
import com.lastminute.flights.Trip;
import com.lastminute.locations.Airport;
import com.lastminute.printers.DefaultPrinter;
import com.lastminute.tickets.calculi.LastminuteCalculator;
import com.lastminute.tickets.helpers.TicketRequest;
import com.lastminute.tickets.helpers.TicketRequest.TicketRequestBuilder;

public class RequiredExamples {
    public static void main(String[] args) throws DatabaseException {
        String path = RequiredExamples.class.getClassLoader().getResource("examples/provided.csv").getPath();
        LastminuteCalculator calculator = new LastminuteCalculator(path);
        AMS_to_FRA_with_AN_ADULT_within_31_DAYS_TO(calculator);
        LHR_to_IST_with_2_ADULTS_and_A_CHILD_and_AN_INFANT_within_15_DAYS_TO(calculator);
        BCN_to_MAD_with_AN_ADULT_and_2_CHILDREN_within_2_DAYS_TO(calculator);
        CDG_to_FRA_with_AN_ADULT_within_1_DAY_TO(calculator);
        
    }
   
    private static void AMS_to_FRA_with_AN_ADULT_within_31_DAYS_TO(LastminuteCalculator calculator) {
        System.out.println("* 1 adult, 31 days to the departure date, flying AMS -> FRA");
        Trip trip = new Trip(Airport.AMS, Airport.FRA);
        TicketRequestBuilder builder = new TicketRequestBuilder(trip, 31).with().adults(1);
        TicketRequest request = builder.build();
        
        calculator.printAvalaible(request, DefaultPrinter.getInstance());
        System.out.println();
    }
    
    private static void LHR_to_IST_with_2_ADULTS_and_A_CHILD_and_AN_INFANT_within_15_DAYS_TO(
            LastminuteCalculator calculator) {
        System.out.println("* 2 adults, 1 child, 1 infant, 15 days to the departure date, flying LHR -> IST");
        Trip trip = new Trip(Airport.LHR, Airport.IST);
        TicketRequestBuilder builder = 
                new TicketRequestBuilder(trip, 15).with().adults(2).and().children(1).and().infants(1);
        TicketRequest request = builder.build();
        
        calculator.printAvalaible(request, DefaultPrinter.getInstance());
        System.out.println();
    }
    
    private static void BCN_to_MAD_with_AN_ADULT_and_2_CHILDREN_within_2_DAYS_TO(
            LastminuteCalculator calculator) {
        System.out.println("* 1 adult, 2 children, 2 days to the departure date, flying BCN -> MAD");
        Trip trip = new Trip(Airport.BCN, Airport.MAD);
        TicketRequestBuilder builder = new TicketRequestBuilder(trip, 2).adults(1).and().children(2);
        TicketRequest request = builder.build();
        
        calculator.printAvalaible(request, DefaultPrinter.getInstance());
        System.out.println();
    }
    
    private static void CDG_to_FRA_with_AN_ADULT_within_1_DAY_TO(LastminuteCalculator calculator) {
        System.out.println("* CDG -> FRA");
        Trip trip = new Trip(Airport.CDG, Airport.FRA);
        TicketRequestBuilder builder = new TicketRequestBuilder(trip, 1).with().adults(1);
        TicketRequest request = builder.build();
        
        calculator.printAvalaible(request, DefaultPrinter.getInstance());
        System.out.println();
    }
}
