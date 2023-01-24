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
public class CO2GDPAnalysis extends Analysis{

    Vector<HashMap<String,Double>> fetchedData = new Vector<HashMap<String,Double>>();
    
    public CO2GDPAnalysis(){
        //GDP $
        indicators.add("NY.GDP.PCAP.CD");
        //CO2 air pollution
        indicators.add("EN.ATM.CO2E.PC");
    }

    public Result doAnalysis(UserSelection selection) {
        
        //fetch data
        indicators.forEach((indicator) -> {
            selection.setAnalysis(indicator);
            fetchedData.add(fetch.fetchData(selection));
        });


        HashMap<String,Double> gdpHash = fetchedData.elementAt(0);
        HashMap<String,Double> co2Hash = fetchedData.elementAt(1);
        
        
        HashMap<String,Double> computedRatio = new HashMap<String,Double>();

        gdpHash.forEach((key,value) -> {
            if(value == 0){
                computedRatio.put(key, 0.0);
            }else{
                computedRatio.put(key, co2Hash.get(key)/value );
            }
        });

        Vector<HashMap<String,Double>> resultData = new Vector<HashMap<String,Double>>();
        resultData.add(computedRatio);
        
        String categories [] = {"CO2 Emissions Vs GDP per capita"};

        return new OneSeriesResult("CO2 Emissions vs GDP Per Capita", resultData, categories);

    }


}
