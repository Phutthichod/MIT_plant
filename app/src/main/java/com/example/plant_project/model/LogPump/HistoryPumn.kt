package com.example.plant_project.model.LogPump

import android.util.Log
import android.widget.TextView

class HistoryPumn (private var logpumn:TextView): ObserverPumn {
    override  fun update(pumpLog:MutableList<PumnItem>){
        Log.d("pppppppppppppppppppp",pumpLog.get(0).getHourStart().toString())
        logpumn.text = pumpLog.get(0).getHourStart().toString()
    }
}