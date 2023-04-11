package Router.one;


public class App {
    public static void main(String[] args) {
         RouterImpl  routerImpl = new RouterImpl();
         String result = routerImpl.withRoute("/foo", "foo").withRoute("/bar", "bar")
         .withRoute("/too", "too").route("/bar");
         System.out.println(result);
    }
}
    
