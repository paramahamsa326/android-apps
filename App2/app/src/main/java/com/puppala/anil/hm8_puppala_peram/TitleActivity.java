package com.puppala.anil.hm8_puppala_peram;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class TitleActivity extends Activity {
	float buttonZonex, buttonZoney,buttonSpacing;
	Context context;
	@Override	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new MyView(this));
    	SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
	    	Assets.highScore = sharedPreferences.getInt("Assets.highScore", 0);
	}
	public class MyView extends View {
		Bitmap bmp, highScore, quit, settings;
		boolean switchToGameScreen;
		public MyView(Context context) {
			super(context); 
			bmp = BitmapFactory.decodeResource (getResources(), R.drawable.home);
			Assets.play= BitmapFactory.decodeResource (getResources(), R.drawable.playleaf);
			highScore= BitmapFactory.decodeResource (getResources(), R.drawable.highscoreleaf);
			quit= BitmapFactory.decodeResource (getResources(), R.drawable.quit);
			settings=BitmapFactory.decodeResource (getResources(), R.drawable.settingsleaf);
			switchToGameScreen = false;
		}

		@SuppressLint("DrawAllocation")
		@Override
		protected void onDraw (Canvas canvas) { 

			float x,y;
			// Draw the title full screen
			Rect dstRect = new Rect();
            canvas.getClipBounds(dstRect);
            canvas.drawBitmap(bmp, null, dstRect, null);
            buttonZonex=canvas.getWidth()/2;
            buttonZoney=canvas.getHeight()*0.2f;
            buttonSpacing=canvas.getHeight()*0.17f;
            x=buttonZonex;
            y=buttonZoney;
			canvas.drawBitmap (Assets.play, x, y, null);
			y+=buttonSpacing;
			canvas.drawBitmap (highScore, x, y, null);
			y+=buttonSpacing;
			canvas.drawBitmap (settings, x, y, null);
			y+=buttonSpacing;
			canvas.drawBitmap (quit, x, y, null);
            // On click switch to main (game) activity
            if (switchToGameScreen) {
            	switchToGameScreen = false;
            	startActivity(new Intent(TitleActivity.this, MainActivity.class));
            }
            else
            	invalidate();
        }
     	@SuppressLint("ClickableViewAccessibility")
		@Override
    	public boolean onTouchEvent(MotionEvent event) {
     		// On click set flag to switch to main (game) activity
     		float touchx, touchy, playx, playy, highy, optionsy, quity;
    		int action = event.getAction();
    		touchx = event.getX();
    		touchy = event.getY();
    		playx=buttonZonex+(Assets.play.getWidth()/2);
    		playy=buttonZoney+(Assets.play.getHeight()/2);
    		highy=playy+buttonSpacing;
    		optionsy=highy+buttonSpacing;
    		quity=optionsy+buttonSpacing;
    		if (action==MotionEvent.ACTION_DOWN){
    		float dis = (float)(Math.sqrt ((touchx - playx) * (touchx - playx) + (touchy - playy) * (touchy - playy)));
    		if(dis<=Assets.play.getHeight()*0.5f)
    		switchToGameScreen = true;	
    		
    		float dishigh = (float)(Math.sqrt ((touchx - playx) * (touchx - playx) + (touchy - highy) * (touchy - highy)));
    		if(dishigh<=highScore.getHeight()*0.5f)   	{	
/*    			SharedPreferences sprefs = PreferenceManager.getDefaultSharedPreferences (context);
    			Editor editor = sprefs.edit();
    			editor.putInt("high_score", Assets.highScore);
    			editor.commit();  */
    		startActivity (new Intent(this.getContext(), PrefActivity.class));	
    		}
    		float disSettings = (float)(Math.sqrt ((touchx - playx) * (touchx - playx) + (touchy - highy-buttonSpacing) * (touchy - highy-buttonSpacing)));
    		if(disSettings<=highScore.getHeight()*0.5f)   		
    		startActivity (new Intent(this.getContext(), PrefsActivity.class));	
    		
    		float disQuit = (float)(Math.sqrt ((touchx - playx) * (touchx - playx) + (touchy - quity) * (touchy - quity)));
    		if(disQuit<=highScore.getHeight()*0.5f)
       		finish();
    		Log.i("ProjectLogging", "Touch at: " + touchx + "," + touchx);
    	}
    		return true; // to indicate we have handled this event
     }
   }

	@Override
	public void onBackPressed () {

    	Log.i("ProjectLogging","Back Button Pressed");
		// Display a toast
		Toast.makeText(this, "Back button pressed", Toast.LENGTH_LONG).show();
		// Display a dialog box
		new AlertDialog.Builder(this)
		  .setTitle("Exit")
		  .setMessage("Are you really want to exit?")
		  .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
		    public void onClick (DialogInterface dg, int which) {
		    	finish();
		    }
		  })
		  .setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick (DialogInterface dg, int which) {
			  // gets into normal program  
		   }
		  })
		  .show();
	}
	@Override
	protected void onResume() {
		
		
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Editor editor = sharedPreferences.edit();
        editor.putInt("Assets.highScore", Assets.highScore);
        editor.commit();
        super.onResume();

	}
}