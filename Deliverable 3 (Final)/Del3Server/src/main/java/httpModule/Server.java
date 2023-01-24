package httpModule;

import analysisModule.Analysis;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import factoryModule.AnalysisFactory;
import resultModule.Result;
import userSelectionModule.UserSelection;
import utilitiesModule.AnalysisNameReformatorServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class Server {
        
        public static void main(String[] args) throws IOException {
            // TODO Auto-generated method stub
            int port = 8000;
            HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
            System.out.println("server started at " + port);
            server.createContext("/WBAnalysis", new WBServerHandler());
            server.createContext("/OCAnalysis", new OCServerHandler());
            server.setExecutor(null); // creates a default executor
            server.start();

        }
        /*
         *I used the same naming conventions for the apis as the ones in the example
         * the World Bank api handler sends a string json from the result object to the client
         * If there are any errors about the selection it probably has to do with the url string i built
         * in the client, the params are: country, fromdate, todate, analysis, and the api is before params
         */
        static class WBServerHandler implements HttpHandler{
            private AnalysisNameReformatorServer aNR = new AnalysisNameReformatorServer();
            public void handle(HttpExchange exchange) throws IOException {
                
                Map<String, String> params = queryToMap(exchange.getRequestURI().getQuery());
                String param1 = params.get("p1");
                String param2 = params.get("p2");
                String param3 = params.get("p3");
                String param4 = params.get("p4");
                UserSelection selection = UserSelection.getInstance();
                
                selection.setAll(param1, param2, param3, aNR.reformat(param4), "WBAnalysis");
                
                // System.out.println(selection.getAnalysis());

                AnalysisFactory aFactory = new AnalysisFactory();
                Analysis analysis = aFactory.generateAnalysis(selection.getAnalysis());
                Result result = analysis.doAnalysis(selection);
                Gson gson = new Gson();
                String response = gson.toJson(result);
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }

            private Map<String,String> queryToMap(String query) {
                if (query == null) {
                    return null;
                }
                Map<String, String> result = new HashMap<>();
                for (String param : query.split("&")) {
                    String[] entry = param.split("=");
                    if (entry.length > 1) {
                        result.put(entry[0], entry[1]);
                    } else {
                        result.put(entry[0], "");
                    }
                }
                return result;
            }
        }
        /*
         *So here i left the analysis blank. We need to implement the adapter for the api type
         */
        static class OCServerHandler implements HttpHandler{
            
            public void handle(HttpExchange exchange) throws IOException {
                Map<String, String> params = queryToMap(exchange.getRequestURI().getQuery());
                String param1 = params.get("p1");
                String param2 = params.get("p2");
                String param3 = params.get("p3");
                UserSelection selection = UserSelection.getInstance();
                selection.setAll(param1, param2, param3,"Covid Cases", "OCAnalysis");
               AnalysisFactory aFactory = new AnalysisFactory();
               Analysis analysis = aFactory.generateAnalysis(selection.getAnalysis());
               Result result = analysis.doAnalysis(selection);

               Gson gson = new Gson();
               String response = gson.toJson(result);
               exchange.sendResponseHeaders(200, response.length());
               OutputStream os = exchange.getResponseBody();
               os.write(response.getBytes());
               os.close();
            }

            public Map<String, String> queryToMap(String query) {
                if (query == null) {
                    return null;
                }
                Map<String, String> result = new HashMap<>();
                for (String param : query.split("&")) {
                    String[] entry = param.split("=");
                    if (entry.length > 1) {
                        result.put(entry[0], entry[1]);
                    } else {
                        result.put(entry[0], "");
                    }
                }
                return result;
            }
        }

}
