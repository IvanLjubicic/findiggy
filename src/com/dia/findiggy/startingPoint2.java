package com.dia.findiggy;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class startingPoint2 extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.startingpoint2);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		Thread timer = new Thread() {
			public void run() {
				try {
					sleep(3000);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}finally {
					Intent openStartingPoint = new Intent("com.dia.findiggy.MENU"); 
					startActivity(openStartingPoint);
					
				}
			}
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
		
	}
	
	

}