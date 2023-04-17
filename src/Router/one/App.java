package Router.one;


public class App {
    public static void main(String[] args) {
         RouterImpl  routerImpl = new RouterImpl();
         String result = routerImpl.withRoute("/foo", "foo").withRoute("/bar", "bar")
         .withRoute("/too", "too").route("/bar");
        
         /* LEVEL 1
         String result = routerImpl.withRoute("/foo/*/bar/*", "yahoo").withRoute("/bar", "bar")
         .withRoute("/too", "too").route("/foo/t/bar/p");
         */
        
         /* LEVEL 2
         String result = routerImpl.withRoute("/foo/:id/bar/:id1", "voila").withRoute("/bar", "bar")
         .withRoute("/too/*", "too").route("/too/id");
         */
         System.out.println(result);
    }
}
    
