package commandModule;

import adapterModule.URLAdapter;
import factoryModule.AdapterFactory;
import httpModule.HttpClient;
import resultModule.Result;
import userSelectionModule.UserSelection;



public class AnalysisCommand extends Command {
    UserSelection selection;
    String analysisName;

    HttpClient client = HttpClient.getInstance();

    /**
     * This is a constructor for AnalysisCommand class with parameters UserSelection and MainUI
     * @param selection- userSelection object that has the user selection  data
     * @param analysisName- String that has the analysis name
     */
    public AnalysisCommand(UserSelection selection, String analysisName) {
        this.selection = selection;
        this.analysisName = analysisName;
    }

    /**
     * This method is from the parent abstract class Command and is used to instantaites the analysis factory (Facotry design pattern) and the analysis object(Strategy design pattern)
     * @return Result object
     */
    public void doAction() {
       
        // CREATING THE NECESSARY URL ADAPTER TO PERFORM THE HTTP REQUEST(FACTORY PATTERN)
        AdapterFactory factory = new AdapterFactory();
        URLAdapter urlAdapter = factory.generateAdapter(selection.getApiType());
        
        //BUILDING THE URL AND SENDING IT TO THE HTTPCLIENT WHICH RETURNS A RESULT OBJECT
        Result result = client.get(urlAdapter.build(selection));

        //JUST IN TIME INITIALIZATION FOR VIEWS/OBSERVERS
        result.generateViews(selection);

        //OBSERVER PATTERN 
        result.notifyViews();
        
    }

}
