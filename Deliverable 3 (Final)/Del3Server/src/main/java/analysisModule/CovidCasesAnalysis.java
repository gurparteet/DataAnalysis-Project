package analysisModule;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.Vector;

import datafetcherModule.OCDataFetcher;
import resultModule.OneSeriesResult;
import resultModule.Result;
import userSelectionModule.UserSelection;

public class CovidCasesAnalysis extends Analysis {

    
    public Result doAnalysis(UserSelection selection) {
        
        this.fetch = new OCDataFetcher();
        TreeMap<String,Double> sortedCovid = new TreeMap<String,Double>();
        sortedCovid.putAll(fetch.fetchData(selection));

        HashMap<String,Double> calculatedCovid = new HashMap<String,Double>();

        sortedCovid.forEach((key,value) -> {
            String year = key.substring(0,4);

            if(calculatedCovid.containsKey(year)){
                double sum = calculatedCovid.get(year) + value;
                calculatedCovid.put(year, sum);
            }else{
                calculatedCovid.put(year,value);
            }
        });

        String title = "Total Covid Cases";
        String categories [] = {"Covid Cases"};
        Vector <HashMap<String,Double>> resultData = new Vector<HashMap<String,Double>>();
        resultData.add(calculatedCovid);
        // System.out.println(calculatedCovid);

        return new OneSeriesResult(title, resultData, categories);
    }
    
}
