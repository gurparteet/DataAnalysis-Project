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


    Vector<HashMap<Integer,Double>> fetchedData = new Vector<HashMap<Integer,Double>>();

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

        HashMap<Integer,Double> healthPerCapitaHash = fetchedData.elementAt(0);
        HashMap<Integer,Double> hospitalBedHash = fetchedData.elementAt(1);
        
        
        HashMap<Integer,Double> computedRatio = new HashMap<Integer,Double>();
        
        System.out.println(fetchedData);

        hospitalBedHash.forEach((key,value) -> {
            if(value == 0){
                computedRatio.put(key, 0.0);
            }else{
                computedRatio.put(key, healthPerCapitaHash.get(key)*1000/value );
            }
        });

        Vector<HashMap<Integer,Double>> resultData = new Vector<HashMap<Integer,Double>>();
        resultData.add(computedRatio);

        String categories [] = {"Health Expenditure Vs Hospital Beds/1000 people"};

        return new OneSeriesResult("Health Expenditure Vs Hospital Beds / 1000 people", selection, resultData, categories);
    }
    
}
