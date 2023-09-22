package com.example.aidldemo

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView

    private var mathService: IPlus? = null
    private val connection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            mathService = IPlus.Stub.asInterface(service)
            val result = mathService?.add(1, 2)
            textView.text = result.toString()
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            mathService = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("LinLi", "onCreate: ");
        setContentView(R.layout.activity_main)
        bindService()
        textView = findViewById(R.id.textView)
    }

    private fun bindService() {
        val intent = Intent(this, MathService::class.java)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(connection)
    }
}