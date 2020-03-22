package com.example.plant_project

import android.annotation.TargetApi
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import co.ceryle.segmentedbutton.SegmentedButtonGroup
import com.example.plant_project.model.ENV.CurrentENV
import com.example.plant_project.model.ENV.Sensor
import com.example.plant_project.model.ENV.SensorItem
import com.example.plant_project.model.LogENV.CurrentLogENV
import com.example.plant_project.model.LogENV.SensorLog
import com.google.firebase.database.FirebaseDatabase
import com.jjoe64.graphview.DefaultLabelFormatter
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.LegendRenderer
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import com.mikhaellopez.circularprogressbar.CircularProgressBar

import kotlinx.android.synthetic.main.activity_main.*
import java.text.Format
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.time.hours


class MainActivity : AppCompatActivity() {

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val tempC = findViewById<CircularProgressBar>(R.id.circularProgressBar1)
        val humC = findViewById<CircularProgressBar>(R.id.circularProgressBar2)
        val soilC = findViewById<CircularProgressBar>(R.id.circularProgressBar3)
//        val segment = findViewById<SegmentedButtonGroup>(R.id.segmentgroup);
//        segment.setOnClickedButtonPosition { i ->
//            Toast.makeText(applicationContext,"XXXXX !! $i", Toast.LENGTH_SHORT).show()
//        }
        val date = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
//        val formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss")
        val formatted = date.format(formatter)
        val sensor = Sensor(formatted)
        val cENV = CurrentENV(tempC,temp, humC,hum,soil, soilC)
        val sensor1 = SensorItem(sensor)
        sensor1.registerObserver(cENV)
        sensor1.waitSensorUpdate()
//        pump
//        val pump = Pump()
//        val CP = CurrentPumn(text_pump)
//        pump.registerObserver(CP)
//        pump.waitSensorUpdate()
//
//        val pLog = PumnLog("14-3-2020")
//        val hPumn = HistoryPumn(logpumn)
//        pLog.registerObserver(hPumn)
//        pLog.waitSensorUpdate()
//


        var graph : GraphView = findViewById(R.id.graph) as GraphView


        val segment = findViewById<SegmentedButtonGroup>(R.id.segmentgroup);
//        var x:Double = 0.0
        segment.setOnClickedButtonPosition { i ->
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("control")
            myRef.setValue(i+1)
        }

        val eLog = SensorLog("19-3-2020")
        val cLog = CurrentLogENV(graph,this)
        eLog.registerObserver(cLog)
        eLog.waitSensorUpdate()
    }
}
