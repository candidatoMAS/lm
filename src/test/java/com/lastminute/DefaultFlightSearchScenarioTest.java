package com.lastminute;

import org.junit.Test;

import com.lastminute.locations.Airport;
import com.lastminute.stages.GivenLastminute;
import com.lastminute.stages.ThenResponseInformation;
import com.lastminute.stages.WhenDatabaseSearch;
import com.tngtech.jgiven.junit.ScenarioTest;

public class DefaultFlightSearchScenarioTest 
    extends ScenarioTest<GivenLastminute<?>, WhenDatabaseSearch<?>, ThenResponseInformation<?>> {
    
    private static String TK2372 = "TK2372";
    private static double TK2372_PRICE = 157.6;
    
    private static String TK2659 = "TK2659";
    private static double TK2659_PRICE = 198.4;
    
    private static String LH5909 = "LH5909";
    private static double LH5909_PRICE = 90.4;
    
    private static String TK8891 = "TK8891";
    private static double TK8891_PRICE = 806.0;
    
    private static String LH1085 = "LH1085";
    private static double LH1085_PRICE = 481.19;
    
    private static String IB2171 = "IB2171";
    private static double IB2171_PRICE = 909.09;
    
    private static String LH5496 = "LH5496";
    private static double LH5496_PRICE = 1028.43;
    
    @Test
    public void searching_finds_result_for_first_example() {
        given().provided_database()
            .and().$_as_origin_airport(Airport.AMS)
            .and().$_as_destination_airport(Airport.FRA)
            .and().$_days_to_departure(31)
            .and().an_adult();
        
        when().search_for_tickets();
        
        then().only_$_flight_are_found(3)
            .and().one_is_flight_with_$_and_$_EURO_price(TK2372, TK2372_PRICE)
            .and().one_is_flight_with_$_and_$_EURO_price(TK2659, TK2659_PRICE)
            .and().one_is_flight_with_$_and_$_EURO_price(LH5909, LH5909_PRICE);
    }
    
    @Test
    public void searching_finds_result_for_second_example() {
        given().provided_database()
            .and().$_as_origin_airport(Airport.LHR)
            .and().$_as_destination_airport(Airport.IST)
            .and().$_days_to_departure(15)
            .and().$_adults(2)
            .and().a_child()
            .and().an_infant();
        
        when().search_for_tickets();
        
        then().only_$_flight_are_found(2)
            .and().one_is_flight_with_$_and_$_EURO_price(TK8891, TK8891_PRICE)
            .and().one_is_flight_with_$_and_$_EURO_price(LH1085, LH1085_PRICE);
    }
    
    @Test
    public void searching_finds_result_for_third_example() {
        given().provided_database()
            .and().$_as_origin_airport(Airport.BCN)
            .and().$_as_destination_airport(Airport.MAD)
            .and().$_days_to_departure(2)
            .and().an_adult()
            .and().$_children(2);
        
        when().search_for_tickets();
        
        then().only_$_flight_are_found(2)
            .and().one_is_flight_with_$_and_$_EURO_price(IB2171, IB2171_PRICE)
            .and().one_is_flight_with_$_and_$_EURO_price(LH5496, LH5496_PRICE);
    }
    
    @Test
    public void searching_finds_no_results_for_fourth_example() {
        given().provided_database()
            .and().$_as_origin_airport(Airport.CDG)
            .and().$_as_destination_airport(Airport.FRA)
            .and().$_days_to_departure(1);
        
        when().search_for_tickets();
        
        then().no_results_are_found();
    }


}
