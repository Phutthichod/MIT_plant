package com.example.plant_project

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.plant_project.model.ENV.CurrentENV
import com.example.plant_project.model.ENV.Sensor
import com.example.plant_project.model.ENV.SensorItem
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
        var date = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formatted = date.format(formatter)
        val sensor = Sensor(formatted)
        val cENV = CurrentENV(
            temp,
            hum,
            time,
            text_date
        )
        val sensor1 = SensorItem(sensor)
        sensor1.registerObserver(cENV)
        sensor1.waitSensorUpdate()
//        pump
        val pump = Pump()
        val CP = CurrentPumn(text_pump)
        pump.registerObserver(CP)
        pump.waitSensorUpdate()



    }
}
