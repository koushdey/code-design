package Router.one;

import java.util.*;
//import java.util.regex.Pattern;       //LEVEL 1 & 2

public class RouterImpl implements Router {

    private Map<String, String> routeHandler;
    //private List<Map.Entry<Pattern, String>> patternHandler;      //LEVEL 1 & 2

    RouterImpl(){
        routeHandler = new HashMap<>();
        //patternHandler = new ArrayList<>();       //LEVEL 1 & 2
    }

    @Override
    public RouterImpl withRoute(String path, String result) {
        //if(!path.contains("*")){      //LEVEL 1
        //if(!(path.contains("*") || path.contains(":"))){      //LEVEL 2
            this.routeHandler.put(path, result);
        //}
        /*
        else {          //LEVEL 1 & 2
            String[] tokens = path.split("\\*",-1);  // "\\*" is regex, -1 for handling * at the end of pattern
                                                     // ":[^/]*" for LEVEL 2
                                                     // matches string after colon till forward slash.
            int len = tokens.length;
            StringBuilder regex = new StringBuilder();
            for(String token : tokens){
                regex.append(token);
                if((--len > 0))
                    regex.append(".*");     //Regex: \/foo\/.*\/bar\/.*
                                            //LEVEL 2 Regex: \/foo\/[^/]+\/bar\/[^/]+
                                            // since lookup will not have colon params
            }
            Pattern pattern = Pattern.compile(regex.toString());
            this.patternHandler.add(Map.entry(pattern, result));
        }
        */
        return this;
    }

    @Override
    public String route(String path) {
        if(!this.routeHandler.containsKey(path)) {
            throw new RuntimeException("No result found");
            /*
            return patternHandler.stream()      //LEVEL 1 & 2
            .filter(entry -> entry.getKey().matcher(path).matches())
            .findFirst().get().getValue();
            */
        }
        return this.routeHandler.get(path);
    }
    
}
