package viewsModule;
import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.util.TableOrder;
import org.jfree.data.category.DefaultCategoryDataset;

import resultModule.Result;
import uiModule.MainUI;

import java.awt.Dimension;
import java.awt.*;

public class PieChart implements View{
    

    public PieChart(){
        
    }


    public void draw(Result result) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        double value1 = result.resultData.get(0).get(Integer.toString(1));
        double value2 = result.resultData.get(0).get(Integer.toString(2));
        dataset.addValue(value1, result.categoriesList[0], result.categoriesList[0]);
        dataset.addValue(value2, result.categoriesList[1], result.categoriesList[0]);

        JFreeChart pieChart = ChartFactory.createMultiplePieChart(result.title, dataset,
                TableOrder.BY_COLUMN, true, true, false);

        ChartPanel chartPanel = new ChartPanel(pieChart);
        chartPanel.setPreferredSize(new Dimension(400, 300));
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);

        MainUI.getInstance().getWest().add(chartPanel);

        }
    }
    

