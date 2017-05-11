package com.orgname.LocalNotifFFI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import com.orgname.LocalNotifFFI.R;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

public class ScreenShotActivity extends Activity {
	Date now;
	public static RelativeLayout rootView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen_shot);
		 rootView = (RelativeLayout) findViewById(R.id.relLay);
	}
	
	public void onBtnTakeScreenShot(View view) {
		 now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);
 
        try {
        	Bitmap bitmap = takeScreenshot();
            saveBitmap(bitmap);
        } catch (Throwable e) {
            // Several error may come out with file handling or OOM
            e.printStackTrace();
        }
	}
    
	   
     private void saveBitmap(Bitmap bitmap) {
    	 /*File imagePath = new File(Environment.getExternalStorageDirectory() + "/screenshot.png");
    	    FileOutputStream fos;
    	    try {
    	        fos = new FileOutputStream(imagePath);
    	        bitmap.compress(CompressFormat.JPEG, 100, fos);
    	        fos.flush();
    	        fos.close();
    	    } catch (FileNotFoundException e) {
    	        Log.e("GREC", e.getMessage(), e);
    	    } catch (IOException e) {
    	        Log.e("GREC", e.getMessage(), e);
    	    }*/
		//MediaStore.Images.Media.InsertData()
	}

	private Bitmap takeScreenshot() {
		View rootView = findViewById(android.R.id.content).getRootView();
		   rootView.setDrawingCacheEnabled(true);
		   return rootView.getDrawingCache();
		
	}

	public static final String insertImage(ContentResolver cr, 
    			Bitmap source, 
    			String title, 
    			String description) {
    		
    		ContentValues values = new ContentValues();
    		values.put(Images.Media.TITLE, title);
    		values.put(Images.Media.DISPLAY_NAME, title);
    		values.put(Images.Media.DESCRIPTION, description);
    		values.put(Images.Media.MIME_TYPE, "image/jpeg");
    		// Add the date meta data to ensure the image is added at the front of the gallery
    		values.put(Images.Media.DATE_ADDED, System.currentTimeMillis());
    		values.put(Images.Media.DATE_TAKEN, System.currentTimeMillis());

            Uri url = null;
            String stringUrl = null;    /* value to be returned */

            try {
                url = cr.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

                if (source != null) {
                    OutputStream imageOut = cr.openOutputStream(url);
                    try {
                        source.compress(Bitmap.CompressFormat.JPEG, 50, imageOut);
                    } finally {
                        imageOut.close();
                    }

                    long id = ContentUris.parseId(url);
                    // Wait until MINI_KIND thumbnail is generated.
                    Bitmap miniThumb = Images.Thumbnails.getThumbnail(cr, id, Images.Thumbnails.MINI_KIND, null);
                    // This is for backward compatibility.
                    storeThumbnail(cr, miniThumb, id, 50F, 50F,Images.Thumbnails.MICRO_KIND);
                } else {
                    cr.delete(url, null, null);
                    url = null;
                }
            } catch (Exception e) {
                if (url != null) {
                    cr.delete(url, null, null);
                    url = null;
                }
            }

            if (url != null) {
                stringUrl = url.toString();
            }

            return stringUrl;
    	}
    	
    	/**
    	 * A copy of the Android internals StoreThumbnail method, it used with the insertImage to
    	 * populate the android.provider.MediaStore.Images.Media#insertImage with all the correct
    	 * meta data. The StoreThumbnail method is private so it must be duplicated here.
    	 * @see android.provider.MediaStore.Images.Media (StoreThumbnail private method)
    	 */
    	private static final Bitmap storeThumbnail(
    			ContentResolver cr,
    			Bitmap source,
    			long id,
    			float width, 
    			float height,
    			int kind) {
    		
    		// create the matrix to scale it
    		Matrix matrix = new Matrix();

    		float scaleX = width / source.getWidth();
    		float scaleY = height / source.getHeight();

    		matrix.setScale(scaleX, scaleY);

    		Bitmap thumb = Bitmap.createBitmap(source, 0, 0,
    			source.getWidth(),
    			source.getHeight(), matrix,
    			true
    		);

    		ContentValues values = new ContentValues(4);
    		values.put(Images.Thumbnails.KIND,kind);
    		values.put(Images.Thumbnails.IMAGE_ID,(int)id);
    		values.put(Images.Thumbnails.HEIGHT,thumb.getHeight());
    		values.put(Images.Thumbnails.WIDTH,thumb.getWidth());

    		Uri url = cr.insert(Images.Thumbnails.EXTERNAL_CONTENT_URI, values);

    		try {
    			OutputStream thumbOut = cr.openOutputStream(url);
    			thumb.compress(Bitmap.CompressFormat.JPEG, 100, thumbOut);
    			thumbOut.close();
    			return thumb;
    		} catch (FileNotFoundException ex) {
    			return null;
    		} catch (IOException ex) {
    			return null;
    		}
        }
}
