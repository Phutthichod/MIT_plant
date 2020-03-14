package com.example.plant_project.model.ENV

import android.widget.TextView

class CurrentENV(private var textTmp: TextView,private var textHum: TextView,private var textTime: TextView,private var textDate: TextView):
    Observer {


    public var temp = 0.00
    override fun update(
        sensor: Sensor?,
        temp: Double?,
        hum: Double?,
        second: Int?,
        hour: Int?,
        minute: Int?
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