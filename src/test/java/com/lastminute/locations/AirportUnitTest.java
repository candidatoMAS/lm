package com.lastminute.locations;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import static org.fest.assertions.Assertions.*;

@RunWith(DataProviderRunner.class)
public class AirportUnitTest {
    
    @DataProvider
    public static Object[][] airportDataProvider() {
        return new Object[][] {
            {Airport.MAD, "MAD", City.MADRID},
            {Airport.BCN, "BCN", City.BARCELONA},
            {Airport.LHR, "LHR", City.LONDON},
            {Airport.CDG, "CDG", City.PARIS},
            {Airport.FRA, "FRA", City.FRANKFURT},
            {Airport.IST, "IST", City.ISTANBUL},
            {Airport.AMS, "AMS", City.AMSTERDAM},
            {Airport.FCO, "FCO", City.ROME},
            {Airport.CPH, "CPH", City.COPENHAGEN}
        };
    }
    
    @Test
    public void testAvailableAirportsNumber() { // Needed to remember updating test every new airport
        assertThat(Airport.values()).hasSize(9);
    }
    
    @Test
    @UseDataProvider("airportDataProvider")
    public void testAirport(Airport airport, String code, City city) {
        assertThat(airport.code()).isEqualTo(code);
        assertThat(airport.city()).isEqualTo(city);
    }
}
