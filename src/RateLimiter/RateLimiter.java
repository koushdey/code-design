package RateLimiter;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class RateLimiter {

    private Map<Integer, Customer> requestMap;
    private long TIMER = 3;
    private int THRESHOLD = 3;
    //private int MAX_CREDITS = 2;      //LEVEL 1

    RateLimiter(){
        requestMap = new HashMap<>();
    }

    public boolean rateLimit(int customerId){
        long now = System.currentTimeMillis();
        Customer customer = requestMap.computeIfAbsent(customerId, v -> new Customer(now));

        if(Duration.ofMillis(now - customer.startTime).getSeconds() >= TIMER ){
            //int credit = Math.min(THRESHOLD - customer.requests, MAX_CREDITS);        //LEVEL 1
            customer.reset(now);
            //customer.reset(now, credit);      //LEVEL 1
        }
        if(customer.requests < THRESHOLD){
            customer.requests++;
            return true;
        }
        /*
        else if(customer.credits > 0){      //LEVEL 1
            customer.credits--;
            return true;
        }
        */
        return false;
    }

    private class Customer{
        private int requests;
        private long startTime;
        //private int credits;      //LEVEL 1

        Customer(long startTime){
            this.startTime = startTime;
            this.requests = 0;
            this.credits = 0;       //LEVEL 1
        }

        public void reset(long startTime){
        //public void reset(long startTime, int credits){       //LEVEL 1
            this.startTime = startTime;
            this.requests = 0;
            //this.credits = credits;       //LEVEL 1
        }
    }
}

//Scaleup
// For concurrency use ConcurrentHashMap and Use AtomicInteger instead of int,
// and semaphores for acquiring user based locks
