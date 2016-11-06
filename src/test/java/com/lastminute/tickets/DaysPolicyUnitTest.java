package com.lastminute.tickets;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.lastminute.i18n.I18n;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import java.util.Arrays;
import java.util.List;

import static org.fest.assertions.Assertions.*;

@RunWith(DataProviderRunner.class)
public class DaysPolicyUnitTest {
    
    @DataProvider
    public static Object[][] daysDataProvider() {
        return new Object[][] {
            {DaysPolicy.MORE_THAN_THIRTY, 0.8, I18n.MORE_THAN_THIRTY_DAYS_PERCENTAGE},
            {DaysPolicy.SIXTEEN_AND_THIRTY, 1.0, I18n.SIXTEEN_AND_THIRTY_DAYS_PERCENTAGE},
            {DaysPolicy.THREE_AND_FIFTEEN, 1.2, I18n.THREE_AND_FIFTEEN_DAYS_PERCENTAGE},
            {DaysPolicy.LESS_THAN_THREE, 1.5, I18n.LESS_THAN_THREE_DAYS_PERCENTAGE},
            {DaysPolicy.NO_POLICY, 1.0, ""},
        };
    }
    
    @DataProvider
    public static Object[][] daysConditionProvider() {
        return new Object[][] {
            {DaysPolicy.MORE_THAN_THIRTY, Arrays.asList(31)},
            {DaysPolicy.SIXTEEN_AND_THIRTY, Arrays.asList(16, 20, 30)},
            {DaysPolicy.THREE_AND_FIFTEEN, Arrays.asList(3, 10, 15)},
            {DaysPolicy.LESS_THAN_THREE, Arrays.asList(0, 1, 2)},
            {DaysPolicy.NO_POLICY, 1.0, Arrays.asList(-1)},
        };
    }
    
    @Test
    public void testAvailableDaysPolicyNumber() { // Needed to remember updating test every new day policy
        assertThat(DaysPolicy.values()).hasSize(5);
    }
    
    @UseDataProvider("daysDataProvider")
    @Test
    public void testDaysPolicy(DaysPolicy dayPolicy, double factor, String percentage) {
        assertThat(dayPolicy.factor()).isEqualTo(factor);
        assertThat(dayPolicy.appliedPolicy()).isEqualTo(percentage);
    }
    
    @UseDataProvider("daysConditionProvider")
    @Test
    public void testRetrieveDaysPolicy(DaysPolicy dayPolicy, List<Integer> daysTo) {
        for(int dayTo : daysTo) {
            assertThat(DaysPolicy.fromDaysTo(dayTo)).isEqualTo(dayPolicy);
        }
    }
    
}
