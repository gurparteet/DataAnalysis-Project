package analysisModule;

import java.util.Vector;

import datafetcherModule.DataFetcher;
import resultModule.Result;
import userSelectionModule.UserSelection;


public abstract class Analysis {

    public Vector <String> indicators = new Vector<String>();
    public DataFetcher fetch = new DataFetcher();;
    public abstract Result doAnalysis(UserSelection selection);
    
}
