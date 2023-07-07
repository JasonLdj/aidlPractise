package com.jason.aidlserver

import android.app.Service
import android.content.Intent
import android.os.IBinder

class AidlServerService :Service(){

    private var aS :String?  = "empty"

   private   val mBinder = object: IMyAidlInterface.Stub (){

        override fun basicTypes(
            anInt: Int,
            aLong: Long,
            aBoolean: Boolean,
            aFloat: Float,
            aDouble: Double,
            aString: String?
        ) {

            aS = aString

        }

        override fun getInfo(): String {

            return  " RemoteService保存数据 => $aS"
        }
    }

    override fun onBind(intent: Intent?): IBinder {
         return mBinder
    }
}