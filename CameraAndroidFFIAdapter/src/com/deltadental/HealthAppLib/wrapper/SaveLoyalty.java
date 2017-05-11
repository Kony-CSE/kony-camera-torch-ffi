package com.deltadental.HealthAppLib.wrapper;

import com.deltadental.HealthAppLib.SaveToAndroidPay;
import com.konylabs.android.KonyMain;

import android.content.Intent;

public class SaveLoyalty {

	public static final String EXTRA_MESSAGE = "com.kony.SaveLoyalty.MESSAGE";
	static String message = "{\"deviceType\":\"phone\",\"memberCompanyName\":\"DELTA DENTAL OF ILLINOIS\",\"addressLineOne\":\"P.O. Box 5402\",\"addressLineTwo\":\"Lisle, IL 60532\",\"phoneNumber\":\"800-323-1743\",\"fullName\":\"John Appleseed\",\"groupName\":\"Pepper Dental Services \",\"groupNumber\":\"\",\"productName\":\"Delta Dental PPO\",\"displayId\":\"\"}";//intent.getStringExtra("extra_data");
	public static void saveLoyalty(String param)
	  {
		KonyMain context = KonyMain.getActivityContext();
		Intent intent = new Intent(context,SaveToAndroidPay.class);
		intent.putExtra("extra_data", param);
		context.startActivity(intent);
	  }
}
