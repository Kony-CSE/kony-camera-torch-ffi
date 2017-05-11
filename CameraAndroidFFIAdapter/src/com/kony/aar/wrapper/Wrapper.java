package com.kony.aar.wrapper;

import com.kony.sampleaarproject.MainActivity;
import com.konylabs.android.KonyMain;
import android.content.Intent;

public class Wrapper {
	
	public static void invokeAAR() {
		KonyMain context = KonyMain.getActivityContext();
		Intent intent = new Intent(context,MainActivity.class);
		intent.putExtra("name", "Kony here");
		context.startActivity(intent);
	}
}
