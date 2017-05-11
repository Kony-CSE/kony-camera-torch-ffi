package com.kony.tealium.adapter;

import com.konylabs.android.KonyMain;
import com.orgname.testtealiumanalytics.TestTealiumActivity;

import android.content.Intent;

public class MainClass {
	
	 public static void invokeTealium(String msg)
	  {
	    System.out.println("****captureSignature****");
	    KonyMain localKonyMain = KonyMain.getActivityContext();

	    Intent localIntent = new Intent(localKonyMain, TestTealiumActivity.class);

	    localIntent.putExtra("URL", msg);

	    localKonyMain.startActivity(localIntent);

	    System.out.println("****captureSignature End ****");
	  }
}
