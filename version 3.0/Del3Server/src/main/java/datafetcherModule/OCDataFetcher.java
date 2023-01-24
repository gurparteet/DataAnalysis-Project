package datafetcherModule;

import java.util.HashMap;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import userSelectionModule.UserSelection;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

public class OCDataFetcher extends DataFetcher{

    public String generateURL(UserSelection selection) {
        String country = selection.getCountry().toLowerCase();
        String startDate = selection.getFromDate()+"-01-01";
        String endDate = selection.getToDate()+"-12-31";

        return "https://api.opencovid.ca/timeseries?stat=all&geo="+country+"&after="+startDate+"&before="+endDate+"&fill=false&version=true&pt_names=short&hr_names=short&legacy=false&fmt=json";
    }


    public HashMap<String, Double> fetchData(UserSelection selection) {
       
            //KEY VALUE PAIRS TO STORE THE YEAR AND DATA FOR THAT YEAR
            HashMap<String, Double> data = new HashMap<String,Double>();
            double value = 0;
            String urlString = generateURL(selection);
            System.out.println("OCDF URL => " + urlString);
    
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

                    // System.out.println("Print me=> ");
                    Gson gson = new Gson();
                    JsonObject object = gson.fromJson(inline, JsonObject.class);
                    String jsonData = object.get("data").toString();
                    JsonObject objectData = gson.fromJson(jsonData, JsonObject.class);
                    JsonArray jsonArray = objectData.get("cases").getAsJsonArray();
                    int sizeOfResults = jsonArray.size();
                   
                    String date;
                    for(int i=0;i<sizeOfResults;i++){

                        date = jsonArray.get(i).getAsJsonObject().get("date").getAsString();
                        value = jsonArray.get(i).getAsJsonObject().get("value").isJsonNull() ? 0 : jsonArray.get(i).getAsJsonObject().get("value").getAsDouble();
                        data.put(date,value); 
                        
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
    

