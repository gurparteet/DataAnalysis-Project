package analysisModule;

import java.util.HashMap;
import java.util.Vector;

import resultModule.OneSeriesResult;
import resultModule.Result;
import userSelectionModule.UserSelection;
/*
 * ONE SERIES
 * WORKING
 */
public class HealthVsHospitalBedsAnalysis extends Analysis{


    Vector<HashMap<String,Double>> fetchedData = new Vector<HashMap<String,Double>>();

    public HealthVsHospitalBedsAnalysis(){
        
        //current health expenditure per capita (ie per person)
        indicators.add("SH.XPD.CHEX.PC.CD");

        //hospital beds
        indicators.add("SH.MED.BEDS.ZS");
    }

    public Result doAnalysis(UserSelection selection) {

        indicators.forEach((indicator) -> {
            selection.setAnalysis(indicator);
            fetchedData.add(fetch.fetchData(selection));
        });

        HashMap<String,Double> healthPerCapitaHash = fetchedData.elementAt(0);
        HashMap<String,Double> hospitalBedHash = fetchedData.elementAt(1);
        
        
        HashMap<String,Double> computedRatio = new HashMap<String,Double>();
        
        System.out.println(fetchedData);

        hospitalBedHash.forEach((key,value) -> {
            if(value == 0){
                computedRatio.put(key, 0.0);
            }else{
                computedRatio.put(key, healthPerCapitaHash.get(key)*1000/value );
            }
        });

        Vector<HashMap<String,Double>> resultData = new Vector<HashMap<String,Double>>();
        resultData.add(computedRatio);

        String categories [] = {"Health Expenditure Vs Hospital Beds/1000 people"};

        return new OneSeriesResult("Health Expenditure Vs Hospital Beds / 1000 people", resultData, categories);
    }
    
}
