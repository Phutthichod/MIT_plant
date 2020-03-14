package com.example.plant_project.model.ENV

import com.example.plant_project.model.ENV.Observer

interface Subject {
     fun registerObserver(observer: Observer)
     fun deleteObserver(observer: Observer)
    fun notifyObserver()
}