package analysisModule;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.Vector;

import resultModule.Result;
import resultModule.ThreeSeriesResult;
import userSelectionModule.UserSelection;

public class CO2VsEnergyVsAirPollutionAnalysis extends Analysis{

    Vector<HashMap<String,Double>> fetchedData = new Vector<HashMap<String,Double>>();


    public CO2VsEnergyVsAirPollutionAnalysis(){
        //CO2 Emissions (metric tonnes per capita)
        indicators.add("EN.ATM.CO2E.PC");
        //Energy use (kg of oil per capita)
        indicators.add("EG.USE.PCAP.KG.OE");
        //PM2.5 air pollution, mean annual exposure (microorganisms per cubic meter)
        indicators.add("EN.ATM.PM25.MC.M3");
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

        //Sorting Hashmaps by putting them into trees
        TreeMap<String,Double> sortedCO2 = new TreeMap<String,Double>();
        sortedCO2.putAll(fetchedData.get(0));
        TreeMap<String,Double> sortedEnergy = new TreeMap<String,Double>();
        sortedEnergy.putAll(fetchedData.get(1));
        TreeMap<String,Double> sortedPM2 = new TreeMap<String,Double>();
        sortedPM2.putAll(fetchedData.get(2));

        //Calculating percentage change for each data group
        HashMap<String,Double> calculatedCO2 = changeCalculator(Integer.parseInt(fromDate),toDate,sortedCO2);
        HashMap<String,Double> calculatedEnergy = changeCalculator(Integer.parseInt(fromDate),toDate,sortedEnergy);
        HashMap<String,Double> calculatedPM2 = changeCalculator(Integer.parseInt(fromDate),toDate,sortedPM2);

        //creating result object
        String title = "CO2 Vs Energy Vs Air Pollution Annual % Change";
        String categories [] = {"CO2 Emissions % Change","Energy Use % Change","PM2 Air Pollution % Change"};
        Vector <HashMap<String,Double>> resultData = new Vector<HashMap<String,Double>>();
        resultData.add(calculatedCO2);
        resultData.add(calculatedEnergy);
        resultData.add(calculatedPM2);
        return new ThreeSeriesResult(title, resultData, categories);
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
