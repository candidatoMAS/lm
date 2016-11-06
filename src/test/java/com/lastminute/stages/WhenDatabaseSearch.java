package com.lastminute.stages;

import java.util.List;

import com.lastminute.db.DatabaseException;
import com.lastminute.db.FlightsDatabase;
import com.lastminute.db.FlightsFileDatabase;
import com.lastminute.tickets.calculi.LastminuteCalculator;
import com.lastminute.tickets.helpers.TicketRequest;
import com.lastminute.tickets.helpers.TicketResponse;
import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.BeforeStage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;

public class WhenDatabaseSearch <SELF extends WhenDatabaseSearch<?>> extends Stage<SELF> {
    
    @ExpectedScenarioState
    TicketRequest ticketRequest;
    
    @ExpectedScenarioState
    String dbPath;
    
    private LastminuteCalculator calculator;

    @ProvidedScenarioState
    private List<TicketResponse> responses;
    
    @BeforeStage
    public void loadDb() throws DatabaseException {
        String path = getClass().getClassLoader().getResource(dbPath).getPath();
        FlightsDatabase db = new FlightsFileDatabase(path);
        calculator = new LastminuteCalculator(db);
    }
    
    public WhenDatabaseSearch<?> search_for_tickets() {
        responses = calculator.getAvailable(ticketRequest);
        return self();
    }
    
}
