package com.lastminute.stages;

import com.lastminute.flights.Trip;
import com.lastminute.locations.Airport;
import com.lastminute.tickets.helpers.TicketRequest;
import com.lastminute.tickets.helpers.TicketRequest.TicketRequestBuilder;
import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.AfterStage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;

public class GivenLastminute <SELF extends GivenLastminute<?>> extends Stage<SELF> {

    @ProvidedScenarioState
    TicketRequest ticketRequest;
    
    @ProvidedScenarioState
    String dbPath;
    
    private static final String PROVIDED_DB_PATH = "provided.csv";
    
    private Airport origin;
    private Airport destination;
    private int daysTo;
    private int adults;
    private int children;
    private int infants;
    
    public GivenLastminute<?> $_as_origin_airport(Airport origin) {
        this.origin = origin;
        return self();
    }
    
    public GivenLastminute<?> $_as_destination_airport(Airport destination) {
        this.destination = destination;
        return self();
    }
    
    public GivenLastminute<?> $_days_to_departure(int daysTo) {
        this.daysTo = daysTo;
        return self();
    }
    
    public GivenLastminute<?> $_adults(int adults) {
        this.adults = adults;
        return self();
    }
    
    public GivenLastminute<?> $_children(int children) {
        this.children = children;
        return self();
    }
    
    public GivenLastminute<?> $_infants(int infants) {
        this.infants = infants;
        return self();
    }
    
    public GivenLastminute<?> an_adult() {
        return $_adults(1);
    }
    
    public GivenLastminute<?> a_child() {
        return $_children(1);
    }
    
    public GivenLastminute<?> an_infant() {
        return $_infants(1);
    }
    
    public GivenLastminute<?> provided_database() {
        dbPath = PROVIDED_DB_PATH;
        return self();
    }
    
    @AfterStage
    private void createRequest() {
        Trip trip = new Trip(origin, destination);
        TicketRequestBuilder builder = new TicketRequestBuilder(trip, daysTo);
        builder.with().adults(adults).and().children(children).and().infants(infants);
        ticketRequest = builder.build();
    }

}
