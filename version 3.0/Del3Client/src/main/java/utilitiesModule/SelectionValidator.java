package utilitiesModule;

import java.util.HashMap;

import uiModule.MainUI;
import userSelectionModule.UserSelection;

public class SelectionValidator{

    HashMap<String, String> temp = new HashMap<>();
    MainUI mainUI = MainUI.getInstance();

    public SelectionValidator(){
        temp.put("Forest Area", "PChart");
        temp.put("Health Expenditure Vs Hospital Beds", "OneSeries");
        temp.put("Educational Expenditure Vs Total GDP", "PChart");
        temp.put("CO2 Emissions Vs GDP Per Capita", "OneSeries");
        temp.put("Health Care Vs Mortality Rate", "TwoSeries");
        temp.put("PM2.5 vs Forest Area Percentage Change", "TwoSeries");
        temp.put("Education Vs Health Expenditure Percentage Change", "TwoSeries");
        temp.put("CO2 Emissions Vs Energy Vs PM2.5 Percentage Change", "ThreeSeries");
    }




    public Boolean isValid(UserSelection selection,String currentAnalysis){
        
        String chartType;

        if(temp.containsKey(currentAnalysis)){
            
            chartType = temp.get(currentAnalysis);

            if(chartType == "OneSeries" || chartType == "TwoSeries"){
                if(selection.getViews().contains("Pie Chart")){
                    mainUI.displayError("You tried to display a Pie chart on an analysis that does not support a Pie Chart");
                    currentAnalysis = null;
                    return false;
                }
            }
            else if(chartType == "PChart"){
                if(selection.getViews().contains("Line Chart") || selection.getViews().contains("Bar Chart") || selection.getViews().contains("Scatter Chart") || selection.getViews().contains("Time Series") || selection.getViews().contains("Report")){
                    mainUI.displayError("You tried to display a chart other than a Pie Chart on an analysis that does not support charts other than: Pie Chart");
                    currentAnalysis = null;
                    return false;
                }
            }

        }

        //VALIDATE USER SELECTION
        if(selection.getCountry() == null || selection.getFromDate() == null || selection.getToDate() == null 
            || selection.getViews().isEmpty() || currentAnalysis == null){
            //Error message display
            mainUI.displayError("Please ensure that all fields have been properly selected");
            return false;
        }

        //VALID API CHOICE
        if(selection.getApiType() == "OpenCovid" && !selection.getCountry().equals("CAN")){
            mainUI.displayError("The Open Covid API is only available for the country CANADA");
            return false;
        }
        //VALID API CHOICE
        if(selection.getApiType() == "OpenCovid" && !currentAnalysis.equals("Covid Cases")){
            mainUI.displayError("Only Covid Cases Analysis is allowed for the Open Covid API");
            return false;
        }
        //VALID API CHOICE
        if(selection.getApiType() == "World Bank" && selection.getAnalysis().equals("Covid Cases")){
            mainUI.displayError("Covid Analysis Is Only Allowed For Open Covid API");
            return false;
        }

        return true;

    }
    
}