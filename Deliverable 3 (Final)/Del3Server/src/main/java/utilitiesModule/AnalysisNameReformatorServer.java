package utilitiesModule;

public class AnalysisNameReformatorServer {
    String reformatedAnalysisName = "";
    public String reformat(String analysisname){
        reformatedAnalysisName = analysisname.replace("(%20)$", " ");
        return reformatedAnalysisName;
    }
 }
