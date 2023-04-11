package Router.three;

public interface Router {
    RouterImpl withRoute(String path, String result);
    String route(String path);
}
