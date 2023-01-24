package analysisModule;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.Vector;

import resultModule.Result;
import resultModule.TwoSeriesResult;
import userSelectionModule.UserSelection;

public class EducationVsHealthCareAnalysis extends Analysis{

    Vector<HashMap<Integer,Double>> fetchedData = new Vector<HashMap<Integer,Double>>();

    public EducationVsHealthCareAnalysis(){
        //Govenment Expenditure on Education total (% of GDP)
        indicators.add("SE.XPD.TOTL.GD.ZS");
        //Current Health Expenditure (% of GDP)
        indicators.add("SH.XPD.CHEX.GD.ZS");
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

        TreeMap<Integer,Double> sortedEducation = new TreeMap<Integer,Double>();
        sortedEducation.putAll(fetchedData.get(0));
        TreeMap<Integer,Double> sortedHealth = new TreeMap<Integer,Double>();
        sortedHealth.putAll(fetchedData.get(1));


       //Calculating percentage change for each data group
       HashMap<Integer,Double> calculatedEducation = changeCalculator(Integer.parseInt(fromDate),toDate,sortedEducation);
       HashMap<Integer,Double> calculatedHealth = changeCalculator(Integer.parseInt(fromDate),toDate,sortedHealth);


       //creating result object
       String title = "Education Vs Health Care GDP % Change";
       String categories [] = {"Education % Change","Health Care % Change"};
       Vector <HashMap<Integer,Double>> resultData = new Vector<HashMap<Integer,Double>>();
       resultData.add(calculatedEducation);
       resultData.add(calculatedHealth);


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
