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

    Vector<HashMap<Integer,Double>> fetchedData = new Vector<HashMap<Integer,Double>>();
    
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

        for(HashMap<Integer,Double> map : fetchedData){
            Set <Integer> keys = map.keySet();
            for(Integer k: keys){
                totalForestArea += map.get(k);
            }
        }

        double averageForestArea = totalForestArea/fetchedData.get(0).size();
        double remainingLandArea = 100-averageForestArea;

        
        HashMap<Integer,Double> data = new HashMap<Integer,Double>();
        data.put(1,averageForestArea);
        data.put(2,remainingLandArea);

        String title = "Land Distribution: Forest Area vs Remaining Land Area";
        String categories [] = {"Forest Area","Remaining Land Area"};
        Vector <HashMap<Integer,Double>> resultData = new Vector<HashMap<Integer,Double>>();
        resultData.add(data);


        return new OneSeriesResult(title, selection, resultData, categories);
    }
    
}
