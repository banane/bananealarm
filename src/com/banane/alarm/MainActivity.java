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
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private static final String TAG = "BANANEALARM";
	public AlarmManager alarmManager;
	Intent alarmIntent;
	PendingIntent pendingIntent;
	Button bananaButton;
	TextView notificationCount;
	TextView notificationCountLabel;
	int mNotificationCount;
	static final String NOTIFICATION_COUNT = "notificationCount";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null) {
	        // Restore value of members from saved state
			mNotificationCount = savedInstanceState.getInt(NOTIFICATION_COUNT);
		}
		
		setContentView(R.layout.activity_main);
		bananaButton = (Button)findViewById(R.id.bananaButton);
		notificationCount = (TextView)findViewById(R.id.notificationCount);
		notificationCountLabel = (TextView)findViewById(R.id.notificationCountLabel);

	}
	
	public void onSaveInstanceState(Bundle savedInstanceState) {
	    // Save the user's current game state
	    savedInstanceState.putInt(NOTIFICATION_COUNT, mNotificationCount);
	    super.onSaveInstanceState(savedInstanceState);
	}
	
	 @Override
	 protected void onNewIntent( Intent intent ) {
		 Log.i( TAG, "onNewIntent(), intent = " + intent );
	        if (intent.getExtras() != null)
	        {
	            Log.i(TAG, "in onNewIntent = " + intent.getExtras().getString("test"));
	        }
	        super.onNewIntent( intent );
	        setIntent( intent );
	  }

	
	public void triggerAlarm(View v){
		setAlarm();
		bananaButton.setVisibility(View.GONE);
		notificationCountLabel.setVisibility(View.VISIBLE);
		notificationCount.setVisibility(View.VISIBLE);
		notificationCount.setText("0");
	}
	
	public void setAlarm(){
		alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

		alarmIntent = new Intent(MainActivity.this, AlarmReceiver.class);
		pendingIntent = PendingIntent.getBroadcast(  MainActivity.this, 0, alarmIntent, 0);
		
		Calendar alarmStartTime = Calendar.getInstance();
		alarmStartTime.add(Calendar.MINUTE, 2);
	 	alarmManager.setRepeating(AlarmManager.RTC, alarmStartTime.getTimeInMillis(), getInterval(), pendingIntent);
	 	Log.i(TAG,"Alarms set every two minutes.");

	}
	
	private int getInterval(){
	    int seconds = 60;
	    int milliseconds = 1000;
	    int repeatMS = seconds * 2 * milliseconds;
	    return repeatMS;
	}
	
	@Override 
	protected void onStart(){
		super.onStart();
		updateUI();
	}
	
	public void cancelNotifications(){
		Log.i(TAG,"All notifications cancelled.");
	}
	
	public void updateUI(){
		MyAlarm app = (MyAlarm)getApplicationContext();
		mNotificationCount = app.getNotificationCount();
		notificationCount.setText(Integer.toString(mNotificationCount));
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		if(this.getIntent().getExtras() != null){
			Log.i(TAG,"extras: " + this.getIntent().getExtras());
			updateUI();
			
		}
	}

	

}
