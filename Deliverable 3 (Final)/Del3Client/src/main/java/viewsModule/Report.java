package viewsModule;

import javax.swing.*;

import resultModule.Result;
import uiModule.MainUI;

import java.awt.*;
import java.awt.Dimension;
import java.util.Set;

public class Report implements View{

    public Report(){

    }


	//change this to a draw method

	public void draw(Result result){
            JTextArea report = new JTextArea();
			report.setEditable(false);
			report.setPreferredSize(new Dimension(400, 300));
			report.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			report.setBackground(Color.white);
			String reportMessage = "";

			Set <String> keys = result.resultData.get(0).keySet();

			reportMessage = result.title +"\n" + "==============================\n";
			for(String k : keys){
				reportMessage += "\nYear " + k + ":\n";
				for(int i=0; i<result.resultData.size(); i++){
					reportMessage += result.categoriesList[i] + " => "
					 + result.resultData.get(i).get(k) + "\n";
				}
			}

			report.setText(reportMessage);
			JScrollPane outputScrollPane = new JScrollPane(report);
			
			MainUI.getInstance().getWest().add(outputScrollPane);
        
	}
    
}
