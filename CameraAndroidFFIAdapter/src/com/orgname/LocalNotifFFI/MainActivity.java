package com.orgname.LocalNotifFFI;

import com.orgname.LocalNotifFFI.R;
import com.konylabs.android.KonyMain;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraDevice;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraManager.AvailabilityCallback;

public class MainActivity extends Activity {
	Boolean  isFLashAvailable = false;
	Context context ;
	
	  private  Camera camera;
	  private CameraDevice cameraDevice;
	   Parameters params;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@TargetApi(23)
	public void startTorchJohn(View v) {
		CameraManager mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            
			try {
				String mCameraId =mCameraManager.getCameraIdList()[0];
				mCameraManager.setTorchMode(mCameraId, true);
			} catch (CameraAccessException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				Log.i("John", "Error torch:: "+e.getMessage());
			}
        }
		//manager.registerAvailabilityCallback(AvailabilityCallback.onCameraUnavailable(TEMPLATE_MANUAL), MainActivity.this);
	}
	
	public  void startCameraFlash(View v) {
			context = getApplicationContext();
			isFLashAvailable = context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
			if(!isFLashAvailable) {
				Toast.makeText(context, "Sorry, your device doesn't support flash light!", Toast.LENGTH_LONG).show();
				return;
			} 
			getCamera();
			turnOnFlash();
			
		}
		
		/*
		 * Get the camera
		 */
		private  void getCamera() {
			if (camera == null) {
				try {
					camera = Camera.open();
					params = camera.getParameters();
				} catch (RuntimeException e) {
					Log.e("Camera Error. Failed to Open. Error: ", e.getMessage());
				}
			}
		}
		
		/*
		 * Turning On flash
		 */
		private  void turnOnFlash() {
			if (!isFLashAvailable) {
				if (camera == null || params == null) {
					return;
				}				
				params = camera.getParameters();
				params.setFlashMode(Parameters.FLASH_MODE_TORCH);
				camera.setParameters(params);
				camera.startPreview();
				isFLashAvailable = true;
			}

		}
}
