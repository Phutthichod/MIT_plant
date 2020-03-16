package com.example.plant_project.model.LogENV

import android.widget.TextView

class CurrentLogENV:
    ObserverLog {

    public var temp = 0.00
    override fun update(
        sensorItemLogs: MutableList<SensorItemLog>
    ) {

    }

}