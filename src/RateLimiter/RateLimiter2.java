package RateLimiter;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class RateLimiter2 {

    private Map<Integer, Customer> requestMap;
    private long TIMER = 3;
    private int THRESHOLD = 3;
    private int MAX_CREDITS = 2;

    RateLimiter2(){
        requestMap = new HashMap<>();
    }

    public boolean rateLimit(int customerId){
        long now = System.currentTimeMillis();
        Customer customer = requestMap.computeIfAbsent(customerId, v -> new Customer(now));

        if(Duration.ofMillis(now - customer.startTime).getSeconds() >= TIMER ){
            int credit = Math.min(THRESHOLD - customer.requests, MAX_CREDITS);
            customer.reset(now, credit);
        }
        if(customer.requests < THRESHOLD){
            customer.requests++;
            return true;
        } 
        else if(customer.credits > 0){
            customer.credits--;
            return true;
        }
        return false;
    }

    private class Customer{
        private int requests;
        private long startTime;
        private int credits;

        Customer(long startTime){
            this.startTime = startTime;
            this.requests = 0;
            this.credits = 0;
        }

        public void reset(long startTime, int credits){
            this.startTime = startTime;
            this.requests = 0;
            this.credits = credits;
        }
    }
}
