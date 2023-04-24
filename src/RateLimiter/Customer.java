package com.atlassian.RateLimiter;

public class Customer {
    private int requests;
    private int credits;
    private long startTime;
    public int id;

    Customer(int id, long startTime){
        this.id = id;
        this.startTime = startTime;
        this.requests = 0;
        this.credits = 0;
    }

    public void reset(long startTime, int credits){
        this.startTime = startTime;
        this.requests = 0;
        this.credits = credits;
    }

    public int getRequests(){
        return this.requests;
    }

    public long getStartTime(){
        return this.startTime;
    }

    public void updateRequests(){
        this.requests++;
    }

    public void updateCredits(){
        this.credits--;
    }

    public int getCredits(){
        return this.credits;
    }
    
}
