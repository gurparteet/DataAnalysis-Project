package resultModule;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import factoryModule.ViewFactory;
import uiModule.MainUI;
import userSelectionModule.UserSelection;
import viewsModule.View;


public class Result {
    
    public String title;
    public String type;
    public String analysisName;
    public Vector<HashMap<String,Double>> resultData = new Vector<HashMap<String,Double>>();
    public String categoriesList [];
    public List <View> views = new Vector<View>();

    /**
     * This is a constructor for Result class.
     * @param title- String  title of the result
     * @param selection - UserSelection object containing the user selection
     * @param resultData - Vector of HashMaps containing the result data
     * @param categories - String array containing the categories
     */

    public Result (String title, Vector<HashMap<String,Double>> resultData, String [] categories, String type){
        this.title = title;
        this.type = type;
        this.resultData = resultData;
        this.categoriesList = categories;
    }

    
    public void generateViews(UserSelection selection){
        //CLEAR SCREEN
        MainUI.getInstance().getWest().removeAll();
        //FACTORY DEESIGN PATTERN
        ViewFactory factory = new ViewFactory();
        views = new Vector<View>();
        for(String v: selection.getViews()){
            views.add(factory.generateView(v));
        }
    }
    
    public void notifyViews() {
        for(View v : views){
            v.draw(this);
        }
        //Refresh UI to display changes.
        MainUI.getInstance().refresh();
    }


   

}
