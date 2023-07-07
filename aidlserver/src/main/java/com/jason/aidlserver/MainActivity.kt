package com.jason.aidlserver

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var tvMsg :TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvMsg = findViewById(R.id.tv_msg)

        bindService(Intent(this,AidlServerService::class.java),conn,Context.BIND_AUTO_CREATE)
    }

    private val conn = object :ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            tvMsg.text = "onServiceConnected"
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            tvMsg.text = "onServiceDisconnected"
        }

    }

    override fun onDestroy() {
        unbindService(conn)
        super.onDestroy()
    }
}