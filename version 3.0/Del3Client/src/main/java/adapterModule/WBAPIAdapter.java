package adapterModule;

import userSelectionModule.UserSelection;
import utilitiesModule.AnalysisNameReformator;

public class WBAPIAdapter extends URLAdapter {

    private AnalysisNameReformator aNR = new AnalysisNameReformator();

    public String build(UserSelection selection) {
        String url = "http://localhost:8000/WBAnalysis/?p1=%s&p2=%s&p3=%s&p4=%s";
        return String.format(url, selection.getCountry(), selection.getFromDate(), selection.getToDate(), aNR.reformatSpaces(selection.getAnalysis()));
    }
    
}
