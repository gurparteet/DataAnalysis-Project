package resultModule;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import factoryModule.ViewFactory;
import uiModule.MainUI;
import userSelectionModule.UserSelection;
import viewsModule.View;

public abstract class Result {
    
    public String title;
    public UserSelection userSelection;
    public String analysisName;
    public List <View> views = new Vector <View>();
    public Vector<HashMap<Integer,Double>> resultData = new Vector<HashMap<Integer,Double>>();
    public String categoriesList [];

    /**
     * This is a constructor for Result class.
     * @param title- String  title of the result
     * @param selection - UserSelection object containing the user selection
     * @param resultData - Vector of HashMaps containing the result data
     * @param categories - String array containing the categories
     */

    public Result (String title, UserSelection selection, Vector<HashMap<Integer,Double>> resultData, String [] categories){
        this.title = title;
        this.userSelection = selection;
        this.resultData = resultData;
        this.categoriesList = categories;
        generateViews();
    }

    //

    /**
     * This method generates the views for the result.
     * @return void
     *
     */

    public void notifyViews() {
        for(View v : this.views){
            v.draw(this);
        }
        //Refresh UI to display changes.
        MainUI.getInstance().refresh();
    }

    /**
     * This method generates the views (factory Design Pattern).
     */
    public void generateViews(){
        ViewFactory factory = new ViewFactory();
        MainUI.getInstance().getWest().removeAll();
        for(String v: this.userSelection.getViews()){
            views.add(factory.generateView(v));
        }
    }


}
