package Router.three;

public class App {
    public static void main(String[] args) {
         RouterImpl  routerImpl = new RouterImpl();
         String result = routerImpl.withRoute("/foo/:id/bar/:id1", "voila").withRoute("/bar", "bar")
         .withRoute("/too/*", "too").route("/too/id");
         System.out.println(result);
    }
}
    
