package analysisModule;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.Vector;

import resultModule.Result;
import resultModule.TwoSeriesResult;
import userSelectionModule.UserSelection;

public class EducationVsHealthCareAnalysis extends Analysis{

    Vector<HashMap<String,Double>> fetchedData = new Vector<HashMap<String,Double>>();

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

        TreeMap<String,Double> sortedEducation = new TreeMap<String,Double>();
        sortedEducation.putAll(fetchedData.get(0));
        TreeMap<String,Double> sortedHealth = new TreeMap<String,Double>();
        sortedHealth.putAll(fetchedData.get(1));


       //Calculating percentage change for each data group
       HashMap<String,Double> calculatedEducation = changeCalculator(Integer.parseInt(fromDate),toDate,sortedEducation);
       HashMap<String,Double> calculatedHealth = changeCalculator(Integer.parseInt(fromDate),toDate,sortedHealth);


       //creating result object
       String title = "Education Vs Health Care GDP % Change";
       String categories [] = {"Education % Change","Health Care % Change"};
       Vector <HashMap<String,Double>> resultData = new Vector<HashMap<String,Double>>();
       resultData.add(calculatedEducation);
       resultData.add(calculatedHealth);


        return new TwoSeriesResult(title, resultData, categories);
    }

    public HashMap<String,Double> changeCalculator(int fromDate, int toDate, TreeMap<String,Double> list){
        HashMap<String,Double> calculatedList = new HashMap<String,Double>();
        for(int i = fromDate + 1; i <= toDate; i++){
            double v2 = list.get(Integer.toString(i));
            double v1 = list.get(Integer.toString(i-1));
            if(v1 == 0){
                calculatedList.put(Integer.toString(i),0.0);
            }else{
                double percentageChange = ((v2-v1)/Math.abs(v1))*100;
                calculatedList.put(Integer.toString(i),percentageChange);
            }
        }
        return calculatedList;
    }
    
}
