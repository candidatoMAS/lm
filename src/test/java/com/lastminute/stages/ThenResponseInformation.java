package com.lastminute.stages;

import java.util.List;

import org.fest.assertions.Delta;

import com.lastminute.tickets.helpers.TicketResponse;
import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;

import static org.fest.assertions.Assertions.*;

public class ThenResponseInformation <SELF extends ThenResponseInformation<?>> extends Stage<SELF> {
    
    private static final double EPSILON = 0.01;
    
    @ProvidedScenarioState
    private List<TicketResponse> responses;
    
    public ThenResponseInformation<?> one_is_flight_with_$_and_$_EURO_price(String code, double price) {
        boolean foundCode = false;
        double foundPrice = 0.0;
        
        for(TicketResponse response: responses) {
            if (response.flightDescription().equals(code)) {
                foundCode = true;
                foundPrice = response.price();
            }
        }
        
        assertThat(foundCode).isTrue();
        assertThat(foundPrice).isEqualTo(price, Delta.delta(EPSILON));
        
        return self();
    }
    
    public ThenResponseInformation<?> only_$_flight_are_found(int results) {             
        assertThat(responses).hasSize(results);
        
        return self();
    }
    
    public ThenResponseInformation<?> no_results_are_found() {
        return only_$_flight_are_found(0);
    }
}
