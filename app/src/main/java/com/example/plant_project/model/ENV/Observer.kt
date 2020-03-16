package com.example.plant_project.model.ENV

interface Observer {

    fun update(
        sensor: Sensor?,
        temp: Double?,
        soil: Double?,
        hum: Double?,
        second: Int?,
        hour: Int?,
        minute: Int?
    ){

    }


}