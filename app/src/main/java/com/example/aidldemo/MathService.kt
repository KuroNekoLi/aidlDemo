package com.example.aidldemo

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MathService : Service() {

    private val binder = object : IPlus.Stub() {
        override fun add(a: Int, b: Int): Int {
            return a + b
        }
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }
}