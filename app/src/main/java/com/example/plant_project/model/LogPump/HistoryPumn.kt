package com.example.plant_project.model.LogPump

import android.app.Activity
import android.util.Log
import android.widget.TextView
import com.jjoe64.graphview.GraphView

class HistoryPumn (private var graph:GraphView,private  var self:Activity): ObserverPumn {
    override  fun update(pumpLog:MutableList<PumnItem>){
//        Log.d("pppppppppppppppppppp",pumpLog.get(0).getHourStart().toString())
//        logpumn.text = pumpLog.get(0).getHourStart().toString()
    }
}