package viewsModule;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import java.awt.Color;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;

import resultModule.Result;
import uiModule.MainUI;

public class TimeSeriesChart implements View{


    public TimeSeriesChart(){


        
    }

    public void draw(Result result){
        TimeSeriesCollection dataset = new TimeSeriesCollection();

        for(int i=0; i < result.resultData.size(); i++){
                TimeSeries series = new TimeSeries(result.categoriesList[i]);
                result.resultData.get(i).forEach((key,value) -> {
                        series.add(new Year(Integer.parseInt(key)),value);
                });
                dataset.addSeries(series);
        }
    

            XYPlot plot = new XYPlot();
            XYSplineRenderer splinerenderer1 = new XYSplineRenderer();
            XYSplineRenderer splinerenderer2 = new XYSplineRenderer();
    
            plot.setDataset(0, dataset);
            plot.setRenderer(0, splinerenderer1);
            DateAxis domainAxis = new DateAxis("Year");
            plot.setDomainAxis(domainAxis);
            plot.setRangeAxis(new NumberAxis(""));
    
            plot.setRenderer(1, splinerenderer2);
            plot.setRangeAxis(1, new NumberAxis("US$"));
    
            plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
            plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis
    
            JFreeChart chart = new JFreeChart(result.title,
                    new Font("Serif", java.awt.Font.BOLD, 18), plot, true);
    
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(400, 300));
            chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            chartPanel.setBackground(Color.white);
            
            MainUI.getInstance().getWest().add(chartPanel);
        }
    }
    

