package RateLimiter;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class RateLimiter {

    private Map<Integer, Customer> requestMap;
    private long TIMER = 3;
    private int THRESHOLD = 3;

    RateLimiter(){
        requestMap = new HashMap<>();
    }

    public boolean rateLimit(int customerId){
        long now = System.currentTimeMillis();
        Customer customer = requestMap.computeIfAbsent(customerId, v -> new Customer(now));

        if(Duration.ofMillis(now - customer.startTime).getSeconds() >= TIMER ){
            customer.reset(now);
        }
        if(customer.requests < THRESHOLD){
            customer.requests++;
            return true;
        }
        return false;
    }

    private class Customer{
        private int requests;
        private long startTime;

        Customer(long startTime){
            this.startTime = startTime;
            this.requests = 0;
        }

        public void reset(long startTime){
            this.startTime = startTime;
            this.requests = 0;
        }
    }
}
