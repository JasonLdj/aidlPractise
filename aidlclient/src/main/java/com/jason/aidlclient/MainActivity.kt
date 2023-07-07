package com.jason.aidlclient

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.jason.aidlserver.IMyAidlInterface

class MainActivity : AppCompatActivity() {

    private lateinit var tvMsg :TextView
    private var autoMex :Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvMsg = findViewById(R.id.tv_msg)

        findViewById<Button>(R.id.btn_connect).setOnClickListener {
            val intent = Intent( "jason.aidl.action")
            intent.setClassName("com.jason.aidlserver","com.jason.aidlserver.AidlServerService")
            bindService(intent,connect, Context.BIND_AUTO_CREATE)
        }

        findViewById<Button>(R.id.btn_send).setOnClickListener {
            remoteService?.basicTypes(1,2L,true,1.5F,2.5,"haha ${autoMex ++}")
        }

        findViewById<Button>(R.id.btn_get).setOnClickListener {
           tvMsg.text =  remoteService?.info
        }

        arrayOf("1","2","3").forEach {

        }
      val ret=   arrayOf("1","2","3").joinToString {i:String -> i }
        Log.i("JasonTTTT", "onCreate: $ret")
    }

    private var remoteService  :IMyAidlInterface ? = null

    private val connect = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            remoteService = IMyAidlInterface.Stub.asInterface(service)
            tvMsg.text = "onServiceConnected"
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            remoteService= null
            tvMsg.text = "onServiceDisconnected"
        }
    }

    override fun onDestroy() {
        unbindService(connect)

        super.onDestroy()
    }
}