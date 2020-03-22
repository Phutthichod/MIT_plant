package com.example.plant_project

import android.annotation.TargetApi
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
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.jjoe64.graphview.DefaultLabelFormatter
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.LegendRenderer
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter
import com.mikhaellopez.circularprogressbar.CircularProgressBar

import kotlinx.android.synthetic.main.activity_main.*
import java.text.Format
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


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
        val formatter = DateTimeFormatter.ofPattern("d-M-yyyy")
//        val formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss")
        val formatted = date.format(formatter)
        val sensor = Sensor(formatted)
        val cENV = CurrentENV(tempC,temp, humC,hum,soil, soilC)
        val sensor1 = SensorItem(sensor)
        sensor1.registerObserver(cENV)
        sensor1.waitSensorUpdate()
//        Log.d("date",dateUpdate)

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
        graph.viewport.isXAxisBoundsManual = true
        graph.viewport.isScalable = true
        graph.viewport.isScrollable = true
        graph.legendRenderer.isVisible = true
        graph.legendRenderer.align = LegendRenderer.LegendAlign.TOP
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getGridLabelRenderer().setLabelFormatter( DateAsXAxisLabelFormatter(this));
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
        val calendar = Calendar.getInstance()
        var d1 = calendar.time
        calendar.set(2020, 3, 22, 24, 0, 0)
        d1 = calendar.time
        graph.getViewport().setMaxX(d1.time.toDouble());
        calendar.set(2020, 3, 22, 1, 0, 0)
        d1 = calendar.time
        graph.getViewport().setMinX(d1.time.toDouble());


        val eLog = SensorLog(formatted)
        val cLog = CurrentLogENV(graph,this@MainActivity)
        eLog.registerObserver(cLog)
        eLog.waitSensorUpdate()
        val segment = findViewById<SegmentedButtonGroup>(R.id.segmentgroup);
//        var x:Double = 0.0
        segment.setOnClickedButtonPosition { i ->
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("control")
            myRef.setValue(i+1)
        }



//        val pLog = PumnLog("19-3-2020")
//        val hPumn = HistoryPumn(graph,this)
//        pLog.registerObserver(hPumn)
//        pLog.waitSensorUpdate()

        val database = FirebaseDatabase.getInstance()
        val dateRef =  database.getReference("date")
        var dateUpdate:String? = ""
        dateRef.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dateUpdate = dataSnapshot.getValue(String::class.java)
                eLog.setDate(dateUpdate!!)
            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
            }
        })
        val timeRef = database.getReference("time")
        timeRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                var time: String? = dataSnapshot.getValue(String::class.java)
                dateText.text = "update $dateUpdate $time "

            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
            }
        })





    }
}
