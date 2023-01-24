package httpModule;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import resultModule.Result;
import uiModule.MainUI;
import userSelectionModule.UserSelection;

import com.google.gson.Gson;

public class HttpClient {

        UserSelection userSelection = UserSelection.getInstance();
        
        private static HttpClient instance = null;

        private HttpClient() {
        }

        public static HttpClient getInstance() {

            if (instance == null) {
                instance = new HttpClient();
            }
            return instance;
        }

        /**
        *  get - gets the data from the API
        *
        * @param urlString - the url to get the data from
         *this method takes the created url string
         *  built from the user selections and sends it to the server
         *  and
        * @return - the data from the API in result form
        */

        public Result get(String urlString) {

            try{

                System.out.println(urlString);

                URL url = new URL(urlString);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.connect();
                int responsecode = con.getResponseCode();
                // System.out.println("Response Code " + responsecode);

                if (responsecode != 200) {
                    if(responsecode == 400){
                        MainUI.getInstance().displayError("HttpResponseCode" + responsecode + " The AnalysisString is not formatted correctly in server(probably %'s");
                        throw new RuntimeException("HttpResponseCode: " + responsecode);
                    }
                    else if(responsecode == 404){
                        MainUI.getInstance().displayError("HttpResponseCode" + responsecode + " The API type was null or some API selection error");
                        throw new RuntimeException("HttpResponseCode: " + responsecode);
                    }
                    else{
                        throw new RuntimeException("HttpResponseCode: " + responsecode);
                    }
                    
                } else {
                    String inline = "";
                    Scanner sc = new Scanner(url.openStream());
                    while (sc.hasNext()) {
                        inline += sc.nextLine();
                    }

                    //RETURNING JSON DATA BACK INTO A RESULT OBJECT
                    Gson gson = new Gson();
                    Result result = gson.fromJson(inline, Result.class);

                    sc.close();
                    
                    //JSON STRING FROM SERVER IS MADE INTO RESULT OBJECT SO VIEWS ARE OK
                    return result;
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        
}
