package com.lastminute.i18n;

import java.util.ResourceBundle;

public final class I18n {

    private I18n() { }

    private static ResourceBundle literals =
            ResourceBundle.getBundle("i18n");

    public static final String IBERIA = literals.getString("airline.iberia.name");
    
    public static final String BRITISH_AIRWAYS = literals.getString("airline.britishairways.name");
    
    public static final String LUFTHANSA = literals.getString("airline.lufthansa.name");
    
    public static final String RYANAIR = literals.getString("airline.ryanair.name");
    
    public static final String VUELING = literals.getString("airline.vueling.name");
    
    public static final String TURKISH_AIRLINES = literals.getString("airline.turkishairlines.name");
    
    public static final String EASYJET = literals.getString("airline.easyjet.name");
    
    public static final String NO_AVAILABLE_FLIGHTS = literals.getString("flights.none.available");
    
    public static final String MADRID = literals.getString("city.madrid.name");
    
    public static final String BARCELONA = literals.getString("city.barcelona.name");
    
    public static final String LONDON = literals.getString("city.london.name");
    
    public static final String PARIS = literals.getString("city.paris.name");
    
    public static final String FRANKFURT = literals.getString("city.frankfurt.name");
    
    public static final String ISTANBUL = literals.getString("city.istanbul.name");
    
    public static final String AMSTERDAM = literals.getString("city.amsterdam.name");
    
    public static final String ROME = literals.getString("city.rome.name");
    
    public static final String COPENHAGEN = literals.getString("city.copenhagen.name");
    
    public static final String ADULT_PASSENGER_DESCRIPTION = literals.getString("rule.passenger.adult.description");

    public static final String CHILD_PASSENGER_DESCRIPTION = literals.getString("rule.passenger.child.description");
    
    public static final String INFANT_PASSENGER_DESCRIPTION = literals.getString("rule.passenger.infant.description");
    
    public static final String ADULT_PASSENGER_PERCENTAGE = literals.getString("rule.passenger.adult.percentage");

    public static final String CHILD_PASSENGER_PERCENTAGE = literals.getString("rule.passenger.child.percentage");
    
    public static final String INFANT_PASSENGER_PERCENTAGE = literals.getString("rule.passenger.infant.percentage");
    
    public static final String MORE_THAN_THIRTY_DAYS_DESCRIPTION = literals.getString("rule.days.more-than-thirty.description");
    
    public static final String SIXTEEN_AND_THIRTY_DAYS_DESCRIPTION = literals.getString("rule.days.sixteen-and-thirty.description");
    
    public static final String THREE_AND_FIFTEEN_DAYS_DESCRIPTION = literals.getString("rule.days.three-and-fifteen.description");
    
    public static final String LESS_THAN_THREE_DAYS_DESCRIPTION = literals.getString("rule.days.less-than-three.description");
    
    public static final String MORE_THAN_THIRTY_DAYS_PERCENTAGE = literals.getString("rule.days.more-than-thirty.percentage");
    
    public static final String SIXTEEN_AND_THIRTY_DAYS_PERCENTAGE = literals.getString("rule.days.sixteen-and-thirty.percentage");
    
    public static final String THREE_AND_FIFTEEN_DAYS_PERCENTAGE = literals.getString("rule.days.three-and-fifteen.percentage");
    
    public static final String LESS_THAN_THREE_DAYS_PERCENTAGE = literals.getString("rule.days.less-than-three.percentage");
    
    public static final String FLIGHT_PRICE_SIMPLE_DESCRIPTION = literals.getString("flight.price.description.simple");
    
    public static final String FLIGHT_PRICE_COMPOSE_DESCRIPTION = literals.getString("flight.price.description.compose");
    
    public static final String FLIGHT_TICKET_PRICE_SIMPLE_DESCRIPTION = literals.getString("flight.ticket.price.description.simple");
    
    public static final String FLIGHT_TICKET_PRICE_COMPOSE_DESCRIPTION = literals.getString("flight.ticket.price.description.compose");
    
    public static final String FLIGHT_POLICY_PRICE_SIMPLE_DESCRIPTION = literals.getString("flight.policy.price.description.simple");
    
    public static final String FLIGHT_POLICY_PRICE_COMPOSE_DESCRIPTION = literals.getString("flight.policy.price.description.compose");
    
    public static final String FLIGHT_NUMBER_PRICE_SIMPLE_DESCRIPTION = literals.getString("flight.number.price.description.simple");
    
    public static final String FLIGHT_NUMBER_PRICE_COMPOSE_DESCRIPTION = literals.getString("flight.number.price.description.compose");
    
}