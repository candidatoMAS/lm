package com.lastminute.tickets;

import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

public class TicketTypeUnitTest {
    
    private static final int A_QUANTITY = 1;
    private static final double A_PRICE = 2.0;
    
    private List<TicketPolicy> policies;
    
    @Test
    public void testPoliciesAreTakeIntoAccountWhenCalculatingFinalPrice() {
        TicketPolicy policy1 = mock(TicketPolicy.class);
        TicketPolicy policy2 = mock(TicketPolicy.class);
        policies = Arrays.asList(policy1, policy2);
        
        TicketType ticketType = mock(TicketType.class, Mockito.CALLS_REAL_METHODS);
        when(ticketType.policies()).thenReturn(policies);
        
        ticketType.calculateFinalPrice(A_QUANTITY, A_PRICE);
        
        verify(policy1, times(1)).factor();
        verify(policy2, times(1)).factor();
    }
    
}
