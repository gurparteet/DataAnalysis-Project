package analysisModule;

import java.util.HashMap;
import java.util.Vector;

public class AnalysisRepo {

    private HashMap<String,Analysis> analysisRepo = new HashMap<String,Analysis>();
    private static AnalysisRepo instance = null;
    
    private AnalysisRepo(){
        analysisRepo.put("Forest Area", new ForestAreaAnalysis());
        analysisRepo.put("Health Expenditure Vs Hospital Beds", new HealthVsHospitalBedsAnalysis());
        analysisRepo.put("Educational Expenditure Vs Total GDP", new EducationVsGDPAnalysis());
        analysisRepo.put("CO2 Emissions Vs GDP Per Capita", new CO2GDPAnalysis());
        analysisRepo.put("Health Care Vs Mortality Rate", new HealthCareVsMortalityAnalysis());
        analysisRepo.put("PM2.5 vs Forest Area Percentage Change", new AirPollutionVsForestAreaAnnualChangeAnalysis());
        analysisRepo.put("Education Vs Health Expenditure Percentage Change", new EducationVsHealthCareAnalysis());
        analysisRepo.put("CO2 Emissions Vs Energy Vs PM2.5 Percentage Change", new CO2VsEnergyVsAirPollutionAnalysis());
        analysisRepo.put("Covid Cases", new CovidCasesAnalysis());

    }

    public static AnalysisRepo getInstance(){
        if(instance == null){
            instance = new AnalysisRepo();
        }
        return instance;
    }

    public Analysis getAnalysis(String name){
        return analysisRepo.get(name);
    }

    public Vector<String> getAnalysisNames(){
        Vector<String> names = new Vector<String>();
        analysisRepo.forEach((key,value) -> {
            names.add(key);
        });
        return names;
    }
    
}
