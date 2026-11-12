/*___________________________________________________________________
|
| File: PrefsFragmentSettings.java
|
| Functions:	PrefsFragmentSettings (constructor)
|				onCreate
|				onResume
|
| Copyright 2015 Abonvita Software LLC.
|__________________________________________________________________*/
package com.puppala.anil.hm7_puppala;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.Preference.OnPreferenceClickListener;
import android.util.Log;
 
/*___________________________________________________________________
|
| Class: PrefsFragmentSettings
|__________________________________________________________________*/
public class PrefsFragmentSettings extends PreferenceFragment implements OnSharedPreferenceChangeListener 
{
    final static String TAG = "PrefsFragmentSettings";
    
	/*___________________________________________________________________
	|
	| Function: PrefsFragmentSettings (constructor) 
	|__________________________________________________________________*/
    public PrefsFragmentSettings () 
    { 
    }
    
	/*___________________________________________________________________
	|
	| Function: onCreate 
	|__________________________________________________________________*/
	@Override
    public void onCreate (Bundle savedInstanceState) 
    {
    	super.onCreate(savedInstanceState);   	
    	// Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.prefs_fragment_settings);
    }
	
	/*___________________________________________________________________
	|
	| Function: onResume 
	|__________________________________________________________________*/
    @Override
    public void onResume() 
    {
    	super.onResume();

	    // Set up a listener whenever a key changes 
	    getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this); 

    	// Set up a click listener
    	Preference pref;
    	pref = getPreferenceScreen().findPreference("key_company_info");
	    pref.setOnPreferenceClickListener(new OnPreferenceClickListener() { 
	        public boolean onPreferenceClick (Preference preference) { 
	        	// Handle action on click here 
	        	try {
	   				Uri site = Uri.parse("http://www.houstonzoo.org/");
	   		        Intent myIntent = new Intent(Intent.ACTION_VIEW, site); 
	   		        startActivity(myIntent);
	   			}
	   			catch (Exception e) {
	   				Log.e (TAG, "Browser failed", e);
	   			}
	   			return (true);
	        } 
	    });
		Preference prefs;
		prefs = getPreferenceScreen().findPreference("share_the_app");
		prefs.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			public boolean onPreferenceClick (Preference preference) {
				Intent email = new Intent(Intent.ACTION_SEND);
				email.putExtra(Intent.EXTRA_EMAIL, new String[]{"apuppala@lamar.edu"});
				email.putExtra(Intent.EXTRA_SUBJECT, "Install the best Android App The Zoo - Suggested by a friend ");
				email.putExtra(Intent.EXTRA_TEXT, "Hello Friend \n I am using The Zoo Android application from many days. It is really a good application. Install it in your Android smart phone through the below link. \n https://play.google.com/store/apps/details?id=com.teamlava.zoostory \n \nRegards,\nYour Friend");
				email.setType("message/rfc822");
				startActivity(Intent.createChooser(email, "Choose an Email client :"));
				return (true);
			}
	});
}

	/*___________________________________________________________________
	|
	| Function: onSharedPreferenceChanged 
	|__________________________________________________________________*/
	public void onSharedPreferenceChanged (SharedPreferences sharedPreferences, String key)  
	{ 	
		if (key.equals("prefs_music_enabled")) {		
			boolean b = sharedPreferences.getBoolean("prefs_music_enabled", true);
			if (b == false) {
				if (Assets.mp != null)
					Assets.mp.setVolume(0, 0);
			}
			else {
				if (Assets.mp != null)
					Assets.mp.setVolume(1, 1);				
			}
		}
		if (key.equals("prefs_animalsound_enabled")) {		
			boolean b = sharedPreferences.getBoolean("prefs_animalsound_enabled", true);
			if (b == false) {
				if (Assets.mp != null)
					Assets.mp.setVolume(0, 0);
			}
			else {
				if (Assets.mp != null)
					Assets.mp.setVolume(1, 1);				
			}
		}
	}
}
