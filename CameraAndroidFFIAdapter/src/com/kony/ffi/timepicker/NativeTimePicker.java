package com.kony.ffi.timepicker;

import java.util.Calendar;

import com.konylabs.android.KonyMain;
import com.konylabs.vm.Function;

import android.app.TimePickerDialog;
import android.widget.TimePicker;
import android.widget.Toast;

public class NativeTimePicker {
	private static KonyMain context ;
	//private int mYear, mMonth, mDay;
	private static int mHour;
	private static int mMinute;
	private static String data;
	static String[] mData = new String[2];
	
	public static void showTimePicker(final Function callBackFunc) {
		final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        context = KonyMain.getActivityContext();	
        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(context,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                    	data= ""+hourOfDay+":"+minute;
                    	mData[0] = data;
                    	try {
							callBackFunc.execute(mData);
						} catch (Exception e) {
							e.printStackTrace();
						}
                        //txtTime.setText(hourOfDay + ":" + minute);
                    	//Toast.makeText(context, "Hour of day ::"+hourOfDay+" minutes:: "+minute, Toast.LENGTH_LONG).show();
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
		
	}

}
