package com.banane.alarm;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {

	private static final String TAG = "BANANEALARM";
	private static final int NOTIFICATION_ID = 1;
	Intent intent;
	PendingIntent pendingIntent;
	NotificationManager notificationManager;

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d(TAG, "in onreceive");
		 Intent service1 = new Intent(context, AlarmService.class);
	      context.startService(service1);
		
	}

}
