package com.itext.test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.itext.constants.PDFConstants;



 
public class PieChartDemo {
    public static void main(String[] args) {
        //TODO: Add code to generate PDFs with charts
    }
 
    public static JFreeChart generatePieChart(JSONArray IndividualPDFChart) {
        DefaultPieDataset dataSet = new DefaultPieDataset();

		for(int i=0;i<IndividualPDFChart.size();i++){
			
			JSONObject jsonChart =IndividualPDFChart.getJSONObject(i); 
			dataSet.setValue(jsonChart.getString(PDFConstants.PieData1), Double.parseDouble(jsonChart.getString(PDFConstants.PieData2)));
		}
        JFreeChart chart = ChartFactory.createPieChart(
        		PDFConstants.PieChartTitle, dataSet, true, true, false);
//        PiePlot3D plot = (PiePlot3D) chart.getPlot();
//        plot.setStartAngle(290);
//        plot.setDirection(Rotation.CLOCKWISE);
//        plot.setForegroundAlpha(0.5f);
//       
        return chart;
    }
 
    public static JFreeChart generateBarChart(JSONArray IndividualPDFChart) {
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        for(int i=0;i<IndividualPDFChart.size();i++){
			
			JSONObject jsonChart =IndividualPDFChart.getJSONObject(i); 
			dataSet.setValue(Double.parseDouble(jsonChart.getString(PDFConstants.PieData2)),PDFConstants.BarChartBarTitle, jsonChart.getString(PDFConstants.PieData1));
		}
       
        JFreeChart chart = ChartFactory.createBarChart(
        		PDFConstants.BarChartTitle, PDFConstants.BarChartXTitle,PDFConstants.BarChartYTitle,
                dataSet, PlotOrientation.VERTICAL, false, true, false);
 
        return chart;
    }
}

