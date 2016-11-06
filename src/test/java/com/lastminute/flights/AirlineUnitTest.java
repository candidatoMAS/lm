package com.lastminute.flights;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import static org.fest.assertions.Assertions.*;

@RunWith(DataProviderRunner.class)
public class AirlineUnitTest {
    
    @DataProvider
    public static Object[][] airlineDataProvider() {
        return new Object[][] {
            {Airline.IBERIA, "IB", "Iberia", 10},
            {Airline.BRITISH_AIRWAYS, "BA", "British Airways", 15},
            {Airline.LUFTHANSA, "LH", "Lufthansa", 7},
            {Airline.RYANAIR, "FR", "Ryanair", 20},
            {Airline.VUELING, "VY", "Vueling", 10},
            {Airline.TURKISH_AIRLINES, "TK", "Turkish Airlines", 5},
            {Airline.EASYJET, "U2", "Easyjet", 19.9}
        };
    }
    
    @Test
    public void testAvailableAirlinesNumber() { // Needed to remember updating test every new airline
        assertThat(Airline.values()).hasSize(7);
    }
    
    @Test
    @UseDataProvider("airlineDataProvider")
    public void testAirline(Airline airline, String iataCode, String description, double infantPrice) {
        assertThat(airline.codeIATA()).isEqualTo(iataCode);
        assertThat(airline.description()).isEqualTo(description);
        assertThat(airline.infantPrice()).isEqualTo(infantPrice);
    }
}
