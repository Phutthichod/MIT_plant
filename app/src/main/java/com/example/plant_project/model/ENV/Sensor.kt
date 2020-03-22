package com.example.plant_project.model.ENV

class Sensor(
        private var date:String? = null){
        fun getDate(): String? {
                return date
        }
        fun setDate(date:String){
                this.date = date
        }

}