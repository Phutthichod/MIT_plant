package com.example.plant_project.model.LogPump

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class PumnLog( private var date:String? = null,private var pumpLog: MutableList<PumnItem> = ArrayList()
               , private  var observerpumn: MutableList<ObserverPumn> =  ArrayList()) : SubjectLog {
    override fun registerObserver(observer: ObserverPumn) {
        this.observerpumn.add(observer)
    }

    override fun deleteObserver(observer: ObserverPumn) {
        val i: Int? = this.observerpumn.indexOf(observer)
        if (i != null) {
            if(i > 0) {
                this.observerpumn.removeAt(i)
            }
        }
    }
    fun envChange(){
        notifyObserver()
    }
    override fun notifyObserver() {
        var i = 0
        while (i < this.observerpumn.size){
            val observer = this.observerpumn[i]
            observer.update(pumpLog)
            i++
        }
    }
    fun waitSensorUpdate(){
        pumpLog.clear()
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("log_pump/"+date)
        Log.d("jjjjjjjjjjjjjjj", "ssssssssssssssssssss")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for(data in dataSnapshot.children){
                    val start:List<String> = data.child("start").getValue(String::class.java)!!.split(':')
                    val stop:List<String> = data.child("stop").getValue(String::class.java)!!.split(':')
                    val   hour_start = start.get(0).toInt()
                    val   minute_start = start.get(1).toInt()
                    val  second_start = start.get(2).toInt()
                    val   hour_stop = stop.get(0).toInt()
                    val   minute_stop = stop.get(1).toInt()
                    val  second_stop = stop.get(2).toInt()
                    Log.d("Listttttttttt",start.toString())
                    val pumnItem = PumnItem(hour_start,minute_start,second_start,hour_stop,minute_stop,second_stop)
                    pumpLog.add(pumnItem)
                }
                envChange()
            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("jjjjjjjjjjjjjjj", "Failed to read value.", error.toException())
            }
        })

    }
}