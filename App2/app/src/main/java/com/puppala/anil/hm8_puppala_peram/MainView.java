package com.puppala.anil.hm8_puppala_peram;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainView extends SurfaceView {
	private SurfaceHolder holder = null;
	Context context;
	private MainThread t = null;
	
	// Constructor 
	public MainView (Context context) {
		super(context);
		// Retrieve the SurfaceHolder instance associated with this SurfaceView. 
		holder = getHolder();
		
		// Initialize variables
		this.context = context;
		Assets.state = Assets.GameState.GettingReady;
		Assets.livesLeft = 3;
		//Background sound while playing game
		// Load the sound effects
	    Assets.soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
		Assets.sound_getready = Assets.soundPool.load(context, R.raw.start, 1);
		Assets.sound_squish = Assets.soundPool.load(context, R.raw.squishsimple, 1);
		Assets.sound_tong = Assets.soundPool.load(context, R.raw.tong, 1);
		Assets.lady_sound_squish = Assets.soundPool.load(context, R.raw.plasticbottle, 1);
	}
	
	public void pause ()
	{
		t.setRunning(false);
		while (true) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			break;
		}
		t = null;
	}
	
	public void resume () 
	{
		t = new MainThread (holder, context);
		t.setRunning(true);
		t.start();
		setFocusable(true); // make sure we get events
	}
	
	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event) 
	{
		float x, y;
		int action = event.getAction();
		x = event.getX();
		y = event.getY();
//		if (action==MotionEvent.ACTION_MOVE) {
//		}
		if (action==MotionEvent.ACTION_DOWN){
			t.setXY ((int)x, (int)y);
			Log.i("ProjectLogging", "Touch at: " + x + "," + y);
		}
//		if (action == MotionEvent.ACTION_UP) {			
//		}		
		return true; // to indicate we have handled this event
	}
}