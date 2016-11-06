package com.lastminute.locations;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import static org.fest.assertions.Assertions.*;

@RunWith(DataProviderRunner.class)
public class CityUnitTest {
    
    @DataProvider
    public static Object[][] cityDataProvider() {
        return new Object[][] {
            {City.MADRID, "Madrid"},
            {City.BARCELONA, "Barcelona"},
            {City.LONDON, "London"},
            {City.PARIS, "Paris"},
            {City.FRANKFURT, "Frankfurt"},
            {City.ISTANBUL, "Istanbul"},
            {City.AMSTERDAM, "Amsterdam"},
            {City.ROME, "Rome"},
            {City.COPENHAGEN, "Copenhagen"},
        };
    }
    
    @Test
    public void testAvailableAirlinesNumber() { // Needed to remember updating test every new city
        assertThat(City.values()).hasSize(9);
    }
    
    @Test
    @UseDataProvider("cityDataProvider")
    public void testCity(City city, String description) {
        assertThat(city.description()).isEqualTo(description);
    }
}

