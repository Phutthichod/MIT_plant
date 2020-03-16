package com.example.plant_project.model.LogPump

class PumnItem (

                private var hour_start: Int? = null,
                private var minute_start: Int? = null,
                private var second_start: Int? = null,
                private var second_stop: Int? = null,
                private var hour_stop: Int? = null,
                private var minute_stop: Int? = null
               ){
    fun getHourStart():Int?{
        return hour_start
    }
    fun getHourStop():Int?{
        return hour_stop
    }
    fun getMinuterStart():Int?{
        return minute_start
    }
    fun getMinuteStop():Int?{
        return minute_stop
    }
    fun getSecondStart():Int?{
        return second_start
    }
    fun getSecondStop():Int?{
        return second_stop
    }


}