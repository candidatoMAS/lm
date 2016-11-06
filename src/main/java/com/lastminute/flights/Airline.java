package com.lastminute.flights;

import com.lastminute.commons.StringUtils;
import com.lastminute.i18n.I18n;

public enum Airline {
    IBERIA("IB", I18n.IBERIA, 10),
    BRITISH_AIRWAYS("BA", I18n.BRITISH_AIRWAYS, 15),
    LUFTHANSA("LH", I18n.LUFTHANSA, 7),
    RYANAIR("FR", I18n.RYANAIR, 20),
    VUELING("VY", I18n.VUELING, 10),
    TURKISH_AIRLINES("TK", I18n.TURKISH_AIRLINES, 5),
    EASYJET("U2", I18n.EASYJET, 19.9);
	
    private final String codeIATA;
    private final String description;
    private final double infantPrice;
	
    private Airline(String codeIATA, String description, double infantPrice) {
        this.codeIATA = codeIATA;
        this.description = description;
        this.infantPrice = infantPrice;
    }
	
    public String codeIATA() {
	return codeIATA;
    }
	
    public String description() {
	return description;
    }
	
    public double infantPrice() {
	return infantPrice;
    }
	
    public static Airline fromFlightCode(String flightDescription) {
	if (StringUtils.isNullOrEmpty(flightDescription)) {
	    throw new IllegalArgumentException("No airline associated to empty description");
	}
	
	for(Airline airline : values()) {
	    if (flightDescription.startsWith(airline.codeIATA)) {
	        return airline;
	    }
	}
	throw new IllegalArgumentException("No airline associated with " + flightDescription);
    }

}
