package modifiedRepos;
import java.util.HashMap;
import java.util.Vector;

import adapterModule.OpenCovidAdapter;
import adapterModule.URLAdapter;
import adapterModule.WBAPIAdapter;

public class APIAdapterRepo {
    private HashMap<String,URLAdapter> APIAdapterRepo = new HashMap<String,URLAdapter>();
    private static APIAdapterRepo instance = null;
    
    private APIAdapterRepo(){
        APIAdapterRepo.put("World Bank", new WBAPIAdapter());
        APIAdapterRepo.put("OpenCovid", new OpenCovidAdapter());
    }

    public static APIAdapterRepo getInstance(){
        if(instance == null){
            instance = new APIAdapterRepo();
        }
        return instance;
    }

    

    public Vector<String> getAdapterNames(){
        Vector<String> names = new Vector<String>();
        APIAdapterRepo.forEach((key,value) -> {
            names.add(key);
        });
        return names;
    }

    public void addAdapter(String name, URLAdapter adapter){
        APIAdapterRepo.put(name, adapter);
    }

    public URLAdapter getAdapter(String name){
        return APIAdapterRepo.get(name);
    }
}