package uiModule;

import javax.swing.*;

import modifiedRepos.APIAdapterRepo;
import modifiedRepos.AnalysisRepo;
import utilitiesModule.CountryData;

import java.awt.*;
import java.util.Vector;


public class MainUI extends JFrame{

	private static MainUI ui;
	private Vector<String> years, viewsNames, methodsNames, countryNames, apiNames;
	private JComboBox fromList, toList, countriesList, viewsList, methodsList, apiList;
	private JPanel north ,south, east ,west;
	private JButton recalculate, addView, removeView;
	private JLabel viewsLabel, methodLabel, chooseCountryLabel, from, to, apiLabel;
	
	

//Singleton pattern- MainUI is a created
	public static MainUI getInstance() {
		if (ui == null) {
			ui = new MainUI();
		}
		return ui;
	}

//Constructor for MainUI

	/**
	 * Constructor for MainUI
	 * It creates the mainUI and sets the layout after login
 	 */
	private MainUI(){
		super("Country Statistics");
		CountryData countryData = new CountryData();
		chooseCountryLabel = new JLabel("Choose a country");
		countryNames = new Vector<String>();
		countryNames.add("Choose a Country");
		countryNames.addAll(countryData.getCountryNames());
		//add the countries here
		countriesList = new JComboBox(countryNames);
		//combo.setSize(200, combo.getPreferredSize().height);
		
		from = new JLabel("From");
		to = new JLabel("To");
		apiLabel = new JLabel("Select API");
		
		years = new Vector<String>();
		years.add("-----");
        for (int i = 2022; i >= 1990; i--) {
            years.add("" + i);
        }

		apiNames = new Vector<String>();
		apiNames.add("-----");
		apiNames.addAll(APIAdapterRepo.getInstance().getAdapterNames());
		
		fromList = new JComboBox(years);
		toList = new JComboBox(years);
		apiList = new JComboBox<>(apiNames);
		
		north = new JPanel();
		
		north.add(apiLabel);
		north.add(apiList);
		north.add(chooseCountryLabel);
		north.add(countriesList);
		north.add(from);
		north.add(fromList);
		north.add(to);
		north.add(toList);
		
		recalculate = new JButton("Recalculate");
		
		

		//Setting UI for views
		viewsLabel = new JLabel("Available Views");
		viewsNames = new Vector<String>();
		viewsNames.add("Pie Chart");
        viewsNames.add("Line Chart");
        viewsNames.add("Bar Chart");
        viewsNames.add("Scatter Chart");
        viewsNames.add("Report");
        viewsNames.add("Time Series");
		viewsList = new JComboBox(viewsNames);
		addView = new JButton("+");
		removeView = new JButton("-");
		

		//Setting UI for methods/analysis types
		methodLabel = new JLabel("        Choose analysis method: ");
		methodsNames = new Vector<String>();
		
		
		//adding all analysis names
		methodsNames.add("-----");
		methodsNames.addAll(AnalysisRepo.getInstance().getAnalysisNames());


		methodsList = new JComboBox(methodsNames);



		south = new JPanel();
		south.add(viewsLabel);
		south.add(viewsList);
		south.add(addView);
		south.add(removeView);
		south.add(methodLabel);
		south.add(methodsList);
		south.add(recalculate);

		east = new JPanel();

		west = new JPanel();
		west.setLayout(new GridLayout(2, 0));
		// createCharts(west);
		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(east, BorderLayout.EAST);
		getContentPane().add(south, BorderLayout.SOUTH);
		getContentPane().add(west, BorderLayout.WEST);

	}
	

	public JComboBox getCountriesList(){
		return countriesList;
	}

	public JComboBox getFromList(){
		return fromList;
	}
	public JComboBox getToList(){
		return toList;
	}
	public JComboBox getViewsList(){
		return viewsList;
	}
	public JComboBox getMethodsList(){
		return methodsList;
	}
	public JButton getRecalculateButton(){
		return recalculate;
	}
	public JButton getAddButton(){
		return addView;
	}
	public JButton getRemoveButton(){
		return removeView;
	}

	public JPanel getWest(){
		return this.west;
	}
	
	public JComboBox getApiList(){
		return apiList;
	}

	//SIMPLE ERROR DISPLAY PANEL
	public void displayError(String message){
		JOptionPane.showMessageDialog(this, message,
            "INVALID SELECTION", JOptionPane.ERROR_MESSAGE);
	}

	public void refresh(){
		revalidate();
		repaint();
	}




}