package com.weightcontrol.activity;

import com.weightcontrol.R;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.series.XYSeries;
import com.androidplot.xy.*;
 
import java.text.DecimalFormat;
import java.util.Arrays;

public class PlotActivity extends Activity 
{
	  private XYPlot mySimpleXYPlot;
	  
	    @Override
	    public void onCreate(Bundle savedInstanceState)
	    {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.plot);
	 
	        mySimpleXYPlot = (XYPlot) findViewById(R.id.mySimpleXYPlot);
	 
	        Number[] series1Numbers = {1, 8, 5, 2, 7, 4};
	        Number[] series2Numbers = {4, 6, 3, 8, 2, 10};
	 
	        XYSeries series1 = new SimpleXYSeries(
	                Arrays.asList(series1Numbers), 
	                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,
	                "Series1"); 
	 
	        XYSeries series2 = new SimpleXYSeries(Arrays.asList(series2Numbers)
	        		, SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series2");
	 
	        LineAndPointFormatter series1Format = new LineAndPointFormatter(
	                Color.rgb(0, 200, 0),                   // Linea
	                Color.rgb(0, 100, 0),                   // Punto
	                Color.rgb(150, 190, 150));              // Relleno
	 
	        mySimpleXYPlot.addSeries(series1, series1Format);
	 
	        mySimpleXYPlot.addSeries(series2, new LineAndPointFormatter(Color.rgb(0, 0, 200), 
	        		Color.rgb(0, 0, 100), Color.rgb(150, 150, 190)));
	 
	    }
	
}
