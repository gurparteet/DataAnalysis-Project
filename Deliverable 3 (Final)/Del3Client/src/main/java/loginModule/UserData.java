package loginModule;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.lang.ClassLoader;

public class UserData {
    HashMap<String, String> userData = new HashMap<String, String>();


    public static String readFileAsString(String file) throws Exception {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    public static User[] UserParse(String file) throws Exception {
        String json = readFileAsString(file);
        User[] userArray = new ObjectMapper().readValue(json, User[].class);
        return userArray;
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
    public UserData() {
        //parse json here and make a HashMap
        try {

            User[] userArray = UserParse(String.valueOf(getFileFromResource("userData.json")));
            for (User user : userArray) {
                userData.put(user.getUsername(), user.getPassword());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    protected HashMap<String, String> getUserData() {

        return userData;
    }



}