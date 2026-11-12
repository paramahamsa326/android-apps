package com.puppala.anil.hm8_puppala_peram;


import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.SoundPool;

import java.util.ArrayList;


public class Assets {
	static Bitmap background;
	static Bitmap foodbar;
	static Bitmap ant1;
	static Bitmap ant2;
	static Bitmap ant3;
	static Bitmap scorebar;
	static Bitmap scorescreen;
	static Bitmap play;
	static MediaPlayer mp;
	public static Context context;
	// States of the Game Screen
	enum GameState {
	  GettingReady,	// play "get ready" sound and start timer, goto next state 
	  Starting,		// when 3 seconds have elapsed, goto next state
	  Running, 		// play the game, when livesLeft == 0 goto next state
	  paused,
	  GameEnding,	// show game over message
	  GameOver,		// game is over, wait for any Touch and go back to title activity screen
	};
	static GameState state;		// current state of the game
	static float gameTimer;	// in seconds
	static int livesLeft;		// 0-3
	static int prasentScore=0;
	static int highScore;
	static String prasenrScorePrint;
	static int highScorePrint;
	
	
	static SoundPool soundPool;
	static int sound_getready;
	static int sound_squish;
	static int sound_tong;
	static int lady_sound_squish;
	
	static Bug bug;
	static Life life;
	static ArrayList<Bug> al;
	static LadyBug ladyBug;
	public static Bitmap ladyroach1;
	public static Bitmap ladyroach2;
	public static Bitmap ladyroach3;
	public static Bitmap lifebit;
	static SuperBug superbug;
}
