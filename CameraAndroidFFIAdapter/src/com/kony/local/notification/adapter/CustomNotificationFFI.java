package com.kony.local.notification.adapter;

import com.konylabs.android.KonyMain;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
public class CustomNotificationFFI {

	 public static final int NOTIFICATION_ID = 1;
	 
	public static void sendNotification(String pTitle,String pContextText,String pSubText,String imageName) {

        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://developer.android.com/reference/android/app/Notification.html"));
        PendingIntent pendingIntent = PendingIntent.getActivity(KonyMain.getActivityContext(), 0, intent, 0);
        
        NotificationCompat.Builder builder = new NotificationCompat.Builder(KonyMain.getActivityContext());
        //builder.setSmallIcon(android.R.drawable.icon);
        //builder.setSmallIcon(BitmapFactory.decodeResource(KonyMain.getActivityContext().getResources(), android.R.drawable.sym_def_app_icon));
        builder.setContentIntent(pendingIntent);

        builder.setAutoCancel(true);
        String pkgName = KonyMain.getAppContext().getPackageName();
       /* BitmapFactory.decodeResource(KonyMain.getActivityContext().getResources(), 
                R.drawable.ic_launcher)*/
       // Drawable drawable=KonyMain.getActivityContext().getResources().getDrawable(R.drawable.icon);
        //ContextCompat.getDrawable(KonyMain.getAppContext(), R.drawable.icon);
        int resID = KonyMain.getActivityContext().getResources().getIdentifier(imageName, "drawable", pkgName);  
        builder.setSmallIcon(resID);
        builder.setColor(Color.rgb(191, 57, 57));
        builder.setContentTitle(pTitle);
        builder.setContentText(pContextText);
        builder.setSubText(pSubText);

        NotificationManager notificationManager = (NotificationManager) KonyMain.getActivityContext().getSystemService(
                KonyMain.getActivityContext().NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
       
    }
}
