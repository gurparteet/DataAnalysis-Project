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
        temp.put("PM2.5 % Change vs Forest Area % Change", "TwoSeries");
        temp.put("Education Vs Health Expenditure % Change", "TwoSeries");
        temp.put("CO2 Emissions Vs Energy Vs PM2.5 % Change", "ThreeSeries");
    }




    public Boolean isValid(UserSelection selection,String currentAnalysis){
        
        Boolean flag = true;
        String chartType;

        if(temp.containsKey(currentAnalysis)){
            
            chartType = temp.get(currentAnalysis);

            if(chartType == "OneSeries" || chartType == "TwoSeries"){
                if(selection.getViews().contains("Pie Chart")){
                    mainUI.displayError("You tried to display a Pie chart on an analysis that does not support a Pie Chart");
                    currentAnalysis = null;
                    flag = false;
                }
            }
            else if(chartType == "PChart"){
                if(selection.getViews().contains("Line Chart") || selection.getViews().contains("Bar Chart") || selection.getViews().contains("Scatter Chart") || selection.getViews().contains("Time Series") || selection.getViews().contains("Report")){
                    mainUI.displayError("You tried to display a chart other than a Pie Chart on an analysis that does not support charts other than: Pie Chart");
                    currentAnalysis = null;
                    flag = false;
                }
            }

        }

        return flag;
    }
}