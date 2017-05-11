package com.kony.calendar;

import com.rentokil.Solo.MainActivity;
import com.konylabs.android.KonyMain;

import android.content.Intent;

public class CalendarAdapter {
	static KonyMain context;

	public static void invokeCalendar() {
		context = KonyMain.getActivityContext();
		Intent intent = new Intent(context,MainActivity.class);
		context.startActivity(intent);
	}
}
