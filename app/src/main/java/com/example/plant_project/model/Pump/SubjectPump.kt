package com.example.plant_project.model.Pump

import com.example.plant_project.model.Pump.ObserverPump

interface SubjectPump {
     fun registerObserver(observer: ObserverPump)
     fun deleteObserver(observer: ObserverPump)
    fun notifyObserver()
}