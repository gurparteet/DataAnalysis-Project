package uiModule;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import commandModule.AnalysisCommand;
import userSelectionModule.UserSelection;
import utilitiesModule.AllowFetch;
import utilitiesModule.CountryData;
import utilitiesModule.SelectionValidator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//THIS ENTIRE CLASS FACILITATES THE FACADE DESIGN PATTERN
public class MainUIFacade implements ItemListener , ActionListener{
    
    private static MainUIFacade instance = null;
    private static MainUI mainUI;
    private UserSelection selection = UserSelection.getInstance();
    private String currentView;
    private String currentAnalysis;
    private String currentApi;

    private MainUIFacade() {

        mainUI = MainUI.getInstance();
        mainUI.setSize(1200, 800);

    }

    public static MainUIFacade getInstance() {

        if (instance == null) {
            instance = new MainUIFacade();
        }
        return instance;
    }

    /**
     *  startmainUI - starts the mainUI and sets the current view after  (Singleton Design Pattern)
     *
     */

    public void startmainUI() {
        mainUI.setVisible(true);

        //setting up event listeners
        mainUI.getCountriesList().addItemListener(this);
        mainUI.getFromList().addItemListener(this);
        mainUI.getToList().addItemListener(this);
        mainUI.getViewsList().addItemListener(this);
        mainUI.getMethodsList().addItemListener(this);
        mainUI.getApiList().addItemListener(this);

        mainUI.getRecalculateButton().addActionListener(this);
        mainUI.getAddButton().addActionListener(this);
        mainUI.getRemoveButton().addActionListener(this);

        currentView = mainUI.getViewsList().getSelectedItem().toString();
        currentAnalysis = mainUI.getMethodsList().getSelectedItem().toString();
        currentApi = mainUI.getApiList().getSelectedItem().toString();
    }

    //Action handler for UI buttons

    /**
     * actionPerformed - It has Action Handler's for UI button for action performed by the user
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e) {
       	if(mainUI.getRecalculateButton().equals(e.getSource())){
			recalculateData();
		}else if(mainUI.getAddButton().equals(e.getSource())){
			selection.setView(currentView);
		}else if(mainUI.getRemoveButton().equals(e.getSource())){
			selection.removeView(currentView);
		}
    }

    //Action handler for UI lists

    /**
     * itemStateChanged - It consist of action handlers the item state change for the UI lists
     * @param e the event to be processed
     */
    public void itemStateChanged(ItemEvent e) {
       
        //Activates when state change is selected
        if(e.getStateChange() == 1){

            if(mainUI.getCountriesList().equals(e.getSource())){
                countryHandler(e);
            }else if(mainUI.getFromList().equals(e.getSource())){
                fromDateHandler(e);
            }else if(mainUI.getToList().equals(e.getSource())){
                toDateHandler(e);
            }else if(mainUI.getViewsList().equals(e.getSource())){
                viewHandler(e);
            }else if(mainUI.getMethodsList().equals(e.getSource())) {
                methodHandler(e);
            }else if(mainUI.getApiList().equals(e.getSource())){
                apiHandler(e);
            }
		}
    }


    //LIST HANDLER FUNCTIONS

    /**
     * countryHandler - It list  handles the country list
     * @param e ItemEvent--> the event to be processed
     *
     */
    public void countryHandler(ItemEvent e){
        AllowFetch s = new AllowFetch(e.getItem().toString());
        if(!s.isAllowed()){
            CountryData c = new CountryData();
            selection.setCountry(c.getCountryIso(e.getItem().toString()));
        }else{

            //Error message display
            mainUI.displayError("Sorry this country is not allowed for data fetching. Please select another country");
            selection.setCountry(null);
        }
    }

    /**
     * fromDateHandler - It list  handles the from date list
     * @param e ItemEvent--> the event to be processed
     */
    public void fromDateHandler(ItemEvent e){
        if(selection.getToDate() == null){
            selection.setFromDate(e.getItem().toString());
        }else if(Integer.parseInt(selection.getToDate()) < Integer.parseInt(e.getItem().toString())){
            //Error message display
            mainUI.displayError("Please ensure that the selected From date is less than the To date");
            selection.setFromDate(null);
        }else{
            selection.setFromDate(e.getItem().toString());
        }
    }

    public void toDateHandler(ItemEvent e){
        if(selection.getFromDate() == null){
            selection.setToDate(e.getItem().toString());
        }else if(Integer.parseInt(selection.getFromDate()) > Integer.parseInt(e.getItem().toString())){
            //Error message display
            mainUI.displayError("Please ensure that the selected To date is greater than the From date");
            selection.setToDate(null);
        }else{
            selection.setToDate(e.getItem().toString());
        }
    }

    public void viewHandler(ItemEvent e){
        currentView = e.getItem().toString();
    }

    public void methodHandler(ItemEvent e){
        currentAnalysis = e.getItem().toString();
        if(currentAnalysis == null){
            mainUI.displayError("Please choose an analysis");
        }
        else{
            selection.setAnalysis(currentAnalysis);
        }
    }

    public void apiHandler(ItemEvent e) {
        currentApi = e.getItem().toString();
        if(currentApi == null || currentApi == "-----"){
            mainUI.displayError("Please choose an API");
        }
        else {
            selection.setApiType(currentApi);
        }
    }


    /**
     * recalculateData - This method is used to recalculate the data when user clicks on the recalculate button
     */

    public void recalculateData(){

        //CLASS VALIDATES THE USERS SELECTION
        SelectionValidator validator = new SelectionValidator();

        if(validator.isValid(selection,currentAnalysis)){
            //(COMMAND DESIGN PATTERN)
            AnalysisCommand command = new AnalysisCommand(selection, currentAnalysis);
            command.doAction();
            
        }

    }
}


