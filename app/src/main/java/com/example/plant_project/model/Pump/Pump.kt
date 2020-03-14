package com.example.plant_project.model.Pump

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Pump: SubjectPump {
    private var status: Boolean = false
    private  var observer: MutableList<ObserverPump> =  ArrayList()
    fun getStatus(): Boolean {
        return status
    }

    override fun registerObserver(observer: ObserverPump) {
        this.observer.add(observer)
    }

    override fun deleteObserver(observer: ObserverPump) {
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
            observer.update(status)
            i++
        }
    }
    fun waitSensorUpdate(){
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("pump")
        Log.d("jjjjjjjjjjjjjjj", "ssssssssssssssssssss")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue(Boolean::class.java)
                status = value!!
                envChange()
            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("jjjjjjjjjjjjjjj", "Failed to read value.", error.toException())
            }
        })
    }
}