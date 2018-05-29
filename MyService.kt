package com.kavan.element.kotlinmusicplayer

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.os.IBinder
import java.text.SimpleDateFormat
import java.util.*

class MyService : Service() {

    private val myBinder = MyLocalBinder()

    override fun onBind(intent: Intent): IBinder? {
        return myBinder
    }

    fun getCurrentTime(): String {
        val dateformat = SimpleDateFormat("HH:mm:ss MM/dd/yyyy",
                Locale.US)
        return dateformat.format(Date())
    }

    inner class MyLocalBinder : Binder() {
        fun getService() : MyService {
            return this@MyService
        }
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        // Send a notification that service is started
        toast("Service started.")
        return Service.START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        toast("Service destroyed.")
        stopForeground(true)
    }


    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        stopSelf()
    }
}