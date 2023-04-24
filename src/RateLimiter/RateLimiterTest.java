package com.atlassian.RateLimiter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RateLimiterTest {
    @Test
    public void testRateLimiting() throws InterruptedException{
        RateLimiterImpl  rateLimiter = new RateLimiterImpl();

        assertEquals(rateLimiter.rateLimit(1), true); 
        assertEquals(rateLimiter.rateLimit(2), true); 
        assertEquals(rateLimiter.rateLimit(1), true); 
        assertEquals(rateLimiter.rateLimit(2), true);
        assertEquals(rateLimiter.rateLimit(1), true); 
        assertEquals(rateLimiter.rateLimit(1), false) ;
        Thread.sleep(3010);
        assertEquals(rateLimiter.rateLimit(1), true);
        assertEquals(rateLimiter.rateLimit(2), true);  
        
    }

    @Test   //LEVEL 1
    public void testRateLimitingWithCredits() throws InterruptedException{
        RateLimiterImpl  rateLimiter = new RateLimiterImpl();

        assertEquals(rateLimiter.rateLimit(-2), false); 
        
        assertEquals(rateLimiter.rateLimit(1), true); 
        assertEquals(rateLimiter.rateLimit(2), true); 
        assertEquals(rateLimiter.rateLimit(1), true); 
        assertEquals(rateLimiter.rateLimit(2), true);
        assertEquals(rateLimiter.rateLimit(1), true); 
        assertEquals(rateLimiter.rateLimit(1), false) ;
        Thread.sleep(3010);
        assertEquals(rateLimiter.rateLimit(1), true);
        assertEquals(rateLimiter.rateLimit(2), true);
        assertEquals(rateLimiter.rateLimit(2), true);
        assertEquals(rateLimiter.rateLimit(2), true);
        assertEquals(rateLimiter.rateLimit(2), true);
        assertEquals(rateLimiter.rateLimit(2), false); 
        assertEquals(rateLimiter.rateLimit(2), false);  
        
    }
}
