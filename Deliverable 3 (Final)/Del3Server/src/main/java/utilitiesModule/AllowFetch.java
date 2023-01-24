package utilitiesModule;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Vector;

public class AllowFetch {

    private Boolean isAllowed;

    public String readFileAsString(String file) throws Exception {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    public Boolean CountryInFile(String file, String country) throws Exception{
        String json = readFileAsString(file);
        Country [] countryArray = new ObjectMapper().readValue(json, Country[].class);
        Vector<String> countryNames = new Vector<String>();
        Boolean value = false;
        for(Country c : countryArray){
            countryNames.add(c.getCountryName());
        }
        if(countryNames.contains(country)){
            value = true;
        }
        return value;
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

    public AllowFetch(String country){
        try{
            isAllowed = CountryInFile(String.valueOf(getFileFromResource("CountryExclusionList.json")), country);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Boolean isAllowed(){
        return this.isAllowed;
    }

}
