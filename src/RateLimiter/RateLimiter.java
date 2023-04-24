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
        Customer customer = requestMap.computeIfAbsent(customerId, v -> new Customer(customerId, now));

        if(Duration.ofMillis(now - customer.getStartTime()).getSeconds() >= TIMER ){
            //int credit = Math.min(THRESHOLD - customer.getRequests(), MAX_CREDITS);        //LEVEL 1
            customer.reset(now);
            //customer.reset(now, credit);      //LEVEL 1
        }
        if(customer.getRequests() < THRESHOLD){
            customer.updateRequests();
            return true;
        }
        /*
        else if(customer.getCredits() > 0){      //LEVEL 1
            customer.updateCredits();
            return true;
        }
        */
        return false;
    }
}

//Scaleup
// For concurrency use ConcurrentHashMap and Use AtomicInteger instead of int,
// and semaphores for acquiring user based locks
