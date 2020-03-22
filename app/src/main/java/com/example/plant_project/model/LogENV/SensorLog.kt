package com.example.plant_project.model.LogENV

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SensorLog(
        private var date:String? = null,private var sensorItemLogs: MutableList<SensorItemLog> = ArrayList()
        , private  var observerlog: MutableList<ObserverLog> =  ArrayList()): Subject{
        fun getDate(): String? {
                return date
        }

        override fun registerObserver(observerlog: ObserverLog) {
                var a = this.observerlog.add(observerlog)

        }
        fun envChange(){
                notifyObserver()
        }
        fun setDate(date:String){
                Log.d("update date",date)
                if(date != this.date){

                        this.date = date
                        waitSensorUpdate()
                }

        }

        override fun notifyObserver() {

                var i = 0
                Log.d("register",this.observerlog.size.toString())
                while (i < this.observerlog.size){
                        val observer = this.observerlog[i]
                        Log.d("notify","update")
                        observer.update(sensorItemLogs)
                        i++
                }
        }
        fun waitSensorUpdate(){
                val database = FirebaseDatabase.getInstance()
                val myRef = database.getReference("log_env/"+date)
                Log.d("jjjjjjjjjjjjjjj", "ssssssssssssssssssss")
                myRef.addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                                sensorItemLogs.clear()
                                for(data in dataSnapshot.children){
                                        val hum = data.child("humidity").getValue(Double::class.java)
                                        val temp = data.child("temperature").getValue(Double::class.java)
                                        val soil = data.child("soil").getValue(Double::class.java)
                                        val s:List<String> = data.child("time").getValue(String::class.java)!!.split(':')
                                        val   hour = s.get(0).toInt()
                                        val   minute = s.get(1).toInt()
                                        val  second = s.get(2).toInt()
                                        Log.d("ENVLog",hum.toString())
                                        val sensorItem = SensorItemLog(hum,soil,temp,hour,minute,second)
                                        sensorItemLogs.add(sensorItem)
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