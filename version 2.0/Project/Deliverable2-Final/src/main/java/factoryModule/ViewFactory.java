package factoryModule;

import viewsModule.BarChart;
import viewsModule.LineChart;
import viewsModule.PieChart;
import viewsModule.Report;
import viewsModule.ScatterPlot;
import viewsModule.TimeSeriesChart;
import viewsModule.View;

public class ViewFactory {

    /**
     * This method is used to generate a View object class (Factory design pattern)
     * @param key- The input key of the view is required (e.g. "BarChart")
     * @return
     */

    public View generateView(String key){
        View view = null;
        switch(key){
            case "Report": view = new Report();
            break;
            case "Pie Chart": view = new PieChart();
            break;
            case "Line Chart": view = new LineChart();
            break;
            case "Scatter Chart": view = new ScatterPlot();
            break;
            case "Bar Chart": view = new BarChart();
            break;
            case "Time Series": view = new TimeSeriesChart();
            break;
            default: throw new IllegalArgumentException("Unknown Key " + key);
        }
        return view;

    }
}
