package analysisModule;

import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

import resultModule.OneSeriesResult;
import resultModule.Result;
import userSelectionModule.UserSelection;

public class EducationVsGDPAnalysis extends Analysis{

    Vector<HashMap<Integer,Double>> fetchedData = new Vector<HashMap<Integer,Double>>();
    
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


        for(HashMap<Integer,Double> map : fetchedData){
            Set <Integer> keys = map.keySet();
            for(Integer k: keys){
                totalEducationExpenditure += map.get(k);
            }
        }

        double averageEducationExpenditure = totalEducationExpenditure/fetchedData.get(0).size();
        double remainingExpenditure = 100-averageEducationExpenditure;
        System.out.println(averageEducationExpenditure);
        HashMap<Integer,Double> data = new HashMap<Integer,Double>();
        data.put(1,averageEducationExpenditure);
        data.put(2,remainingExpenditure);

        String title = "Government Expenditure: GDP Education vs Remaining GDP";
        String categories [] = {"Educational Expenditure","Remaining Expenditure"};
        Vector <HashMap<Integer,Double>> resultData = new Vector<HashMap<Integer,Double>>();
        resultData.add(data);

        return new OneSeriesResult(title, selection, resultData, categories);
    }
    
}
