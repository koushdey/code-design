package Router.one;

import java.util.HashMap;
import java.util.Map;

public class RouterImpl implements Router {

    private Map<String, String> routeHandler;

    RouterImpl(){
        routeHandler = new HashMap<>();
    }

    @Override
    public RouterImpl withRoute(String path, String result) {
        this.routeHandler.put(path, result);
        return this;
    }

    @Override
    public String route(String path) {
        if(!this.routeHandler.containsKey(path))
            throw new RuntimeException("No result found");
        return this.routeHandler.get(path);
    }
    
}
