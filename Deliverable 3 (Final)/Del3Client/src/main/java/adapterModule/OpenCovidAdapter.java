package adapterModule;

import userSelectionModule.UserSelection;

public class OpenCovidAdapter extends URLAdapter{

    public String build(UserSelection selection) {
        String url = "http://localhost:8000/OCAnalysis/?p1=%s&p2=%s&p3=%s";
        return String.format(url, selection.getCountry(), selection.getFromDate(), selection.getToDate());
    }
    
}
