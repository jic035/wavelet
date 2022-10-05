import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler1 implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    ArrayList<String> strList = new ArrayList<String>();
    String str = "";

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return str;
        } 
        else if (url.getPath().equals("/add")) {
            String[] parameters = url.getQuery().split("=");
            
            if (parameters[0].equals("s")) {
                strList.add(parameters[1]);
                return String.format("Successfully added: " + parameters[1]);
            }
        }
        else if (url.getPath().equals("/search")){
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/search")) {
                String[] searchFor = url.getQuery().split("=");

                if (searchFor[0].equals("s")) {
                    ArrayList<String> toPrint = new ArrayList<String>();
                    for (String element : strList) {
                        if (element.contains(searchFor[1])) {
                            toPrint.add(element);
                        }
                    }
                    return String.format("" + toPrint);
                }
            }
        }
        return "404 Not Found!";
    }
}