package RateLimiter;

public class App {
    public static void main(String[] args) throws Exception {
        
        RateLimiter rateLimiter = new RateLimiter();

        System.out.println(rateLimiter.rateLimit(1));
        System.out.println(rateLimiter.rateLimit(2));
        System.out.println(rateLimiter.rateLimit(1));
        System.out.println(rateLimiter.rateLimit(2));
        System.out.println(rateLimiter.rateLimit(1));
        System.out.println(rateLimiter.rateLimit(1));
        System.out.println(rateLimiter.rateLimit(1));
        Thread.sleep(3010);
        System.out.println(rateLimiter.rateLimit(1));
        

        /// Scaleup
        RateLimiter2 rateLimiter2 = new RateLimiter2();

        System.out.println(rateLimiter2.rateLimit(1));
        System.out.println(rateLimiter2.rateLimit(1));
        System.out.println(rateLimiter2.rateLimit(1));
        System.out.println(rateLimiter2.rateLimit(1));
        Thread.sleep(3010);
        System.out.println(rateLimiter2.rateLimit(1));
        Thread.sleep(3010);
        System.out.println(rateLimiter2.rateLimit(1));
        System.out.println(rateLimiter2.rateLimit(1));
        System.out.println(rateLimiter2.rateLimit(1));
        System.out.println(rateLimiter2.rateLimit(1));
        System.out.println(rateLimiter2.rateLimit(1));
        System.out.println(rateLimiter2.rateLimit(1));

        //Scaleup
        // For concurrency use ConcurrentHashMap and Use AtomicInteger,
        // and semaphores for acquiring user based locks
    }
}
