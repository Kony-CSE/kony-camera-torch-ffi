package com.kony.scandit.barcodeffi;

import com.konylabs.android.KonyMain;
import com.konylabs.vm.Function;
import android.content.Intent;
import android.preference.PreferenceManager.OnActivityResultListener;

import com.kony.scanditbarcodescanner.MainActivity;
public class ScanditBarcodeScanner implements OnActivityResultListener {
	static Intent intent;
	public static void startBarCodeScanning(String pScanditSdkAppKey, Function pCallbackFunction) {

	    MainActivity.scanditSdkAppKey = pScanditSdkAppKey;
	    MainActivity.callbackFunction = pCallbackFunction;
	    if (intent == null)
	    {
	      //intent = new Intent(KonyMain.getActContext(), MainActivity.class);
	    }
	    KonyMain.getActContext().startActivityForResult(intent, 0);	
	
	}
	@Override
	public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		return false;
	}
}
