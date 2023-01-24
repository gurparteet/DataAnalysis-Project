package commandModule;

import analysisModule.Analysis;
import factoryModule.AnalysisFactory;
import resultModule.Result;
import uiModule.MainUIFacade;
import userSelectionModule.UserSelection;


public class AnalysisCommand extends Command {
    UserSelection selection;
    String analysisName;
    MainUIFacade mainUIFacade;

    /**
     * This is a constructor for AnalysisCommand class with parameters UserSelection and MainUI
     * @param mainUIFacade - MainUIFacade object that has the mainUI elements
     * @param selection- userSelection object that has the user selection  data
     * @param analysisName- String that has the analysis name
     */
    public AnalysisCommand(MainUIFacade mainUIFacade, UserSelection selection, String analysisName) {
        this.mainUIFacade = mainUIFacade;
        this.selection = selection;
        this.analysisName = analysisName;
    }

    /**
     * This method is from the parent abstract class Command and is used to instantaites the analysis factory (Facotry design pattern) and the analysis object(Strategy design pattern)
     * @return Result object
     */
    public Result doAction() {
        //Instantiate analysis factory (FACTORY DESIGN PATTERN)
        AnalysisFactory factory = new AnalysisFactory();
        //(STRATEGY DESIGN PATTERN)
        //Instantaiate analysis context and dynamically create user selected analysis 
        Analysis analysis = factory.generateAnalysis(analysisName);
        Result result = analysis.doAnalysis(selection);
        return result;
    }

}
