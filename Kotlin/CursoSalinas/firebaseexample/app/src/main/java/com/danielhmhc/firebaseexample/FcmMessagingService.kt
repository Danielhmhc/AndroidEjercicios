package com.danielhmhc.firebaseexample

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FcmMessagingService : FirebaseMessagingService() {

    lateinit var CHANNEL_ID:String
    val DESCUENTO:String = "descuento"

    override fun onMessageReceived(rM: RemoteMessage?) {
        super.onMessageReceived(rM)

        if(rM != null){
            if(rM.data.size>0){
                mostrarNotification(rM)
            }
        }
    }

    private fun mostrarNotification(rM: RemoteMessage?) {
        var desc = rM!!.data.get(DESCUENTO).toString().toDouble()
        Log.d("Mensaje","Descuento = $desc")

        CHANNEL_ID= getString(R.string.CHANNEL_ID)
        val intent = Intent(this,FcmActivity::class.java)
        intent.putExtra(DESCUENTO,desc)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) //Limpia la notificacion

        val pendingIntent = PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_ONE_SHOT) //El request code sirve para definir las notificaciones, REVISAR EL TEMA DE LAS PENDING INTENTS FLAGS,

        val sonidoNotificacion = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION) as Uri

        val builder = NotificationCompat.Builder(this , CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_carrito)
            .setContentTitle("Titulo de la notificacion")
            .setContentText("Oferta descuento en producto")
            .setAutoCancel(true) //Para que cuando la toque el usuario desaparecza
            .setSound(sonidoNotificacion)
            .setContentIntent(pendingIntent) // para que se abra nuestra activity cuando se toque la notificacion

        //CAMBIAR COLOR DEPENDIENDO EL DESCUENTO

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder.color =
                if (desc >= 50)
                    ContextCompat.getColor(applicationContext, R.color.colorPrimary)
                else
                    ContextCompat.getColor(applicationContext, R.color.colorAccent)
        }
        createNotificationChannel() //creamos el canal , Para versiones de 8 en adelante es necesario crear el canal para  mostrar las notificaciones
        with(NotificationManagerCompat.from(this)){
            notify(0,builder.build())  // Aqui debe de lanzar una notificacion en el carrito, si lo hace
        }

    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager //se importo el android content , los contextos de firebase suelen correr en segundo plano
            notificationManager.createNotificationChannel(channel)
        }

        /*
        Bandeja del sistema: las notificaciones son manejadas por este componente , se necesita describir mediante codigo para que
         */
    }


}
