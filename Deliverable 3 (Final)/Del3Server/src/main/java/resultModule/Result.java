package resultModule;

import java.util.HashMap;
import java.util.Vector;

public abstract class Result {
    
    public String title;
    public String analysisName;
    public Vector<HashMap<String,Double>> resultData = new Vector<HashMap<String,Double>>();
    public String categoriesList [];

    public Result (String title, Vector<HashMap<String,Double>> resultData, String [] categories){
        this.title = title;
        this.resultData = resultData;
        this.categoriesList = categories;
    }



}
