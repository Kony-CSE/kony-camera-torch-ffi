package com.kony.zebracrossing.barcodescanner;

import com.konylabs.android.KonyMain;

import android.content.Intent;

public class ZebraCrossingScanner {
	static Intent intent;
	static KonyMain context;
	public static void startZCBarCodeScanning() {
		context = KonyMain.getActivityContext();
		intent = new Intent("com.google.zxing.client.android.SCAN");
		intent.putExtra("SCAN_MODE", "QR_CODE_MODE,PRODUCT_MODE");
		context.startActivity(intent);
	}

}
