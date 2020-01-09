package com.danielhmhc.firebaseexample

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessaging

class FcmActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fcm)

        var descuento = intent.getDoubleExtra("descuento",0.0)
        Toast.makeText(this,"Descuento $descuento",Toast.LENGTH_LONG).show()
    }

    fun suscribirTopic(view: View) {
        var tag:String = view.tag.toString()
        Log.d("Mensaje",tag)
        if (view is CheckBox) {
            if (view.isChecked)
                FirebaseMessaging.getInstance().subscribeToTopic(tag)
            else {
                FirebaseMessaging.getInstance().unsubscribeFromTopic(tag)
            }
        }
    }

}
