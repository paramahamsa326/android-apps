package com.puppala.anil.hm8_puppala_peram;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

	MainView v;

	public SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Assets.mp = null;
		Assets.context = this;
		// Disable the title
		requestWindowFeature (Window.FEATURE_NO_TITLE);
		// Make full screen
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// Start the view
		v = new MainView(this);
		setContentView(v);
	}
	
			
	@Override
	protected void onPause () {
		super.onPause();
		v.pause();
		// save the state of the image
		if (isFinishing())
		    if (Assets.mp != null) {
		    	Assets.mp.release();
		    	Assets.mp = null;
		    }
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("Assets.highScore", Assets.highScore);
        editor.commit();
	}

	@Override
	protected void onResume () {
		super.onResume();
		v.resume();
	}
	PhoneStateListener phoneStateListener = new PhoneStateListener() {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {

            if (state == TelephonyManager.CALL_STATE_RINGING) {
            	if (Assets.mp != null) {
			    	Assets.mp.release();
			    	Assets.mp = null;
            	}
            	v.pause();
                Log.i("Phone", "Ringing");

            } else if (state == TelephonyManager.CALL_STATE_IDLE) {

                Log.i("Phone", "Idle");
            } else if (state == TelephonyManager.CALL_STATE_OFFHOOK) {
                // A call is dialing, active or on hold
                Log.i("Phone", "offhook");
                v.resume();

            }
            super.onCallStateChanged(state, incomingNumber);
        }
    };
}
