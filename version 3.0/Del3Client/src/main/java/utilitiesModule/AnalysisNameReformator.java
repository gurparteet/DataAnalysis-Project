package utilitiesModule;

public class AnalysisNameReformator {
    String newAnalysisString = "";

    public String reformatSpaces(String analysis){
        newAnalysisString = analysis.replaceAll(" ", "%20");
       
        return newAnalysisString;
    }
}