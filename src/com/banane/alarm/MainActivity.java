package com.banane.alarm;

import java.util.Calendar;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {
	
	private static final String TAG = "BANANEALARM";
	public AlarmManager alarmManager;
	Intent alarmIntent;
	PendingIntent pendingIntent;
	NotificationManager notificationManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		cancelNotifications();		
		setContentView(R.layout.activity_main);

	}
	
	public void setAlarm(View v){
		alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		alarmIntent = new Intent(MainActivity.this, AlarmReceiver.class);
		pendingIntent = PendingIntent.getBroadcast(  MainActivity.this, 0, alarmIntent, 0);
		
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 2);
		  
		Log.d(TAG,"calendar: "+ calendar.getTime());
		alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(),pendingIntent);
	}
	
	@Override 
	protected void onStart(){
		Log.d(TAG, "in on start");
		super.onStart();
		cancelNotifications();
	}
	
	public void cancelNotifications(){
		Log.d(TAG, "in cancelNotifications()");
		notificationManager.cancelAll();
	}

	

}
