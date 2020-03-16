package com.example.plant_project.model.ENV

import android.util.Log
import android.widget.TextView

class CurrentENV(private var textTmp: TextView,private var textHum: TextView,private var textTime: TextView,private var textDate: TextView):
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
        if (temp != null) {
           textTmp.text = temp.toString()
            textHum.text = hum.toString()
            val time = "update "+hour.toString()+":"+minute.toString()+":"+second.toString()
            textTime.text = time
            textDate.text = sensor!!.getDate()
        }
    }

}