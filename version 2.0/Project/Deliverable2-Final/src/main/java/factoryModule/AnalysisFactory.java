package factoryModule;

import analysisModule.Analysis;
import analysisModule.AnalysisRepo;



public class AnalysisFactory {
    AnalysisRepo repo = AnalysisRepo.getInstance();

    /**
     * This method is used to generate an Analysis object class using the factory design pattern
     * @param name -The input name of the analysis is required
     * @return Analysis class object required is returned
     */
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
