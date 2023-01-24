package userSelectionModule;

import java.util.Vector;

// import utilitiesModule.analysisNameReformatorServer;

public class UserSelection {

    private static UserSelection instance = null;
    private String country, fromDate, toDate, analysisType, apiType;
    private Vector<String> views = new Vector<String>();
    // private analysisNameReformatorServer aNR = new analysisNameReformatorServer();
    private  UserSelection(){

    }

    //SINGLETON DESIGN PATTERN
    public static UserSelection getInstance(){
        if(instance == null){
            instance = new UserSelection();
        }
        return instance;
    }
    public void setAll(String country, String fromDate, String toDate, String analysisType, String apiType){
        this.country = country;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.analysisType = analysisType;
        this.apiType = apiType;
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

    public void setView(String view){
        //view isnt in list then add it
        if(!views.contains(view)){
            views.add(view);
            System.out.println("View " + view + " has been added");
        }
    }

    public String getApiType() {
        return apiType;
    }

    public void setApiType(String apiType) {
        this.apiType = apiType;
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
