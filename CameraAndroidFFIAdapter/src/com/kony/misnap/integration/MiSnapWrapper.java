package com.kony.misnap.integration;

import com.konylabs.android.KonyMain;

import android.content.Intent;
import customers.misnap.com.misnapcustomerapp.MainActivity;

public class MiSnapWrapper {

	public static void invokeMiSnap(String pScanDocType) {
		MainActivity.scannerDocType = pScanDocType;
		KonyMain context = KonyMain.getActContext();
		Intent intent = new Intent(context,MainActivity.class);
		context.startActivity(intent);
		
	}
}
