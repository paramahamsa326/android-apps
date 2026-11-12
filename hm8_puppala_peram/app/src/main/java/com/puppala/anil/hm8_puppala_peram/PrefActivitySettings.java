package com.puppala.anil.hm8_puppala_peram;

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

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

/*___________________________________________________________________
|
| Class: PrefsFragmentSettings
|__________________________________________________________________*/
public class PrefActivitySettings extends PreferenceFragment 
{
    final static String TAG = "PrefActivitySettings";
	public SharedPreferences sharedPreferences;
	MainActivity mainActivity=new MainActivity();
    
	/*___________________________________________________________________
	|
	| Function: PrefsFragmentSettings (constructor) 
	|__________________________________________________________________*/
    public PrefActivitySettings () 
    { 
    }
/*    public void SaveInt(String key, int value){
    	Preference pref;
    	pref = getPreferenceScreen().findPreference("high_Score");
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getActivity());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("Assets.highScore", Assets.highScore);
        editor.commit();
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
        addPreferencesFromResource(R.xml.pref_activity_settings);
    }
	
	/*___________________________________________________________________
	|
	| Function: onResume 
	|__________________________________________________________________*/
    @Override
    public void onResume() 
    {
    	super.onResume();   
    	SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
	    	Preference sharedpref;
	    	int highscore;
	    	sharedpref = getPreferenceScreen().findPreference("high_score");
	    	highscore = sharedPreferences.getInt("highScore",0);
	    	
	    	sharedpref.setSummary(String.valueOf(highscore));
	    	
    }

	/*___________________________________________________________________
	|
	| Function: onSharedPreferenceChanged 
	|__________________________________________________________________*/
}
