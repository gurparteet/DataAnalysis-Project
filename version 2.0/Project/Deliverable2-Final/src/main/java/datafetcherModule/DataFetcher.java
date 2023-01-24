package datafetcherModule;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import userSelectionModule.UserSelection;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;


public class DataFetcher {

    public DataFetcher() {

    }

    /**
     * This method is used to fetch the data from the API
     * @param selection- userSelection object that has the user selection  data
     *
     * @return Url for data fetching for requested analysis by user in for of String.
     */

        public String generateURL(UserSelection selection){
        String country = selection.getCountry().toLowerCase();
        int startDate = Integer.parseInt(selection.getFromDate());
        int endDate = Integer.parseInt(selection.getToDate());
        String analysis = selection.getAnalysis();

        return "http://api.worldbank.org/v2/country/"+country+"/indicator/"+analysis+"?date="+startDate+":"+endDate+"&format=json";
    }
    //NEXT UPDATE: ADDING MORE ANALYSIS and API's  - New classes for Adapting different API .

    /**
     * This method is used to fetch the data from the API
     * @param selection- userSelection object that has the user selection  data
     *
     * @return HashMap of data for requested analysis by user.
     */


    public HashMap<Integer,Double> fetchData(UserSelection selection){

            //KEY VALUE PAIRS TO STORE THE YEAR AND DATA FOR THAT YEAR
        HashMap<Integer, Double> data = new HashMap<Integer,Double>();
        double value;
        String urlString = generateURL(selection);
        // System.out.println(urlString);

        try {
            URL link = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) link.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                String inline = "";
                Scanner sc = new Scanner(link.openStream());
                while (sc.hasNext()) {
                    inline += sc.nextLine();
                }
                sc.close();
                JsonArray jsonArray = new JsonParser().parse(inline).getAsJsonArray();
                // int size = jsonArray.size();
                int sizeOfResults = jsonArray.get(1).getAsJsonArray().size();
                
                int year = 0;
                for(int i=0;i<sizeOfResults;i++){
                    year = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("date").getAsInt();

                    if(jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").isJsonNull()){
                       value= 0;
                    }
                    else{
                        value=jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").getAsDouble();
                    }

                    
                    data.put(year, value);
                }
            }



        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return data;
    }



}