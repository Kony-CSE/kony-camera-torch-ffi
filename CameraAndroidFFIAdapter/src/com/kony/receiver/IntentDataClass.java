package com.kony.receiver;

import com.konylabs.android.KonyMain;

import android.app.Activity;
import android.content.Intent;

public class IntentDataClass {
	 public static String intentData = "";
	 public static Boolean isNewIntentCalled = false;
	  public static String getIntentData() {
	    return intentData;
	  }
	  
	  public static void setIntentDataEmpty() {
		  intentData = "";
	  }
	  
	  public static void sendDataToNativeApp(String msg) {
		  Intent intent=new Intent(); 
		  KonyMain context = KonyMain.getActivityContext();
          intent.putExtra("key",msg	);  
          context.setResult(Activity.RESULT_OK,intent);  
          context.finish();
	  }
}
