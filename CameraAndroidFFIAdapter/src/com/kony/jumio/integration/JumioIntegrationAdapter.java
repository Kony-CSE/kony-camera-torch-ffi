package com.kony.jumio.integration;

import com.konylabs.android.KonyMain;
import com.konylabs.ffi.ActivityResultListener;
import com.konylabs.vm.Function;
import com.stgit.jumionative.MainActivity;
import android.content.Intent;
import android.util.Log;

/**
 * 
 * @author KH2195 John Vinodh
 *
 */
public class JumioIntegrationAdapter {

	private static KonyMain context;
	public static Function fun;
	
	public static void invokeJumioSDK(Function pCallbackFunction) {
		System.out.println("########## JumioController ###############");
		KonyMain act = KonyMain.getActContext();
		act.registerActivityResultListener(1,
				(ActivityResultListener) new myResult());
		fun = pCallbackFunction;
		Intent myIntent = new Intent(act, MainActivity.class);
		// myIntent.putExtra("url", url);
		act.startActivityForResult(myIntent, 1);
		System.out.println("########## JumioController 4 ###############");
		Log.d((String) "Jumio",
				(String) "---- sssssssssssssssEnd of the FFi Code ----");
	}
	}

class myResult implements ActivityResultListener {
	myResult() {
	}

	public void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		Log.d((String) "StandardLib", (String) "Reached inside this place");
		Log.d((String) "StandardLib",
				(String) ("request code is " + requestCode));
		Log.d((String) "StandardLib",
				(String) ("resultCode  is " + resultCode));
		if (requestCode == 1 && resultCode == -1) {
			try {
				//Function f1 = fun;
				//f1.execute(new Object[] {});
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Log.d((String) "StandardLib",
				(String) "$$$$ Reached End of FFI $$$$");
	}
}
