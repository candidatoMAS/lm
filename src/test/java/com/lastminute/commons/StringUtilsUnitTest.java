package com.lastminute.commons;

import static org.fest.assertions.Assertions.*;

import org.junit.Test;

public class StringUtilsUnitTest {
    
    private static final String NON_EMPTY_STRING = "A_STRING";
    
    @Test
    public void testNullIsNullOrEmpty() {
        assertThat(StringUtils.isNullOrEmpty(null)).isTrue();
    }
    
    @Test
    public void testEmptyIsNullOrEmpty() {
        assertThat(StringUtils.isNullOrEmpty("")).isTrue();
    }
    
    @Test
    public void testNotEmptyIsNotNullOrEmpty() {
        assertThat(StringUtils.isNullOrEmpty(NON_EMPTY_STRING)).isFalse();
    }

}
