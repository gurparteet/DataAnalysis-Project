package factoryModule;

import analysisModule.Analysis;
import analysisModule.AnalysisRepo;



public class AnalysisFactory {
    AnalysisRepo repo = AnalysisRepo.getInstance();

    public Analysis generateAnalysis(String name){

        Analysis analysis = null;
        try{
            analysis = repo.getAnalysis(name).getClass().getConstructor().newInstance();
        }catch(Exception e){
            System.out.print(e);
        }
        return analysis;

    }
}
