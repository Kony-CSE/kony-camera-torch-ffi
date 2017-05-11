package com.kony.cameraflash;

import com.konylabs.android.KonyMain;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

public class CameraFlash {
	static Boolean isFLashAvailable = false;
	private static Camera camera;
	static Parameters params;
	static CameraManager mCameraManager;

	public static void startCameraFlash() {
		KonyMain context = KonyMain.getActivityContext();
		isFLashAvailable = context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
		if (!isFLashAvailable) {
			Toast.makeText(context, "Sorry, your device doesn't support flash light!", Toast.LENGTH_LONG).show();
			return;
		}

		mCameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			startCameraFlashForLollipop();
		} else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
			getCamera();
			turnOnFlash();
		}

	}

	/**
	 * starting Camera Flash for Lollipop and Above versions
	 * 
	 */
	@TargetApi(23)
	private static void startCameraFlashForLollipop() {

		try {
			String mCameraId = mCameraManager.getCameraIdList()[0];
			mCameraManager.setTorchMode(mCameraId, true);
		} catch (CameraAccessException e) {
			
			// e.printStackTrace();
			Log.i("StandardLib", "Error torch:: " + e.getMessage());
		}

	}

	/*
	 * Get the camera
	 */
	private static void getCamera() {
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
	private static void turnOnFlash() {
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

	public static void stopCameraFlash() {

		KonyMain context = KonyMain.getActivityContext();
		isFLashAvailable = context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
		if (!isFLashAvailable) {
			Toast.makeText(context, "Sorry, your device doesn't support flash light!", Toast.LENGTH_LONG).show();
			return;
		}

		mCameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			stopCameraFlashForLollipop();
		} else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
			getCamera();
			turnOffFlash();
		}
	}

	/**
	 * Stopping Camera Flash for Lollipop and Above versions
	 * 
	 */
	@TargetApi(23)
	private static void stopCameraFlashForLollipop() {

		try {
			String mCameraId = mCameraManager.getCameraIdList()[0];
			mCameraManager.setTorchMode(mCameraId, false);
		} catch (CameraAccessException e) {				

			Log.i("StandardLib", "Error torch:: " + e.getMessage());
		}

	}

	private static void turnOffFlash() {
		if (!isFLashAvailable) {
			if (camera == null || params == null) {
				return;
			}

			params = camera.getParameters();
			params.setFlashMode(Parameters.FLASH_MODE_TORCH);
			camera.setParameters(params);
			camera.stopPreview();
			isFLashAvailable = false;
		}
	}
}
