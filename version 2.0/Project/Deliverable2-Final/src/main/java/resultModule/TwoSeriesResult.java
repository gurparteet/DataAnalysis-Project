package resultModule;

import java.util.HashMap;
import java.util.Vector;

import userSelectionModule.UserSelection;

public class TwoSeriesResult extends Result{

    public TwoSeriesResult(String title, UserSelection selection, Vector<HashMap<Integer,Double>> resultData, String [] categories){
        super(title, selection, resultData, categories);
    }

}
