package resultModule;

import java.util.HashMap;
import java.util.Vector;

public class OneSeriesResult extends Result{

    public OneSeriesResult(String title, Vector<HashMap<String,Double>> resultData, String [] categories){
        super(title, resultData, categories);
    }
    
}
