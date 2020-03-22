package com.example.plant_project.model.ENV

import android.util.Log
import android.widget.TextView
import com.mikhaellopez.circularprogressbar.CircularProgressBar

class CurrentENV(private var progressTmp: CircularProgressBar,private var textTmp:TextView, private var progressHum: CircularProgressBar,private var textHum:TextView
                 ,private var textSoil:TextView, private var progressSoil: CircularProgressBar):
    Observer {

    companion object{
        public var listSensor: MutableList<SensorItem> = ArrayList()
    }
    public var temp = 0.00
    override fun update(
        sensor: Sensor?,
        temp: Double?,
        soil: Double?,
        hum: Double?,
        hour: Int?,
        minute: Int?,
        second: Int?
    ) {
        Log.d("update",temp.toString())
        if (temp != null) {
            progressTmp.apply {
                progress = 40f
                setProgressWithAnimation(temp.toFloat(), 1500) // =1.5s
                progressMax = 50f
            }
            textTmp.text = temp.toString()
            progressHum.apply {
                progress = 40f
                setProgressWithAnimation(hum!!.toFloat(), 1500) // =1.5s
                progressMax = 40f
            }
            textHum.text = hum.toString()
            progressSoil.apply {
                progress = 40f
                setProgressWithAnimation(soil!!.toFloat(), 1500) // =1.5s
                progressMax = 40f
            }
            textSoil.text = soil.toString()
//            textHum.text = hum.toString()
//            val time = "update "+hour.toString()+":"+minute.toString()+":"+second.toString()
//            textTime.text = time
//            textDate.text = sensor!!.getDate()
        }
    }

}