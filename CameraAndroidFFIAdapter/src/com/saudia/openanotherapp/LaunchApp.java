package com.saudia.openanotherapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import com.konylabs.android.KonyMain;

public class LaunchApp {
	
	
	// Launch any App after clicking the button
    public static void launchAppRequest(String appPackage) {
    	
    	KonyMain km = KonyMain.getActivityContext();
    	
        PackageManager pm = km.getPackageManager();
        boolean isInstalled = isPackageInstalled(appPackage, pm);
        Intent launchApp=null;

        if (isInstalled == true){
         launchApp = pm.getLaunchIntentForPackage(appPackage);
         km.startActivity(launchApp);

        }else{

            try {
                km.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackage)));
            } catch (android.content.ActivityNotFoundException anfe) {
                km.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackage)));
            }
        }
    }


    private static boolean isPackageInstalled(String packagename, PackageManager packageManager) {
        try {

            packageManager.getPackageInfo(packagename, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }


}
