package datafetcherModule;
import java.util.HashMap;
import userSelectionModule.UserSelection;

public abstract class DataFetcher {

    public abstract String generateURL(UserSelection selection);

    public abstract HashMap<String,Double> fetchData(UserSelection selection);
    
}