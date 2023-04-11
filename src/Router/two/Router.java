package Router.two;

public interface Router {
    RouterImpl withRoute(String path, String result);
    String route(String path);
}
