package com.lastminute.tickets;

import org.junit.Test;

import com.lastminute.i18n.I18n;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import static org.fest.assertions.Assertions.*;

//TODO consider making common class to reduce duplicate code between subclasses of TicketType
public class ChildTicketUnitTest {
    
    private static final int A_QUANTITY = 1;
    private static final double A_PRICE = 2.0;
    private static final double CHILD_FACTOR = 0.67;
    private static final String EXPECTED_FACTOR_DESCRIPTION = "67%";
    private static final double A_POLICY_FACTOR = 0.7;
    private static final double EXPECTED_PRICE = 0.938;
    
    private TicketPolicy ticketPolicy;
    
    @Test
    public void testDescription() {
        ChildTicket childTicket = new ChildTicket(0, 0.0, ticketPolicy);
        
        assertThat(childTicket.policyDescription).isEqualTo(I18n.CHILD_PASSENGER_DESCRIPTION);
    }
    
    @Test
    public void testPrice() {
        ChildTicket childTicket = new ChildTicket(0, A_PRICE, ticketPolicy);
        
        assertThat(childTicket.originalPrice()).isEqualTo(A_PRICE);
    }
    
    @Test
    public void testFactor() {
        ChildTicket childTicket = new ChildTicket(0, 0.0, ticketPolicy);
        
        assertThat(childTicket.priceFactor()).isEqualTo(CHILD_FACTOR);
    }
    
    @Test
    public void testFactorDescription() {
        ChildTicket childTicket = new ChildTicket(0, 0.0, ticketPolicy);
        
        assertThat(childTicket.priceFactorDescription()).isEqualTo(EXPECTED_FACTOR_DESCRIPTION);
    }
    
    @Test
    public void testPolicy() {
        ticketPolicy = mock(TicketPolicy.class);
        ChildTicket childTicket = new ChildTicket(0, 0.0, ticketPolicy);
        
        assertThat(childTicket.policies()).hasSize(1);
        assertThat(childTicket.policies().get(0)).isEqualTo(ticketPolicy);
    }
    
    @Test
    public void testPolicies() {
        TicketPolicy ticketPolicy1 = mock(TicketPolicy.class);
        TicketPolicy ticketPolicy2 = mock(TicketPolicy.class);
        List<TicketPolicy> policies = Arrays.asList(ticketPolicy1, ticketPolicy2);
        
        ChildTicket childTicket = new ChildTicket(0, 0.0, policies);
        
        assertThat(childTicket.policies()).isEqualTo(policies);
    }
    
    @Test
    public void testPassengers() {
        ChildTicket childTicket = new ChildTicket(A_QUANTITY, 0.0, ticketPolicy);
        
        assertThat(childTicket.passengers()).isEqualTo(A_QUANTITY);
    }
    
    @Test
    public void testCalculateFinalPrice() {
        ticketPolicy = mock(TicketPolicy.class);
        when(ticketPolicy.factor()).thenReturn(A_POLICY_FACTOR);
        ChildTicket childTicket = new ChildTicket(A_QUANTITY, A_PRICE, ticketPolicy);
        
        assertThat(childTicket.calculateFinalPrice()).isEqualTo(EXPECTED_PRICE);
    }
    
}
