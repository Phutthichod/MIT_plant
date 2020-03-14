package com.example.plant_project.model.Pump

import android.widget.TextView

class CurrentPumn(private var textPump:TextView) : ObserverPump{
    override fun update(status: Boolean) {
        if(status)
            textPump.text = "เปิด"
        else
            textPump.text = "ปิด"
    }
}