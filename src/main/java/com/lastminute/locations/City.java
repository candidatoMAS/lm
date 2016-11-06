package com.lastminute.locations;

import com.lastminute.i18n.I18n;

public enum City {
	MADRID(I18n.MADRID),
	BARCELONA(I18n.BARCELONA),
	LONDON(I18n.LONDON),
	PARIS(I18n.PARIS),
	FRANKFURT(I18n.FRANKFURT),
	ISTANBUL(I18n.ISTANBUL),
	AMSTERDAM(I18n.AMSTERDAM),
	ROME(I18n.ROME),
	COPENHAGEN(I18n.COPENHAGEN);
	
	private final String description;
	
	private City(String description) {
		this.description = description;
	}
	
	public String description() {
		return description;
	}

}
