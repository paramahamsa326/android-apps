package com.puppala.anil.hm8_puppala_peram;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.media.MediaPlayer;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.SurfaceHolder;
import android.widget.Toast;

import java.util.ArrayList;

public class MainThread extends Thread {
	private SurfaceHolder holder;
	private Handler handler;		// required for running code in the UI thread
	private boolean isRunning = false;
	static Context context;
	Paint paint;
	int touchx, touchy;	// x,y of touch event
	boolean touched;	// true if touch happened
	private static final Object lock = new Object(); 

	public MainThread (SurfaceHolder surfaceHolder, Context context) {
	   holder = surfaceHolder;
	   MainThread.context = context;
	   handler = new Handler();
	   touched = false;
	}
	
	public void setRunning(boolean b) {
		isRunning = b;	// no need to synchronize this since this is the only line of code to writes this variable
	}
	
	// Set the touch event x,y location and flag indicating a touch has happened
	public void setXY (int x, int y) {
		synchronized (lock) {
			touchx = x;
			touchy = y;
			this.touched = true;
		}
	}
	
	@Override
	public void run() {
		while (isRunning) {
			// Lock the canvas before drawing
			Canvas canvas = holder.lockCanvas();	
			if (canvas != null) {
				// Perform drawing operations on the canvas
				render(canvas);		
				// After drawing, unlock the canvas and display it
				holder.unlockCanvasAndPost (canvas);	
			}
		}
	}
	
	// Loads graphics, etc. used in game
	private void loadData (Canvas canvas) {
		Bitmap bmp;
		int newWidth, newHeight;
		float scaleFactor;
		
		// Create a paint object for drawing vector graphics
		paint = new Paint();
		
		// Load score bar
		// ADD CODE HERE
				
		// Load food bar
		bmp = BitmapFactory.decodeResource (context.getResources(), R.drawable.food);
		// Compute size of bitmap needed (suppose want height = 10% of screen height)
		newHeight = (int)(canvas.getHeight() * 0.1f);
		// Scale it to a new size
		Assets.foodbar = Bitmap.createScaledBitmap (bmp, canvas.getWidth(), newHeight, false);
		// Delete the original
		bmp = null;		
		
		// Load ant1
		bmp = BitmapFactory.decodeResource (context.getResources(), R.drawable.ant1);
		// Compute size of bitmap needed (suppose want width = 20% of screen width)
		newWidth = (int)(canvas.getWidth() * 0.2f);
		// What was the scaling factor to get to this?
		scaleFactor = (float)newWidth / bmp.getWidth();
		// Compute the new height
		newHeight = (int)(bmp.getHeight() * scaleFactor);
		// Scale it to a new size
		Assets.ant1 = Bitmap.createScaledBitmap (bmp, newWidth, newHeight, false);
		// Delete the original
		bmp = null;
		
		// Load ant2
			bmp = BitmapFactory.decodeResource (context.getResources(), R.drawable.ant2);
			// Compute size of bitmap needed (suppose want width = 20% of screen width)
			newWidth = (int)(canvas.getWidth() * 0.2f);
			// What was the scaling factor to get to this?
			scaleFactor = (float)newWidth / bmp.getWidth();
			// Compute the new height
			newHeight = (int)(bmp.getHeight() * scaleFactor);
			// Scale it to a new size
			Assets.ant2 = Bitmap.createScaledBitmap (bmp, newWidth, newHeight, false);
			// Delete the original
			bmp = null;
			bmp = BitmapFactory.decodeResource (context.getResources(), R.drawable.bug);
			// Compute size of bitmap needed (suppose want width = 20% of screen width)
			newWidth = (int)(canvas.getWidth() * 0.2f);
			// What was the scaling factor to get to this?
			scaleFactor = (float)newWidth / bmp.getWidth();
			// Compute the new height
			newHeight = (int)(bmp.getHeight() * scaleFactor);
			// Scale it to a new size
			Assets.ladyroach1 = Bitmap.createScaledBitmap (bmp, newWidth, newHeight, false);
			// Delete the original
			bmp = null;
			//Lady roach2
			bmp = BitmapFactory.decodeResource (context.getResources(), R.drawable.bug2);
			// Compute size of bitmap needed (suppose want width = 20% of screen width)
			newWidth = (int)(canvas.getWidth() * 0.2f);
			// What was the scaling factor to get to this?
			scaleFactor = (float)newWidth / bmp.getWidth();
			// Compute the new height
			newHeight = (int)(bmp.getHeight() * scaleFactor);
			// Scale it to a new size
			Assets.ladyroach2 = Bitmap.createScaledBitmap (bmp, newWidth, newHeight, false);
			// Delete the original
			bmp = null;
			//Lady roach3
			bmp = BitmapFactory.decodeResource (context.getResources(), R.drawable.bugsquish);
			// Compute size of bitmap needed (suppose want width = 20% of screen width)
			newWidth = (int)(canvas.getWidth() * 0.2f);
			// What was the scaling factor to get to this?
			scaleFactor = (float)newWidth / bmp.getWidth();
			// Compute the new height
			newHeight = (int)(bmp.getHeight() * scaleFactor);
			// Scale it to a new size
			Assets.ladyroach3 = Bitmap.createScaledBitmap (bmp, newWidth, newHeight, false);
			// Delete the original
			bmp = null;
			
			bmp = BitmapFactory.decodeResource (context.getResources(), R.drawable.life);
			// Compute size of bitmap needed (suppose want width = 20% of screen width)
			newWidth = (int)(canvas.getWidth() * 0.2f);
			// What was the scaling factor to get to this?
			scaleFactor = (float)newWidth / bmp.getWidth();
			// Compute the new height
			newHeight = (int)(bmp.getHeight() * scaleFactor);
			// Scale it to a new size
			Assets.lifebit = Bitmap.createScaledBitmap (bmp, newWidth, newHeight, false);
			// Delete the original
			bmp = null;
			//Lady roach2
			bmp = BitmapFactory.decodeResource (context.getResources(), R.drawable.life);
			// Compute size of bitmap needed (suppose want width = 20% of screen width)
			newWidth = (int)(canvas.getWidth() * 0.2f);
			// What was the scaling factor to get to this?
			scaleFactor = (float)newWidth / bmp.getWidth();
			// Compute the new height
			newHeight = (int)(bmp.getHeight() * scaleFactor);
			// Scale it to a new size
			Assets.lifebit = Bitmap.createScaledBitmap (bmp, newWidth, newHeight, false);
			// Delete the original
			bmp = null;
			//Lady roach3
			bmp = BitmapFactory.decodeResource (context.getResources(), R.drawable.life);
			// Compute size of bitmap needed (suppose want width = 20% of screen width)
			newWidth = (int)(canvas.getWidth() * 0.2f);
			// What was the scaling factor to get to this?
			scaleFactor = (float)newWidth / bmp.getWidth();
			// Compute the new height
			newHeight = (int)(bmp.getHeight() * scaleFactor);
			// Scale it to a new size
			Assets.lifebit = Bitmap.createScaledBitmap (bmp, newWidth, newHeight, false);
			// Delete the original
			bmp = null;
				
		// Load score bar
			bmp = BitmapFactory.decodeResource (context.getResources(), R.drawable.scorebar);
			// Compute size of bitmap needed (suppose want width = 20% of screen width)
			newWidth = (int)(canvas.getWidth() * 1.07f);
			// What was the scaling factor to get to this?
			scaleFactor = (float)newWidth / bmp.getWidth();
			// Compute the new height
			newHeight = (int)(bmp.getHeight() * scaleFactor);
			// Scale it to a new size
			Assets.scorebar = Bitmap.createScaledBitmap (bmp, newWidth, newHeight, false);
			// Delete the original
			bmp = null;
			
		// Load game end screen
			bmp = BitmapFactory.decodeResource (context.getResources(), R.drawable.scorescreen);
			// Compute size of bitmap needed (suppose want width = 20% of screen width)
			newWidth = (int)(canvas.getWidth() * 1.07f);
			// What was the scaling factor to get to this?
			scaleFactor = (float)newWidth / bmp.getWidth();
			// Compute the new height
			newHeight = (int)(bmp.getHeight() * scaleFactor);
			// Scale it to a new size
			Assets.scorescreen = Bitmap.createScaledBitmap (bmp, newWidth, newHeight, false);
			// Delete the original
			bmp = null;

		// Load the other bitmaps similarly
		// ...
		
		// Load roach3 (dead bug)
		bmp = BitmapFactory.decodeResource (context.getResources(), R.drawable.antsquash);
		// Compute size of bitmap needed (suppose want width = 20% of screen width)
		newWidth = (int)(canvas.getWidth() * 0.2f);
		// What was the scaling factor to get to this?
		scaleFactor = (float)newWidth / bmp.getWidth();
		// Compute the new height
		newHeight = (int)(bmp.getHeight() * scaleFactor);
		// Scale it to a new size
		Assets.ant3 = Bitmap.createScaledBitmap (bmp, newWidth, newHeight, false);
		// Delete the original
		bmp = null;
		
		// Create a bug
		Assets.al = new ArrayList<Bug>();
		// Create a bug
		for(int i=0; i<5;i++)
		{
		Assets.bug = new Bug();
		Assets.al.add(Assets.bug);
		}
		Assets.ladyBug = new LadyBug();
		Assets.life= new Life();
		Assets.superbug= new SuperBug();
	}

	// Load specific background screen
	private void loadBackground (Canvas canvas, int resId) {
		// Load background
		Bitmap bmp = BitmapFactory.decodeResource (context.getResources(), resId);
		// Scale it to fill entire canvas
		Assets.background = Bitmap.createScaledBitmap (bmp, canvas.getWidth(), canvas.getHeight(), false);
		// Delete the original
		bmp = null;						
	}
	public void playAudio(int resid) {
		final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		boolean b = prefs.getBoolean("prefs_music_enabled", true);		
		if (b == false)
			return;
		
		if (Assets.mp != null) {
	    	Assets.mp.release();
	    	Assets.mp = null;
	    }
		
		Assets.mp= MediaPlayer.create(context, resid);
		
		try{
			Assets.mp.start();
			Assets.mp.setLooping(true);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private void render (Canvas canvas) {
		int i, x, y;
		
		switch (Assets.state) {
			case GettingReady:
				Assets.prasentScore=0;
				// Load a special "getting ready screen"
				loadBackground (canvas, R.drawable.ready);
				// Load data and other graphics needed by game
				loadData(canvas);
				// Draw the background screen
				canvas.drawBitmap (Assets.background, 0, 0, null);
				// Play a sound effect
				Assets.soundPool.play(Assets.sound_getready, 1, 1, 1, 0, 1);
				// Start a timer
				Assets.gameTimer = System.nanoTime() / 1000000000f;
				// Go to next state
				Assets.state = Assets.GameState.Starting;
				break;
			case Starting:
				// Draw the background screen
				canvas.drawBitmap (Assets.background, 0, 0, null);

				// Has 3 seconds elapsed?
				float currentTime = System.nanoTime() / 1000000000f;
				if (currentTime - Assets.gameTimer >= 3) {
					// Load game play background
					playAudio(R.raw.gamebackgroun);
					loadBackground (canvas, R.drawable.background);
					// Goto next state
					Assets.state = Assets.GameState.Running;
				}
				break;
			case Running:
				// Draw the background screen
				canvas.drawBitmap (Assets.background, 0, 0, null);
				canvas.drawBitmap (Assets.scorebar, 0, 0, null);
				int spacing = 30;
				int size=(int)(canvas.getWidth() * 0.05f)*2;
				paint.setColor(Color.BLUE);
				paint.setStyle(Style.FILL);
				paint.setTextSize(size); 
				Assets.prasenrScorePrint= Integer.toString(Assets.prasentScore);
				canvas.drawText(Assets.prasenrScorePrint, 15, (size/2)+spacing, paint);
				// Draw the score bar at top of screen
				// ADD CODE HERE
				// Draw the score on the score bar
				// canvas.drawText(....)
				
				// Draw the foodbar at bottom of screen
				canvas.drawBitmap (Assets.foodbar, 0, canvas.getHeight()-Assets.foodbar.getHeight(), null);		
				// Draw one circle for each life at top right corner of screen
				// Let circle radius be 5% of width of screen
				int radius = (int)(canvas.getWidth() * 0.05f);
				spacing = 8; // spacing in between circles
				x = canvas.getWidth() - radius - spacing;	// coordinates for rightmost circle to draw
				y = radius + spacing;
				for (i=0; i<Assets.livesLeft; i++) {
					paint.setColor(Color.GREEN);
					paint.setStyle(Style.FILL);
					canvas.drawCircle(x, y, radius, paint);
					paint.setColor(Color.BLACK);
					paint.setStyle(Style.STROKE);
					canvas.drawCircle(x, y, radius, paint);
					// Reposition to draw the next circle to the left
					x -= (radius*2 + spacing);
				}

				// Process a touch
				if (touched) {
					// Set touch flag to false since we are processing this touch now
					touched = false;
					boolean ladyBugKilled = false, lifeKilled= false, superKilled=false;
					// See if this touch killed a bug
					boolean bugKilled = Assets.bug.touched(canvas, touchx-Assets.ant1.getWidth()/4, touchy-Assets.ant1.getHeight()/4);
					for( i = 0 ;i<5;i++)
					{
						if(bugKilled)
							break;
						bugKilled = Assets.al.get(i).touched(canvas, touchx, touchy);
					}
					ladyBugKilled = Assets.ladyBug.touched(canvas, touchx, touchy);
					lifeKilled = Assets.life.touched(canvas, touchx, touchy);
					if(ladyBugKilled){

						Assets.soundPool.play(Assets.lady_sound_squish, 1, 1, 1, 0, 1);
						Assets.state = Assets.state.GameEnding;
						
					}
					else if (bugKilled)
					{
						Assets.prasentScore ++;
						Assets.soundPool.play(Assets.sound_squish, 1, 1, 1, 0, 1);
					}

					else if (superKilled)
					{
						Assets.prasentScore+=10;
						Assets.soundPool.play(Assets.sound_squish, 1, 1, 1, 0, 1);
					}
					else
						Assets.soundPool.play(Assets.sound_tong, 1, 1, 1, 0, 1);
				}
				
				for( i = 0 ;i<5;i++)
				{
				Assets.al.get(i).drawDead(canvas);
				// Move bugs on screen
				Assets.al.get(i).move(canvas);
				// Bring a dead bug to life?
				Assets.al.get(i).birth(canvas);
				}
				if(Assets.livesLeft < 3)
				{
				Assets.life.drawDead(canvas);
				Assets.life.move(canvas);
				Assets.life.birth(canvas);
				}
				
				Assets.ladyBug.drawDead(canvas);
				Assets.ladyBug.move(canvas);
				Assets.ladyBug.birth(canvas);
				

				//Assets.superbug.drawDead(canvas);
				//Assets.superbug.move(canvas);
				//Assets.superbug.birth(canvas);
				
				if (Assets.livesLeft == 0)
					// Goto next state
					Assets.state = Assets.GameState.GameEnding;
				
				break;
				// ADD MORE CODE HERE TO PLAY GAME

				// Are no lives left?
				
			case GameEnding:
				// Show a game over message
				/*handler.post(new Runnable() {
					public void run() {	
						Toast.makeText(context, "Game Over", Toast.LENGTH_SHORT).show(); 
						if (Assets.mp != null) {
				    	Assets.mp.release();
				    	Assets.mp = null; 
				    	final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
				        SharedPreferences.Editor editor = sharedPreferences.edit();
                            if (Assets.prasentScore>sharedPreferences.getInt("highScore",0))
                                editor.putInt("highScore", Assets.prasentScore);
				        editor.commit();
					}
					}});*/
				// Goto next state
				Assets.state = Assets.GameState.GameOver;
				break;
			case GameOver:
				// Fill the entire canvas' bitmap with 'black'
				canvas.drawColor(Color.BLACK);	
				int endspacing = 50;
				int endTextSize=(int)(canvas.getWidth() * 0.05f)*2;

				paint.setColor(Color.BLUE);
				paint.setStyle(Style.FILL);
				paint.setTextSize(endTextSize); 
				Assets.prasenrScorePrint= Integer.toString(Assets.prasentScore);
				canvas.drawBitmap (Assets.scorescreen, 0, 0, null);
				canvas.drawText("Present Score: ", canvas.getWidth()*0.15f, canvas.getHeight()*0.20f, paint);
				paint.setTextSize(endTextSize+20); 
				canvas.drawText(Assets.prasenrScorePrint, canvas.getWidth()*0.45f, canvas.getHeight()*0.35f, paint);
				
				int highscore; 
				SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainThread.context);
				highscore = sharedPreferences.getInt("Assets.highScore", 0);
				//Assets.highscore=Assets.prasentScore;

                    if (Assets.prasentScore>sharedPreferences.getInt("highScore",0)) {
                        paint.setTextSize(endTextSize);
                        canvas.drawText(" New highest score", canvas.getWidth() * 0.15f, canvas.getHeight() * 0.45f + (endspacing / 2) + endspacing * 3, paint);
                    }

                handler.post(new Runnable() {
                    public void run() {
                        Toast.makeText(context, "Game Over", Toast.LENGTH_SHORT).show();
                        if (Assets.mp != null) {
                            Assets.mp.release();
                            Assets.mp = null;
                            final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            if (Assets.prasentScore>sharedPreferences.getInt("highScore",0))
                                editor.putInt("highScore", Assets.prasentScore);
                            editor.commit();
                        }
                    }});



                break;
		default:
			break;
		}	
	}
	
}

