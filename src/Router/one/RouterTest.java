package com.atlassian.Router;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RouterTest {
    
    @Test
    public void returnRouteWithGivenPath(){
        RouterImpl  router = new RouterImpl();

        String result = router.withRoute("/foo", "foo").withRoute("/bar", "bar")
                        .route("/foo");
        assertEquals(result, "foo");
    }

    @Test   //LEVEL 1
    public void returnRouteWithGivenRegexPath(){
        RouterImpl  router = new RouterImpl();

        String result = router.withRoute("/foo/*", "foo").withRoute("/bar/*/zar", "bar")
                        .route("/bar/it/zar");
        assertEquals(result, "bar");
    }

    @Test   //LEVEL 2
    public void returnRouteWithGivenRegexPathParams(){
        RouterImpl  router = new RouterImpl();

        String result = router.withRoute("/foo/:id", "123").withRoute("/bar/*/zar", "bar")
                        .route("/foo/123");
        assertEquals(result, "123");
    }
}
