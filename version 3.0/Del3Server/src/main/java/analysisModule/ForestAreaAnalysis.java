package analysisModule;

import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

import resultModule.OneSeriesResult;
import resultModule.Result;
import userSelectionModule.UserSelection;
/*
 * ONE SERIES
 * WORKING
 */
public class ForestAreaAnalysis extends Analysis{

    Vector<HashMap<String,Double>> fetchedData = new Vector<HashMap<String,Double>>();
    
    public ForestAreaAnalysis(){
        //forest area
        indicators.add("AG.LND.FRST.ZS");
    }

    public Result doAnalysis(UserSelection selection) {

        for(String s: indicators){
            selection.setAnalysis(s);
            fetchedData.add(fetch.fetchData(selection));
        }

        //Calculating average forest area

        int totalForestArea = 0;

        for(HashMap<String,Double> map : fetchedData){
            Set <String> keys = map.keySet();
            for(String k: keys){
                totalForestArea += map.get(k);
            }
        }

        double averageForestArea = totalForestArea/fetchedData.get(0).size();
        double remainingLandArea = 100-averageForestArea;

        
        HashMap<String,Double> data = new HashMap<String,Double>();
        data.put(Integer.toString(1),averageForestArea);
        data.put(Integer.toString(2),remainingLandArea);

        String title = "Land Distribution: Forest Area vs Remaining Land Area";
        String categories [] = {"Forest Area","Remaining Land Area"};
        Vector <HashMap<String,Double>> resultData = new Vector<HashMap<String,Double>>();
        resultData.add(data);


        return new OneSeriesResult(title, resultData, categories);
    }
    
}
