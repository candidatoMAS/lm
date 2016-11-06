package com.lastminute.tickets;

import com.lastminute.i18n.I18n;


public enum DaysPolicy implements TicketPolicy {
    MORE_THAN_THIRTY(0.8, I18n.MORE_THAN_THIRTY_DAYS_PERCENTAGE) {
        boolean condition(int daysTo) {
            return daysTo >= 31;
        }
    },
    SIXTEEN_AND_THIRTY(1.0, I18n.SIXTEEN_AND_THIRTY_DAYS_PERCENTAGE) {
        boolean condition(int daysTo) {
            return daysTo >= 16 && daysTo <=30;
        }
    },
    THREE_AND_FIFTEEN(1.2, I18n.THREE_AND_FIFTEEN_DAYS_PERCENTAGE) {
        boolean condition(int daysTo) {
            return daysTo >= 3 && daysTo <=15;
        }
    },
    LESS_THAN_THREE(1.5, I18n.LESS_THAN_THREE_DAYS_PERCENTAGE) {
        boolean condition(int daysTo) {
            return daysTo < 3 && daysTo >= 0;
        }
    },
    NO_POLICY(1.0, "" ) {
        boolean condition(int daysTo) {
            return false;
        }
    };
	
    private final double factor;
    private final String percentage;
	
    private DaysPolicy(double factor, String percentage) {
        this.factor = factor;
        this.percentage = percentage;
    }
    
    public static DaysPolicy fromDaysTo(int daysTo) {
        for (DaysPolicy policy : values()) {
            if (policy.condition(daysTo)) {
                return policy;
            }
        }
        return NO_POLICY;
    }
	
    public String appliedPolicy() {
        return percentage;
    }
	
    public double factor() {
        return factor;
    }
    
    abstract boolean condition(int daysTo);
	
}
