package analysisModule;

import java.util.HashMap;
import java.util.Vector;

import resultModule.Result;
import resultModule.TwoSeriesResult;
import userSelectionModule.UserSelection;
/*
 * TWO SERIES
 * WORKING
 */
public class HealthCareVsMortalityAnalysis extends Analysis{

    Vector<HashMap<String,Double>> fetchedData = new Vector<HashMap<String,Double>>();

    /*
     * no calulation needed
     * the indicators get all the data needed accordingly
     */
    public HealthCareVsMortalityAnalysis(){
        
        //Mortality rate, infant (per 1000 live births)
        indicators.add("SP.DYN.IMRT.IN");

        //Problems in accessing health care (getting money for treatment) (% of women): Q1 (lowest wealth)
        indicators.add("SH.ACS.MONY.Q1.ZS");
    }


    public Result doAnalysis(UserSelection selection) {
        
        //fetch data
        indicators.forEach((indicator) -> {
            selection.setAnalysis(indicator);
            fetchedData.add(fetch.fetchData(selection));
        });
    //    Vector<HashMap<Integer, Double>> resultDataVector = new Vector<>();
    //    resultDataVector.add(0, fetchedData.elementAt(0));
    //    resultDataVector.add(1, fetchedData.elementAt(1));
       
        
        
        String categories [] = {"Infant Mortality/1000", "Health Care (Lower 1Q Women)", "Dummy Data"};

        return new TwoSeriesResult("Health Care (Lower 1Q Women) Vs Mortality/1000", fetchedData, categories);
    }
    
}
