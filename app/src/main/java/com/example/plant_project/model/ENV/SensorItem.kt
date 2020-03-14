package com.example.plant_project.model.ENV

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SensorItem(private var sensor: Sensor? = null):
    Subject {
    private var hum: Double? = null
    private var temp: Double? = null
    private var second: Int? = null
    private var hour: Int? = null
    private var minute: Int? = null
    private  var observer: MutableList<Observer> =  ArrayList()

    fun getSensor(): Sensor? {
        return this.sensor
    }
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

    override fun registerObserver(observer: Observer) {
        this.observer.add(observer)
    }

    override fun deleteObserver(observer: Observer) {
        val i: Int? = this.observer.indexOf(observer)
        if (i != null) {
            if(i > 0) {
                this.observer.removeAt(i)
            }
        }
    }
    fun envChange(){
        notifyObserver()
    }

    override fun notifyObserver() {
        var i = 0
        while (i < this.observer.size){
            val observer = this.observer[i]
            observer.update(sensor,temp,hum,second,hour,minute)
            i++
        }
    }
    fun waitSensorUpdate(){
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("env")
        Log.d("jjjjjjjjjjjjjjj", "ssssssssssssssssssss")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                hum = dataSnapshot.child("humidity").getValue(Double::class.java)
                temp = dataSnapshot.child("temperature").getValue(Double::class.java)
                var s:List<String> = dataSnapshot.child("time").getValue(String::class.java)!!.split(':')
                hour = s.get(0).toInt()
                minute = s.get(1).toInt()
                second = s.get(2).toInt()
                envChange()
            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("jjjjjjjjjjjjjjj", "Failed to read value.", error.toException())
            }
        })

    }
    fun setENV(sensor: Sensor?, temp: Double?, hum: Double?, second: Int?, minute: Int?, hour: Int?){
        this.sensor = sensor
        this.temp = temp
        this.hum = hum
        this.temp = temp
        this.second = second
        this.minute = minute
        this.hour = hour
        envChange()
    }
    fun getOb(): Int? {
        return this.observer.size
    }
}