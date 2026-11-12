package com.puppala.anil.hm7_puppala;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String DEBUG_LOG="ProjectLogging";
    ImageView imageView1;
    ImageButton button1, button2;
    Button button;;
    SoundPool sp=null;
    SoundPool click=null;
    int imageIndex=0;
    int audioIndex=0;
    int clickIndex=0;
    String s=null, toaster=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i (DEBUG_LOG, "Creating Activity");
        imageView1 = (ImageView) findViewById (R.id.imageView);
        button1 =(ImageButton)findViewById ( R.id.imageButton);
        button2 =(ImageButton)findViewById ( R.id.imageButton2);
        button=(Button)findViewById(R.id.infobutton);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button.setOnClickListener(this);
        imageView1.setOnClickListener(this);
        playAudio(R.raw.forestmp);
        sp=new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        click=new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        clickIndex=click.load(this,R.raw.clickb,1);
    }
    @Override
    public void onClick(View v) {

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        boolean b = prefs.getBoolean("prefs_animalsound_enabled", true);
        if (v == button1){
            if (b == false) ;
            else  click.play(clickIndex,1,1,1,0,1);
            Log.i (DEBUG_LOG, "Clicked button1 or previous button");
            imageIndex--;
            if(imageIndex<1) imageIndex=5;
            drawImage();
        }
        else if (v == button2){
            click.play(clickIndex,1,1,1,0,1);
            Log.i(DEBUG_LOG, "Clicked button2 or next button");
            imageIndex++;
            if(imageIndex>5) imageIndex=1;
            drawImage();
        }else if (v == button){
            Log.i(DEBUG_LOG, "Clicked info button");
            //if(imageIndex==0)
                startActivity (new Intent(this, PrefsActivity.class));
            //else
            //   launchBrowser();
        }
        else if (v==imageView1){
            if (b == false) {
                Toast.makeText(getApplicationContext(),toaster,Toast.LENGTH_SHORT).show();
                return;
            }
            sp.play(audioIndex, 1, 1, 1, 0, 1);
        }
    }
    @Override
    public void onPause()
    {
        super.onPause();
        Assets.mp.stop();
        Log.i (DEBUG_LOG, "Activity paused");
    }
    @Override
    public void onResume()
    {
        super.onResume();
        playAudio(R.raw.forestmp);
        SoundPool sp = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        if (sp == null)
            Log.i("ProjectLogging", "Error initializing SoundPool");
        Log.i (DEBUG_LOG, "Activity resumed");
        drawImage();
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState!=null){
            imageIndex = savedInstanceState.getInt("imageIndex");
            Assets.mp.start();
            drawImage();
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("imageIndex", imageIndex);
    }
    @Override
    public void onBackPressed () {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle ("Closing Application")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }


    public void  drawImage()
    {
        switch(imageIndex){
            case 1:
                toaster="Toger Roaring";
                s="https://en.wikipedia.org/?title=Tiger";
                audioIndex=sp.load(this,R.raw.tiger, 1);
                imageView1.setImageResource(R.drawable.tiger);
                Log.i (DEBUG_LOG, "Tiger Activity");
                break;
            case 2:
                toaster="Wolf Howling";
                s="https://en.wikipedia.org/wiki/Gray_wolf";
                audioIndex=sp.load(this,R.raw.wolf_x, 1);
                imageView1.setImageResource(R.drawable.wolf);
                Log.i (DEBUG_LOG, "Wolf Activity");
                break;
            case 3:
                toaster="Elephant Trumpeting";
                s="https://en.wikipedia.org/wiki/Elephant";
                audioIndex=sp.load(this,R.raw.elephant, 1);
                imageView1.setImageResource(R.drawable.elephant);
                Log.i (DEBUG_LOG, "Elephant Activity");
                break;
            case 4:
                toaster="Dog Barking";
                s="https://sh.wikipedia.org/wiki/%C5%BDirafa";
                audioIndex=sp.load(this,R.raw.dogbark, 1);
                imageView1.setImageResource(R.drawable.dog);
                Log.i (DEBUG_LOG, "Dog Activity");
                break;
            case 5:
                toaster="Goat Bleat";
                s="https://en.wikipedia.org/?title=Lion";
                audioIndex=sp.load(this,R.raw.goat, 1);
                imageView1.setImageResource(R.drawable.goat);
                Log.i (DEBUG_LOG, "Lion Activity");
                break;
        }
    }
    private void playAudio(int  resid) {
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        boolean b = prefs.getBoolean("prefs_music_enabled", true);
        if (b == false)
            return;

        if (Assets.mp != null) {
            Assets.mp.release();
            Assets.mp = null;
        }

        Assets.mp= MediaPlayer.create(this, resid);

        try{
            Assets.mp.start();
            Assets.mp.setLooping(true);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void launchBrowser() {
        //Uri uriUrl = Uri.parse(s);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse(s));
        startActivity(launchBrowser);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            startActivity (new Intent(this, PrefsActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}