package modifiedRepos;
import java.util.HashMap;
import java.util.Vector;

public class AnalysisRepo {
    private HashMap<String,String> analysisRepo = new HashMap<String,String>();
    private static AnalysisRepo instance = null;
    
    private AnalysisRepo(){
        analysisRepo.put("Forest Area", null);
        analysisRepo.put("Health Expenditure Vs Hospital Beds", null);
        analysisRepo.put("Educational Expenditure Vs Total GDP", null);
        analysisRepo.put("CO2 Emissions Vs GDP Per Capita", null);
        analysisRepo.put("Health Care Vs Mortality Rate", null);
        analysisRepo.put("PM2.5 vs Forest Area Percentage Change", null);
        analysisRepo.put("Education Vs Health Expenditure Percentage Change", null);
        analysisRepo.put("CO2 Emissions Vs Energy Vs PM2.5 Percentage Change", null);
        analysisRepo.put("CO2 Emissions Vs Energy Vs PM2.5 Percentage Change", null);
        analysisRepo.put("Covid Cases", null);
    }

    public static AnalysisRepo getInstance(){
        if(instance == null){
            instance = new AnalysisRepo();
        }
        return instance;
    }

    

    public Vector<String> getAnalysisNames(){
        Vector<String> names = new Vector<String>();
        analysisRepo.forEach((key,value) -> {
            names.add(key);
        });
        return names;
    }
}