package com.example.plant_project

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.plant_project.model.ENV.CurrentENV
import com.example.plant_project.model.ENV.Sensor
import com.example.plant_project.model.ENV.SensorItem
import com.example.plant_project.model.LogENV.CurrentLogENV
import com.example.plant_project.model.LogENV.SensorLog
import com.example.plant_project.model.LogPump.HistoryPumn
import com.example.plant_project.model.LogPump.PumnLog
import com.example.plant_project.model.Pump.CurrentPumn
import com.example.plant_project.model.Pump.Pump
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class MainActivity : AppCompatActivity() {

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val date = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val formatted = date.format(formatter)
        val sensor = Sensor(formatted)
        val cENV = CurrentENV(temp, hum, time, text_date)
        val sensor1 = SensorItem(sensor)
        sensor1.registerObserver(cENV)
        sensor1.waitSensorUpdate()
//        pump
        val pump = Pump()
        val CP = CurrentPumn(text_pump)
        pump.registerObserver(CP)
        pump.waitSensorUpdate()

        val pLog = PumnLog("14-3-2020")
        val hPumn = HistoryPumn(logpumn)
        pLog.registerObserver(hPumn)
        pLog.waitSensorUpdate()

        val eLog = SensorLog("14-3-2020")
        val cLog = CurrentLogENV()
        eLog.registerObserver(cLog)
        eLog.waitSensorUpdate()





    }
}
