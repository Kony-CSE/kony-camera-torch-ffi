package com.kony.custom.local.notification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import com.konylabs.android.KonyMain;
import com.konylabs.notification.KonyLocalNotificationBroadcastReceiver;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

public class CustomLocalNotificationBroadcastReceiver extends KonyLocalNotificationBroadcastReceiver {

	private static int localMsgNotificationId;
    private static int localMsgActionId;
    
    public static final Integer ACTION_VISIBLEON_WATCH_ONLY = 0;
	public static final Integer ACTION_VISIBLEON_BOTH = 1;
	
	public void showLocalMessageNotification(Context context, Intent intent) {/*
		String pkgName = context.getPackageName();
		
		Bundle extras = intent.getExtras();
		String message = extras.getString("message");
		String title = extras.getString("title");
		String categoryId = extras.getString("categoryId");
		
		int resId = context.getResources().getIdentifier("notify_local_msg", "string", pkgName);
		String enableNotifyLocalMsg = context.getString(resId);
		
		if(enableNotifyLocalMsg != null && enableNotifyLocalMsg.equalsIgnoreCase("true")) //Check if local msg is enabled or not
		{				
			resId = context.getResources().getIdentifier("notify_local_msg_icon", "string", pkgName);
			String iconName = context.getString(resId);

			int icon = context.getResources().getIdentifier(iconName, "drawable", pkgName);
			if(icon == 0)
				icon = context.getResources().getIdentifier("icon", "drawable", pkgName);
			
			resId = context.getResources().getIdentifier("notify_local_msg_sound", "string", pkgName);
			String sound = context.getString(resId);
			
			resId = context.getResources().getIdentifier("notify_local_msg_vibrate", "string", pkgName);
			String vibrate = context.getString(resId);
			
			resId = context.getResources().getIdentifier("notify_local_msg_lights", "string", pkgName);
			String lights = context.getString(resId);
			
			resId = context.getResources().getIdentifier("notify_local_msg_clear", "string", pkgName);
			String clear = context.getString(resId);
			
			int notificationId  = generateLocalMessageNotificationId(context);
			Intent wearIntent = new Intent();
			wearIntent.putExtras(intent.getExtras());
			wearIntent.putExtra("requestCode", notificationId);
			wearIntent.setAction(pkgName+".LOCAL_MSG_VIEW");
			wearIntent.addCategory("android.intent.category.DEFAULT");
			PendingIntent contentIntent = PendingIntent.getActivity(context, notificationId, wearIntent, PendingIntent.FLAG_UPDATE_CURRENT);
			intent.setAction(KonyMain.getAppContext().getPackageName()+".localnotificationclear");
			PendingIntent deleteIntent = PendingIntent.getBroadcast(KonyMain.getAppContext(), notificationId, intent, PendingIntent.FLAG_UPDATE_CURRENT);
			
			NotificationCompat.Builder notificationBuilder =
	                new NotificationCompat.Builder(context)
	                        .setSmallIcon(icon)
	                        .setColor(Color.rgb(191, 57, 57))
	                        .setContentTitle(title)
	                        .setContentText(message).setWhen(System.currentTimeMillis()).setContentIntent(contentIntent).setDeleteIntent(deleteIntent);
			
			ArrayList<NotificationCompat.Action> wearbleActions = new ArrayList<NotificationCompat.Action>();
			ArrayList<NotificationCompat.Action> actions = new ArrayList<NotificationCompat.Action>();
			
			KonyNotificationSettingsDB db = new KonyNotificationSettingsDB();
			
			if (categoryId != null && categoryId.length() > 0) {
				
				String actionIds[] = db.getRegisteredActionsByCategory(categoryId);
				if(actionIds != null){
					for (String actionId : actionIds) {
						if(actionId.length()>0){
							String [] actionInfo = db.getActionsInfoById(actionId);
							if(actionInfo != null){
								Integer visibleOn = Integer.valueOf(actionInfo[2]);
							
	                            PendingIntent actionIntent = createNotificationPendingIntentForAction(context, intent, actionId, notificationId);
	                            try{
	                            resId = context.getResources().
										getIdentifier(actionInfo[1].substring(0,actionInfo[1].indexOf(".")), "drawable", context.getPackageName());
	                            }catch(Exception e){
	                            	resId = 0;
	                            }
	                            
	                            NotificationCompat.Action action = new NotificationCompat.Action.Builder(
										 resId, actionInfo[0], actionIntent)
							                .build();
								 
								 if(visibleOn == ACTION_VISIBLEON_WATCH_ONLY){
									 wearbleActions.add(action);
								 }else{ //means both
									 wearbleActions.add(action);
									 actions.add(action);
								 }
							}
						}
					}
					notificationBuilder.extend(new NotificationCompat.WearableExtender().addActions(wearbleActions));
					for(NotificationCompat.Action action: actions){
						notificationBuilder.addAction(action);
					}
				}
				
				HashMap<String, Object> map = db.getPropertyInfo(categoryId);
				db.close();
				
				if(map != null && map.size() > 0){
					Set<String> keySet = map.keySet();
					for(String key:keySet){
						Object value = map.get(key);
						if(key.equalsIgnoreCase("priority")){
							int priority = (int) Double.parseDouble((String) value);
							notificationBuilder.setPriority(priority);
						}
					}
				}
			}
			
			Notification notification = notificationBuilder.build();
			notification.flags |= Notification.FLAG_AUTO_CANCEL;
			if(clear != null && clear.equalsIgnoreCase("true"))
				notification.flags |= Notification.FLAG_NO_CLEAR;
			if(sound != null && sound.equalsIgnoreCase("true"))
				notification.defaults |= Notification.DEFAULT_SOUND;
			if(vibrate != null && vibrate.equalsIgnoreCase("true"))
				notification.defaults |= Notification.DEFAULT_VIBRATE;
			if(lights != null && lights.equalsIgnoreCase("true"))
				notification.defaults |= Notification.DEFAULT_LIGHTS;
			
		    // Get an instance of the NotificationManager service
			NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
		    // Build the notification and issues it with notification manager.
			notificationManager.notify(notificationId, notification);
		}
	*/}
	
	protected final PendingIntent createNotificationPendingIntentForAction(Context context,Intent intent, String actionId, int notificationId){
		Intent wearIntent = new Intent();
		wearIntent.putExtras(intent.getExtras());
		String pkgName = context.getPackageName();
		wearIntent.setAction(pkgName+".LOCAL_MSG_VIEW");
        wearIntent.putExtra("actionId", actionId);
		wearIntent.addCategory("android.intent.category.DEFAULT");
        int actionIdNumber = generateLocalMessageActionId(context);
        wearIntent.putExtra("requestCode", notificationId);
		PendingIntent pendingIntent = PendingIntent.getActivity(context, actionIdNumber, wearIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		return pendingIntent;
	}
	
	private final int generateLocalMessageActionId(Context context){
	       int actionsStartId = getMaxLocalNotificationsCount(context)+1;
		   int actionsLimit = (getMaxLocalNotificationsCount(context) * 3)+1;
		   if(localMsgActionId > getMaxLocalNotificationsCount(context)){
				++localMsgActionId;
		   }else{
				localMsgActionId = actionsStartId;
		   }
	        if(localMsgActionId > actionsLimit)
	            localMsgActionId = actionsStartId;

	        return localMsgActionId;
	    }
	
	private final int getMaxLocalNotificationsCount(Context context){
		int maxNoti = 1;
		String pkgName = context.getPackageName();
		int resId = context.getResources().getIdentifier("notify_local_msg_notifications_count", "string", pkgName);
		if(resId != 0){
			String maxNotificationsStr = context.getString(resId);
			try {
				maxNoti =  Integer.parseInt(maxNotificationsStr);
				if(maxNoti > 50)
					maxNoti = 50;
			}
			catch(Exception e){}
		}
		return maxNoti;
	}
	
	private final int generateLocalMessageNotificationId(Context context){
		++localMsgNotificationId;
		if(localMsgNotificationId > getMaxLocalNotificationsCount(context))
			localMsgNotificationId = 1;

		return localMsgNotificationId;
	}
}
