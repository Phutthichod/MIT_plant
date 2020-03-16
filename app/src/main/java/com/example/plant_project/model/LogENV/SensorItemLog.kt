package com.example.plant_project.model.LogENV

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SensorItemLog (private var hum: Double? = null,
         private var soil: Double? = null,
        private var temp: Double? = null,
        private var hour: Int? = null,
        private var minute: Int? = null,
        private var second: Int? = null
        ){


    fun getHum(): Double? {
        return this.hum
    }
    fun getTemp(): Double? {
        return this.temp
    }
    fun getSecond(): Int? {
        return this.second
    }
    fun getHour(): Int? {
        return this.hour
    }
    fun getMinute(): Int? {
        return this.minute
    }
    fun getSoil(): Double? {
        return this.soil
    }


}