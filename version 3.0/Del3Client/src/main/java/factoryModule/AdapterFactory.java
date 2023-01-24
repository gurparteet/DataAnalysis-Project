package factoryModule;
import adapterModule.URLAdapter;
import modifiedRepos.APIAdapterRepo;

public class AdapterFactory {
     /**
     * This method is used to generate a URLAdapter object class (Factory design pattern)
     * @param key- The input key of the adapter is required (e.g. "WBAPI")
     * @return
     */

    public URLAdapter generateAdapter(String key){
        
        APIAdapterRepo repo = APIAdapterRepo.getInstance();
        URLAdapter adapter = null;
        try{
            adapter = repo.getAdapter(key).getClass().getConstructor().newInstance();
        }catch(Exception e){
            System.out.print(e);
        }
        return adapter;

    }
}
