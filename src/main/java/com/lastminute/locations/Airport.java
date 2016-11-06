package com.lastminute.locations;

import com.lastminute.commons.StringUtils;


public enum Airport {
	MAD("MAD", City.MADRID),
	BCN("BCN", City.BARCELONA),
	LHR("LHR", City.LONDON),
	CDG("CDG", City.PARIS),
	FRA("FRA", City.FRANKFURT),
	IST("IST", City.ISTANBUL),
	AMS("AMS", City.AMSTERDAM),
	FCO("FCO", City.ROME),
	CPH("CPH", City.COPENHAGEN);
	
	private final String code;
	private final City city;
	
	private Airport(String code, City city) {
		this.code = code;
		this.city = city;
	}
	
	public String code() {
		return code;
	}
	
	public City city() {
		return city;
	}
	
	public static Airport fromCode(String code) {
		if (StringUtils.isNullOrEmpty(code)) {
			throw new IllegalArgumentException("No airport with empty code");
		}
		for (Airport airport : Airport.values()) {
			if (airport.code.equals(code)) {
				return airport;
			}
		}
		throw new IllegalArgumentException(code + " is a non existing airport");
	}
	
}
