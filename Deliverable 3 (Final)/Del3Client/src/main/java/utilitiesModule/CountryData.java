package utilitiesModule;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class CountryData {
    Country[] countries;

    public static String readFileAsString(String file) throws Exception {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    public static Country[] CountryParse(String file) throws Exception {
        String json = readFileAsString(file);
        Country[] countryArray = new ObjectMapper().readValue(json, Country[].class);
        return countryArray;
    }

    private File getFileFromResource(String fileName) throws URISyntaxException {

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {

            return new File(resource.toURI());
        }
    }

    public CountryData(){
        try {
            countries = CountryParse(String.valueOf(getFileFromResource("countryList.json")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Country[] getCountries() {
        return countries;
    }
    public Vector<String> getCountryNames(){
        List<String> temp = new ArrayList<String>();

        for(Country country: this.countries){
           temp.add(country.getCountryName());
        }
        Vector<String> vector = new Vector<String>(temp);
        return vector;
    }
    public Vector<String> getCountryStartDates(){
        List<String> temp = new ArrayList<String>();

        for(Country country: this.countries){
            temp.add(country.getStartYear());
        }
        Vector<String> vector = new Vector<String>(temp);
        return vector;
    }

    public String getCountryIso(String country){
        String iso = "";
        for(Country c : countries){
            if(c.getCountryName().equals(country)){
                
                iso = c.getIso3digit();
            }
        }
        return iso;
    }
}
