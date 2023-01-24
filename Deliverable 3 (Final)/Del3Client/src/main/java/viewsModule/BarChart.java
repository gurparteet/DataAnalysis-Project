package viewsModule;

import java.awt.Font;

import javax.swing.BorderFactory;
import java.awt.Dimension;
import java.awt.Color;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import resultModule.Result;
import uiModule.MainUI;

public class BarChart implements View{



    public BarChart(){


    }

    //pass result here
    public void draw(Result result) {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            for(int i=0; i < result.resultData.size(); i++){
                String category = result.categoriesList[i];
                result.resultData.get(i).forEach((key,value) -> {
                    dataset.setValue(value,category,key);
                });
            }   

    
            CategoryPlot plot = new CategoryPlot();
            BarRenderer barrenderer1 = new BarRenderer();
            // BarRenderer barrenderer2 = new BarRenderer();
    
            plot.setDataset(0, dataset);
            plot.setRenderer(0, barrenderer1);
            CategoryAxis domainAxis = new CategoryAxis("Year");
            plot.setDomainAxis(domainAxis);
            plot.setRangeAxis(new NumberAxis(""));
    
            // plot.setDataset(1, dataset2);
            // plot.setRenderer(1, barrenderer2);
            plot.setRangeAxis(1, new NumberAxis("US$"));
    
            plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
            plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis
    
            JFreeChart barChart = new JFreeChart(result.title,
                    new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

            ChartPanel chartPanel = new ChartPanel(barChart);
            chartPanel.setPreferredSize(new Dimension(400, 300));
            chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            chartPanel.setBackground(Color.white);
            
            MainUI.getInstance().getWest().add(chartPanel);
        }

    
    }


    

