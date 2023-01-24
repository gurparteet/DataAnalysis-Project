package analysisModule;

import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

import resultModule.OneSeriesResult;
import resultModule.Result;
import userSelectionModule.UserSelection;

public class EducationVsGDPAnalysis extends Analysis{

    Vector<HashMap<String,Double>> fetchedData = new Vector<HashMap<String,Double>>();
    
    public EducationVsGDPAnalysis(){
        //Education
        indicators.add("SE.XPD.TOTL.GD.ZS");
    }

    public Result doAnalysis(UserSelection selection) {

        for(String s: indicators){
            selection.setAnalysis(s);
            fetchedData.add(fetch.fetchData(selection));
        }

        //Calculating average forest area
        double totalEducationExpenditure = 0;


        for(HashMap<String,Double> map : fetchedData){
            Set <String> keys = map.keySet();
            for(String k: keys){
                totalEducationExpenditure += map.get(k);
            }
        }

        double averageEducationExpenditure = totalEducationExpenditure/fetchedData.get(0).size();
        double remainingExpenditure = 100-averageEducationExpenditure;
        System.out.println(averageEducationExpenditure);
        HashMap<String,Double> data = new HashMap<String,Double>();
        data.put(Integer.toString(1),averageEducationExpenditure);
        data.put(Integer.toString(2),remainingExpenditure);

        String title = "Government Expenditure: GDP Education vs Remaining GDP";
        String categories [] = {"Educational Expenditure","Remaining Expenditure"};
        Vector <HashMap<String,Double>> resultData = new Vector<HashMap<String,Double>>();
        resultData.add(data);

        return new OneSeriesResult(title, resultData, categories);
    }
    
}
