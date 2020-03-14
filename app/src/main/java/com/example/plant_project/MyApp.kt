package com.example.plant_project

import android.app.Application
import com.google.firebase.database.FirebaseDatabase

internal class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
    }
}