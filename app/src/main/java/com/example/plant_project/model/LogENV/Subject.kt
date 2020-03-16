package com.example.plant_project.model.LogENV

import com.example.plant_project.model.ENV.Observer

interface Subject {
     fun registerObserver(observer: ObserverLog)
     fun deleteObserver(observer: ObserverLog)
    fun notifyObserver()
}