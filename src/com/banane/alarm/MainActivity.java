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
		
		setContentView(R.layout.activity_main);

	}
	
	public void triggerAlarm(View v){
		setAlarm();
	}
	
	public void setAlarm(){
		alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		alarmIntent = new Intent(MainActivity.this, AlarmReceiver.class);
		pendingIntent = PendingIntent.getBroadcast(  MainActivity.this, 0, alarmIntent, 0);
		
		Calendar alarmStartTime = Calendar.getInstance();

		// it's easy to test with 2 minutes in the future
//		calendar.add(Calendar.MINUTE, 2);		  
//		alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(),pendingIntent);
		
		 alarmStartTime.set(Calendar.HOUR_OF_DAY, 10);
	     alarmStartTime.set(Calendar.MINUTE, 00);
	     alarmStartTime.set(Calendar.SECOND, 0);
	 	alarmManager.setRepeating(AlarmManager.RTC, alarmStartTime.getTimeInMillis(), getInterval(), pendingIntent);

		Log.d(TAG,"alarmStartTime: "+ alarmStartTime.getTime());
	}
	
	private int getInterval(){
		int days = 1;
	    int hours = 24;
	    int minutes = 60;
	    int seconds = 60;
	    int milliseconds = 1000;
	    int repeatMS = days * hours * minutes * seconds * milliseconds;
	    return repeatMS;
	}
	
	@Override 
	protected void onStart(){
		super.onStart();
		cancelNotifications();
		setAlarm();
	}
	
	public void cancelNotifications(){
		notificationManager.cancelAll();
	}

	

}
