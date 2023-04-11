package Router.two;

public class App {
    public static void main(String[] args) {
         RouterImpl  routerImpl = new RouterImpl();
         String result = routerImpl.withRoute("/foo/*/bar/*", "yahoo").withRoute("/bar", "bar")
         .withRoute("/too", "too").route("/foo/t/bar/p");
         System.out.println(result);
    }
}
    
