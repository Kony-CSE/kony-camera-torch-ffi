package com.kony.custom.calendar;

import com.konylabs.api.ui.KonyCustomWidget;
import com.konylabs.vm.Function;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;

public class CustomCalendar extends KonyCustomWidget {
	
	 LinearLayout linearLayout;
	 Function jsOnClickCallback;
	 private static String TAG = "CalendarWidget";
	@Override
	public View onCreateView(Context context) {
		Log.i(TAG, "Entering into onCreateView");
	    this.linearLayout = new LinearLayout(context);
	    DatePicker datePicker = new DatePicker(context);
	    datePicker.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
		return null;
	}

	@Override
	public void onDestroyView(View arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHeight(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setWidth(int arg0) {
		// TODO Auto-generated method stub
		
	}

}
