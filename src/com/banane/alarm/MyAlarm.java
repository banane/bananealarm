package com.banane.alarm;

import android.app.Application;

public class MyAlarm extends Application {
	public int notificationCount;

	@Override
	public void onCreate() {
		super.onCreate();
		notificationCount = 0;
	}
	
	public void incrementCount(){
		notificationCount ++;
	}
	
	public int getNotificationCount(){
		return notificationCount;
	}
}
