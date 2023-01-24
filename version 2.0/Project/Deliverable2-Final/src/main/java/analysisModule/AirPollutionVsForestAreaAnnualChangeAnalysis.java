package analysisModule;
import userSelectionModule.UserSelection;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.Vector;

import resultModule.Result;
import resultModule.TwoSeriesResult;
/*
 * TWO SERIES
 * WORKING
 * ANNUAL PERCENT CHANGE
 * THE POLLUTION PER CUBIC METER COMPLETLY OUTWEIGHS THE FOREST AREA CHANGE AND THE GRAPHS LOOK DUMB
 * BUT WORKING
 */
public class AirPollutionVsForestAreaAnnualChangeAnalysis extends Analysis {
    
    Vector<HashMap<Integer,Double>> fetchedData = new Vector<HashMap<Integer,Double>>();

    public AirPollutionVsForestAreaAnnualChangeAnalysis(){
        //forest area
        indicators.add("AG.LND.FRST.ZS");
        //air pollution
        indicators.add("EN.ATM.PM25.MC.M3");
    }


    public Result doAnalysis(UserSelection selection) {
        
        String fromDate = String.valueOf(Integer.parseInt(selection.getFromDate())-1);
        int toDate = Integer.parseInt(selection.getToDate());

        for(String s: indicators){
            //setting start year to a year before they selected
            //so we can calculate percent annual change
            selection.setAnalysis(s);
            selection.setFromDate(fromDate);
            fetchedData.add(fetch.fetchData(selection));
            //failsafe to reset user selection back to original
            selection.setFromDate(Integer.toString(Integer.parseInt(fromDate)+1));
        }


        //the below is sorting the data fetched, and seperating it all
        TreeMap<Integer,Double> sortedForest = new TreeMap<Integer,Double>();
        sortedForest.putAll(fetchedData.get(0));
        TreeMap<Integer,Double> sortedPollution = new TreeMap<Integer,Double>();
        sortedPollution.putAll(fetchedData.get(1));


       //Calculating percentage change for each data group
       HashMap<Integer,Double> calculatedForest = changeCalculator(Integer.parseInt(fromDate),toDate,sortedForest);
       HashMap<Integer,Double> calculatedPollution = changeCalculator(Integer.parseInt(fromDate),toDate,sortedPollution);


        //creating result object
        String title = "Forest Area Annual Change vs Air Pollution Annual Change";
        String categories [] = {"Forest Area Annual Change","Air Pollution Annual Change"};
        Vector <HashMap<Integer,Double>> resultData = new Vector<HashMap<Integer,Double>>();
        resultData.add(calculatedForest);
        resultData.add(calculatedPollution);
        return new TwoSeriesResult(title, selection, resultData, categories);
    }

    public HashMap<Integer,Double> changeCalculator(int fromDate, int toDate, TreeMap<Integer,Double> list){
        HashMap<Integer,Double> calculatedList = new HashMap<Integer,Double>();
        for(int i = fromDate + 1; i <= toDate; i++){
            double v2 = list.get(i);
            double v1 = list.get(i-1);
            if(v1 == 0){
                calculatedList.put(i,0.0);
            }else{
                double percentageChange = ((v2-v1)/Math.abs(v1))*100;
                calculatedList.put(i,percentageChange);
            }
        }
        return calculatedList;
    }
}
