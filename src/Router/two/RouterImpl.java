package Router.two;

import java.util.*;
import java.util.regex.Pattern;

public class RouterImpl implements Router {

    private Map<String, String> routeHandler;
    private List<Map.Entry<Pattern, String>> patternHandler;

    RouterImpl(){
        routeHandler = new HashMap<>();
        patternHandler = new ArrayList<>();
    }

    @Override
    public RouterImpl withRoute(String path, String result) {
        if(!path.contains("*")){
            this.routeHandler.put(path, result);
        }
        else {
            String[] tokens = path.split("\\*",-1);
            int len = tokens.length;
            StringBuilder regex = new StringBuilder();
            for(String token : tokens){
                regex.append(token);
                if((--len > 0))
                    regex.append(".*");
            }
            Pattern pattern = Pattern.compile(regex.toString());
            this.patternHandler.add(Map.entry(pattern, result));
        }
        return this;
    }

    @Override
    public String route(String path) {
        if(!this.routeHandler.containsKey(path)) {
            return patternHandler.stream()
            .filter(entry -> entry.getKey().matcher(path).matches())
            .findFirst().get().getValue();
        }
            //throw new RuntimeException("No result found");
        return this.routeHandler.get(path);
    }
    
}
