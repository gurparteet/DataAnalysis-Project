package viewsModule;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import resultModule.Result;
import uiModule.MainUI;

public class LineChart implements View{
    


    public LineChart(){

    }

    public void draw(Result result) {
        XYSeriesCollection dataset = new XYSeriesCollection();

        for(int i=0; i < result.resultData.size(); i++){
                XYSeries series1 = new XYSeries(result.categoriesList[i]);
                result.resultData.get(i).forEach((key,value) -> {
                        series1.add(Integer.parseInt(key),value);
                });
                dataset.addSeries(series1);
        }
                

        JFreeChart chart = ChartFactory.createXYLineChart(result.title, "Year", "", dataset,
                PlotOrientation.VERTICAL, true, true, false);

        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(
                new TextTitle(result.title, new Font("Serif", java.awt.Font.BOLD, 18)));

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(400, 300));
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        
        MainUI.getInstance().getWest().add(chartPanel);

        }
    }

