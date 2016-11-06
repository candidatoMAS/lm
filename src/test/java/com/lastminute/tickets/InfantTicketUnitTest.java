package com.lastminute.tickets;

import org.junit.Test;

import com.lastminute.i18n.I18n;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import static org.fest.assertions.Assertions.*;

// TODO consider making common class to reduce duplicate code between subclasses of TicketType
public class InfantTicketUnitTest {
    
    private static final int A_QUANTITY = 1;
    private static final double A_PRICE = 2.0;
    private static final double INFANT_FACTOR = 1.0;
    private static final double A_POLICY_FACTOR = 0.7;
    private static final double EXPECTED_PRICE = 1.4;
    
    private TicketPolicy ticketPolicy;
    
    @Test
    public void testDescription() {
        InfantTicket infantTicket = new InfantTicket(0, 0.0, ticketPolicy);
        
        assertThat(infantTicket.policyDescription).isEqualTo(I18n.INFANT_PASSENGER_DESCRIPTION);
    }
    
    @Test
    public void testPrice() {
        InfantTicket infantTicket = new InfantTicket(0, A_PRICE, ticketPolicy);
        
        assertThat(infantTicket.originalPrice()).isEqualTo(A_PRICE);
    }
    
    @Test
    public void testFactor() {
        InfantTicket infantTicket = new InfantTicket(0, 0.0, ticketPolicy);
        
        assertThat(infantTicket.priceFactor()).isEqualTo(INFANT_FACTOR);
    }
    
    @Test
    public void testFactorDescription() {
        InfantTicket infantTicket = new InfantTicket(0, 0.0, ticketPolicy);
        
        assertThat(infantTicket.priceFactorDescription()).isEmpty();
    }
    
    @Test
    public void testPolicy() {
        ticketPolicy = mock(TicketPolicy.class);
        InfantTicket infantTicket = new InfantTicket(0, 0.0, ticketPolicy);
        
        assertThat(infantTicket.policies()).hasSize(1);
        assertThat(infantTicket.policies().get(0)).isEqualTo(ticketPolicy);
    }
    
    @Test
    public void testPolicies() {
        TicketPolicy ticketPolicy1 = mock(TicketPolicy.class);
        TicketPolicy ticketPolicy2 = mock(TicketPolicy.class);
        List<TicketPolicy> policies = Arrays.asList(ticketPolicy1, ticketPolicy2);
        
        InfantTicket infantTicket = new InfantTicket(0, 0.0, policies);
        
        assertThat(infantTicket.policies()).isEqualTo(policies);
    }
    
    @Test
    public void testPassengers() {
        InfantTicket infantTicket = new InfantTicket(A_QUANTITY, 0.0, ticketPolicy);
        
        assertThat(infantTicket.passengers()).isEqualTo(A_QUANTITY);
    }
    
    @Test
    public void testCalculateFinalPrice() {
        ticketPolicy = mock(TicketPolicy.class);
        when(ticketPolicy.factor()).thenReturn(A_POLICY_FACTOR);
        InfantTicket infantTicket = new InfantTicket(A_QUANTITY, A_PRICE, ticketPolicy);
        
        assertThat(infantTicket.calculateFinalPrice()).isEqualTo(EXPECTED_PRICE);
    }
    
}
