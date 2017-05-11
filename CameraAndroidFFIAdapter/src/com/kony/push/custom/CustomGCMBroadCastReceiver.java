package com.kony.push.custom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.konylabs.gcm.KonyGCMBroadcastReceiver;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

public class CustomGCMBroadCastReceiver extends KonyGCMBroadcastReceiver   {
	private static int pushMsgNotificationId = 0;
	@Override
	public void showPushMessageNotification(Context context,Intent intent) {
		
		String pkgName = context.getPackageName();
		int resId = context.getResources().getIdentifier("notify_push_msg", "string", pkgName);
		String enableNotifyPushMsg = context.getString(resId);
		if(enableNotifyPushMsg != null && enableNotifyPushMsg.equalsIgnoreCase("true")) //Check if push is enabled or not
		{				
			resId = context.getResources().getIdentifier("notify_push_msg_icon", "string", pkgName);
			String iconName = context.getString(resId);

			int icon = context.getResources().getIdentifier(iconName, "drawable", pkgName);
			if(icon == 0)
				icon = context.getResources().getIdentifier("icon", "drawable", pkgName);
			
			resId = context.getResources().getIdentifier("notify_push_msg_title_from_payload", "string", pkgName);
			String titleFromPayload = context.getString(resId);
			String title = null;
			String randomKey = null;
			if(titleFromPayload != null && titleFromPayload.equalsIgnoreCase("true"))
			{	
				resId = context.getResources().getIdentifier("notify_push_msg_title_keys", "string", pkgName);
				if(resId != 0){
					String titleKeysStr = context.getString(resId);
					if(titleKeysStr != null && titleKeysStr.trim().length() > 0){
						String[] titleKeys = titleKeysStr.split(",");
						Bundle bundle = intent.getExtras();
						for(int i= 0;i<titleKeys.length;++i){
							if((title=bundle.getString(titleKeys[i]))!=null)
								break;
						}
					}
				}
				
				if(title == null){
					//Take some randon key from payload bundle
					Bundle bundle = intent.getExtras();
					Set<String> keySet = bundle.keySet();
					if(keySet != null && !keySet.isEmpty())	{
						Iterator<String> it = keySet.iterator();
						if(it.hasNext()){
							randomKey = it.next();
							title = bundle.getString(randomKey);
						}
					}
				}
			}
			if(title == null)
			{
				resId = context.getResources().getIdentifier("notify_push_msg_default_title", "string", pkgName);
				title = context.getString(resId);				
			}

			resId = context.getResources().getIdentifier("notify_push_msg_desc_from_payload", "string", pkgName);
			String descFromPayload = context.getString(resId);
			String desc = null;
			if(descFromPayload != null && descFromPayload.equalsIgnoreCase("true"))
			{
				resId = context.getResources().getIdentifier("notify_push_msg_desc_keys", "string", pkgName);
				if(resId != 0){
					String descKeysStr = context.getString(resId);
					if(descKeysStr != null && descKeysStr.trim().length() > 0){
						String[] descKeys = descKeysStr.split(",");
						Bundle bundle = intent.getExtras();
						for(int i= 0;i<descKeys.length;++i){
							if((desc=bundle.getString(descKeys[i]))!=null)
								break;
						}
					}
				}
				
				if(desc == null){
					//Take some randon value from payload bundle
					Bundle bundle = intent.getExtras();
					if(randomKey == null){
						Set<String> keySet = bundle.keySet();
						if(keySet != null && !keySet.isEmpty())	{
							Iterator<String> it = keySet.iterator();
							if(it.hasNext())
								randomKey = it.next();
						}
					}
					
					desc = bundle.getString(randomKey);
				}
			}
			if(desc == null)
			{
				resId = context.getResources().getIdentifier("notify_push_msg_default_desc", "string", pkgName);
				desc = context.getString(resId);				
			}
			
			resId = context.getResources().getIdentifier("notify_push_msg_sound", "string", pkgName);
			String sound = context.getString(resId);
			
			resId = context.getResources().getIdentifier("notify_push_msg_vibrate", "string", pkgName);
			String vibrate = context.getString(resId);
			
			resId = context.getResources().getIdentifier("notify_push_msg_lights", "string", pkgName);
			String lights = context.getString(resId);
			
			resId = context.getResources().getIdentifier("notify_push_msg_clear", "string", pkgName);
			String clear = context.getString(resId);
			
			int notificationId  = generatePushMessageNotificationId(context);
			PendingIntent contentIntent = createNotificationPendingIntent(context,intent, notificationId); 
			
			NotificationCompat.Builder notificationBuilder =
	                new NotificationCompat.Builder(context)
	                        .setSmallIcon(icon)
	                        .setContentTitle(title)
	                        .setColor(Color.rgb(191, 57, 57)) // Color.rgb(255, 0, 0);
	                        .setContentText(desc).setWhen(System.currentTimeMillis()).setContentIntent(contentIntent);
			
			ArrayList<NotificationCompat.Action> wearbleActions = new ArrayList<NotificationCompat.Action>();
			ArrayList<NotificationCompat.Action> actions = new ArrayList<NotificationCompat.Action>();
			
			String categoryId = intent.getExtras().getString("category");
			//KonyNotificationSettingsDB db = new KonyNotificationSettingsDB();
			
			if (categoryId != null && categoryId.length() > 0) {/*
				String actionIds[] = db.getRegisteredActionsByCategory(categoryId);
				if(actionIds != null){
					for (String actionId : actionIds) {
						if(actionId.length()>0){
							String [] actionInfo = db.getActionsInfoById(actionId);
							if(actionInfo != null){
								Integer visibleOn = Integer.valueOf(actionInfo[2]);
							
	                            PendingIntent wearPendingIntent = createNotificationPendingIntentForAction(context, intent, actionId, notificationId);
	                            try{
	                            resId = context.getResources().
										getIdentifier(actionInfo[1].substring(0,actionInfo[1].indexOf(".")), "drawable", context.getPackageName());
	                            }catch(Exception e){
									resId = 0;
								}
								 NotificationCompat.Action action = new NotificationCompat.Action.Builder(
										 resId, actionInfo[0], wearPendingIntent)
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
			*/} 
			
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
	}

		private final int generatePushMessageNotificationId(Context context){
		++pushMsgNotificationId;
		if(pushMsgNotificationId > getMaxNotificationsCount(context))
			pushMsgNotificationId = 1;

		return pushMsgNotificationId;
	}
		
		private final int getMaxNotificationsCount(Context context){
			int maxNoti = 1;
			String pkgName = context.getPackageName();
			int resId = context.getResources().getIdentifier("notify_push_msg_notifications_count", "string", pkgName);
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
}
