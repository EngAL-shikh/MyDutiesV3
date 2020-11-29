package com.amroz.myduties

import android.app.Application

class TaskApplcation: Application() {

    override fun onCreate() {
        super.onCreate()
        TaskRepository.initialize(this)
    }

}