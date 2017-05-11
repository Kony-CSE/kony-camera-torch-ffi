package com.kony.toastffi;

import com.konylabs.android.KonyMain;

import android.util.Log;
import android.widget.Toast;

public class ToastMessage {

	public static void showToastMessage(String msg, int duration) {
		 Log.d("KonyFFILib","In ToastMessage: showToastMesasge() -----START");
		KonyMain context = KonyMain.getActContext();
		Toast.makeText(context, msg, duration).show();
		Log.d("KonyFFILib","In ToastMessage: showToastMesasge() -----END");
	}
	
	 public String concatStrings(String s1, String s2) {
	        return s1+"is concatenated with "+s2;
	    }
}
