package com.example.plant_project.model.LogENV

import android.app.Activity
import android.graphics.Color
import android.util.Log
import co.ceryle.segmentedbutton.SegmentedButton
import com.jjoe64.graphview.DefaultLabelFormatter
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.LegendRenderer
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import java.text.Format
import java.text.SimpleDateFormat
import java.util.*

class CurrentLogENV(private var graph:GraphView,private  var self:Activity):
    ObserverLog {

    public var temp = 0.00
    override fun update(
        sensorItemLogs: MutableList<SensorItemLog>
    ) {
        Log.d("loooooooooooooo","update")
        graph.viewport.isXAxisBoundsManual = true
        graph.viewport.isScalable = true
        graph.viewport.isScrollable = true
        graph.legendRenderer.isVisible = true
        graph.legendRenderer.align = LegendRenderer.LegendAlign.TOP
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getGridLabelRenderer().setLabelFormatter( DateAsXAxisLabelFormatter(self));
        graph.getGridLabelRenderer().setNumHorizontalLabels(4);
//        graph.getViewport().setXAxisBoundsManual(true);
        graph.getGridLabelRenderer().setHumanRounding(false);
        graph.getGridLabelRenderer().setLabelFormatter(object : DefaultLabelFormatter() {
            override fun formatLabel(value: Double, isValueX: Boolean): String {
                if (isValueX) {
                    val formatter: Format = SimpleDateFormat("HH:mm:ss")

                    return formatter.format(value)
                }
                return super.formatLabel(value, isValueX)
            }
        })
        graph.removeAllSeries()
        val series_soil = creatSeries("soil","#fcba03")
        val series_temp = creatSeries("soil","#03fc39")
        val calendar = Calendar.getInstance()
        calendar.set(2020, 3, 22, 5, 30, 2)
        var d1 = calendar.time

        var j = 0
//        calendar.set(2020, 3, 22, 1, 0, 0)
//        d1 = calendar.time
//        graph.getViewport().setMinX(d1.time.toDouble())
//        calendar.set(2020, 3, 22, 23, 30, 0)
//        graph.getViewport().setMaxX(d1.time.toDouble())
        for (i in sensorItemLogs) {

            calendar.set(2020, 3, 22, i.getHour()!!, i.getMinute()!!, i.getSecond()!!)
            d1 = calendar.time
            series_soil.appendData(DataPoint(d1,i.getSoil()!!.toDouble()),true,100)
            series_temp.appendData(DataPoint(d1,i.getTemp()!!.toDouble()),true,100)
//            if(j == 0)  graph.getViewport().setMinX(d1.time.toDouble());
//            if(j == sensorItemLogs.size-1)  graph.getViewport().setMaxX(d1.time.toDouble());
            j++
        }
        calendar.set(2020, 3, 22, 24, 0, 0)
        d1 = calendar.time
        graph.getViewport().setMaxX(d1.time.toDouble());
        calendar.set(2020, 3, 22, 1, 0, 0)
        d1 = calendar.time
        graph.getViewport().setMinX(d1.time.toDouble());
        graph.addSeries(series_soil)
//        graph.addSeries(series_temp)
     }
    fun creatSeries(title:String,color: String):LineGraphSeries<DataPoint>{
        lateinit var series: LineGraphSeries<DataPoint>
        series = LineGraphSeries<DataPoint>()
        series.backgroundColor = Color.argb(40, 194, 235, 205)
        series.color = Color.parseColor(color)
        series.isDrawDataPoints = true
        series.setAnimated(true)
        series.isDrawBackground = true
        series.title = title
//        series.appendData(DataPoint(0.0, 0.0), true, 100)
        return series
    }

}