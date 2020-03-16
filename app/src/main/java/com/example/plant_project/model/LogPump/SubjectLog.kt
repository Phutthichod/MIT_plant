package com.example.plant_project.model.LogPump

import com.example.plant_project.model.LogENV.ObserverLog

interface SubjectLog{
    fun registerObserver(observer: ObserverPumn)
    fun deleteObserver(observer: ObserverPumn)
    fun notifyObserver()
}