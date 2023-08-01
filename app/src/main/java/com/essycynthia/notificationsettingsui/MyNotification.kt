package com.essycynthia.notificationsettingsui

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

class MyNotification( var context: Context,  var title: String, var message: String) {
    private var channelID = "FCM20"
    private var channelName = "MyMessage"
    private var notificationManager =
        context.applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    private lateinit var notificationChannel: NotificationChannel
    private lateinit var notificationBuilder: NotificationCompat.Builder
    fun firNotification() {
        val intent = Intent(context,MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_ONE_SHOT)

        if (Build.VERSION_CODES.O <= Build.VERSION.SDK_INT) {
            notificationChannel =
                NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH)
           notificationManager.createNotificationChannel(notificationChannel)

    }
        notificationBuilder = NotificationCompat.Builder(context,channelID)
        notificationBuilder.setSmallIcon(R.drawable.baseline_notifications_none_24)
        notificationBuilder.setContentTitle(title)
        notificationBuilder.addAction(R.drawable.ic_launcher_foreground,"Open App",pendingIntent)
        notificationBuilder.setContentText(message)
        notificationBuilder.setAutoCancel(true)
        notificationBuilder.setVibrate(longArrayOf(1000,1000,1000,1000))
        notificationManager.notify(100,notificationBuilder.build())

}
}