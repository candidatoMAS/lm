package com.lastminute.tickets;

import org.junit.Test;

import com.lastminute.i18n.I18n;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import static org.fest.assertions.Assertions.*;

public class AdultTicketUnitTest {
    
    private static final int A_QUANTITY = 1;
    private static final double A_PRICE = 2.0;
    private static final double ADULT_FACTOR = 1.0;
    private static final String EXPECTED_FACTOR_DESCRIPTION = "100%";
    private static final double A_POLICY_FACTOR = 0.7;
    private static final double EXPECTED_PRICE = 1.4;
    
    private TicketPolicy ticketPolicy;
    
    @Test
    public void testDescription() {
        AdultTicket adultTicket = new AdultTicket(0, 0.0, ticketPolicy);
        
        assertThat(adultTicket.policyDescription).isEqualTo(I18n.ADULT_PASSENGER_DESCRIPTION);
    }
    
    @Test
    public void testPrice() {
        AdultTicket adultTicket = new AdultTicket(0, A_PRICE, ticketPolicy);
        
        assertThat(adultTicket.originalPrice()).isEqualTo(A_PRICE);
    }
    
    @Test
    public void testFactor() {
        AdultTicket adultTicket = new AdultTicket(0, 0.0, ticketPolicy);
        
        assertThat(adultTicket.priceFactor()).isEqualTo(ADULT_FACTOR);
    }
    
    @Test
    public void testFactorDescription() {
        AdultTicket adultTicket = new AdultTicket(0, 0.0, ticketPolicy);
        
        assertThat(adultTicket.priceFactorDescription()).isEqualTo(EXPECTED_FACTOR_DESCRIPTION);
    }
    
    @Test
    public void testPolicy() {
        ticketPolicy = mock(TicketPolicy.class);
        AdultTicket adultTicket = new AdultTicket(0, 0.0, ticketPolicy);
        
        assertThat(adultTicket.policies()).hasSize(1);
        assertThat(adultTicket.policies().get(0)).isEqualTo(ticketPolicy);
    }
    
    @Test
    public void testPolicies() {
        TicketPolicy ticketPolicy1 = mock(TicketPolicy.class);
        TicketPolicy ticketPolicy2 = mock(TicketPolicy.class);
        List<TicketPolicy> policies = Arrays.asList(ticketPolicy1, ticketPolicy2);
        
        AdultTicket adultTicket = new AdultTicket(0, 0.0, policies);
        
        assertThat(adultTicket.policies()).isEqualTo(policies);
    }
    
    @Test
    public void testPassengers() {
        AdultTicket adultTicket = new AdultTicket(A_QUANTITY, 0.0, ticketPolicy);
        
        assertThat(adultTicket.passengers()).isEqualTo(A_QUANTITY);
    }
    
    @Test
    public void testCalculateFinalPrice() {
        ticketPolicy = mock(TicketPolicy.class);
        when(ticketPolicy.factor()).thenReturn(A_POLICY_FACTOR);
        AdultTicket adultTicket = new AdultTicket(A_QUANTITY, A_PRICE, ticketPolicy);
        
        assertThat(adultTicket.calculateFinalPrice()).isEqualTo(EXPECTED_PRICE);
    }
    
}
