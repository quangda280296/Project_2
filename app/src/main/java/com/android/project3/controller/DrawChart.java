package com.android.project3.controller;

import android.content.Context;
import android.graphics.Color;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by keban on 5/3/2017.
 */

public class DrawChart {

    Context context;
    List<String> date;
    List<Integer> data;
    int max;

    public DrawChart(Context c, List<String> X, List<Integer> Y)
    {
        if(X.size() == Y.size())
        {
            context = c;
            convert(X, Y);
            max = getMax(data);
        }
    }

    public GraphicalView Draw()
    {
        // Now we create the renderer
        XYSeriesRenderer renderer = new XYSeriesRenderer();
        renderer.setDisplayChartValues(true);
        renderer.setDisplayChartValuesDistance(1);
        renderer.setChartValuesTextSize(30);
        renderer.setChartValuesSpacing(30);
        renderer.setLineWidth(2);
        renderer.setColor(Color.RED);
        // Include low and max value
        renderer.setDisplayBoundingPoints(true);
        // we add point markers
        renderer.setPointStyle(PointStyle.CIRCLE);
        renderer.setPointStrokeWidth(3);

        XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
        mRenderer.setZoomEnabled(false,false);
        mRenderer.setLabelsColor(context.getResources().getColor(android.R.color.holo_red_light));
        mRenderer.setLabelsTextSize(15);
        mRenderer.addSeriesRenderer(renderer);
        // We want to avoid black border
        // transparent margins
        mRenderer.setMarginsColor(Color.argb(0x00, 0xff, 0x00, 0x00));
        // Disable Pan on two axis
        mRenderer.setPanEnabled(false, false);
        mRenderer.setShowGrid(true); // we show the grid

//        mRenderer.setXAxisMin(0);
//        mRenderer.setXAxisMax(this.date.size());
        mRenderer.setXAxisColor(context.getResources().getColor(android.R.color.holo_red_light));
        mRenderer.setXLabelsColor(context.getResources().getColor(android.R.color.holo_red_light));
        mRenderer.setXLabelsPadding(5);

        mRenderer.setYAxisMin(0);
        mRenderer.setYAxisMax(this.max + 5);
        mRenderer.setYAxisColor(context.getResources().getColor(android.R.color.holo_red_light));

        mRenderer.setXLabels(0);

        XYSeries series = new XYSeries("Quãng đường của bạn (km)");

        for(int i = 1; i <= this.data.size(); i++)
        {
            series.add(i, this.data.get(i-1));
            mRenderer.addXTextLabel(i, this.date.get(i-1));
        }

        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        dataset.addSeries(series);

        GraphicalView chartView = ChartFactory.getLineChartView(context, dataset, mRenderer);
        return chartView;
    }

    public int getMax(List<Integer> param)
    {
        int maxNumber = 0;
        if(param != null)
        {
            if(param.size() > 0)
            {
                maxNumber = param.get(0);
                for(int p : param)
                {
                    if(p > maxNumber)
                        maxNumber = p;
                }
            }
        }
        return maxNumber;
    }

    public void convert(List<String> X, List<Integer> Y)
    {
        if(X.size() > 10)
        {
            date = new ArrayList<>();
            data = new ArrayList<>();
            int size = X.size();

            this.date.add(X.get(0));
            this.data.add(Y.get(0));

            for(int i = 0; i < 4; i++)
            {
                this.date.add(X.get(i));
                this.data.add(Y.get(i));
            }

            for(int i = size - 5; i < size - 1; i--)
            {
                this.date.add(X.get(i));
                this.data.add(Y.get(i));
            }

            this.date.add(X.get(size - 1));
            this.data.add(Y.get(size - 1));
        }
        else
        {
            this.date = X;
            this.data = Y;
        }
    }
}
