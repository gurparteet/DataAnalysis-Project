package userSelectionModule;

import java.util.Vector;

public class UserSelection {

    private static UserSelection instance = null;
    private String country, fromDate, toDate, analysisType, apiType;
    private Vector<String> views = new Vector<String>();
    
    private  UserSelection(){

    }

    //SINGLETON DESIGN PATTERN
    public static UserSelection getInstance(){
        if(instance == null){
            instance = new UserSelection();
        }
        return instance;
    }

    public void setCountry(String country){
        this.country = country;
    }

    public String getCountry(){
        return this.country;
    }

    public void setFromDate(String fromDate){
        this.fromDate = fromDate;
    }

    public String getFromDate(){
        return this.fromDate;
    }
    
    public void setToDate(String toDate){
        this.toDate = toDate;
    }

    public String getToDate(){
        return this.toDate;
    }
    public void setAnalysis(String analysisType){
        this.analysisType = analysisType;
    }

    public String getAnalysis(){
        return this.analysisType;
    }

    public String getApiType() {
        return apiType;
    }

    public void setApiType(String apiType) {
        this.apiType = apiType;
    }

    public void setView(String view){
        //view isnt in list then add it
        if(!views.contains(view)){
            views.add(view);
            System.out.println("View " + view + " has been added");
        }
    }

    public void removeView(String view){
        if(views.contains(view)){
            views.remove(view);
            System.out.println("View " + view + " has been removed");
        }
    }

    public Vector<String> getViews(){
        return this.views;
    }


}
