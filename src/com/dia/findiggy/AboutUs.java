package com.dia.findiggy;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class AboutUs extends Activity implements OnClickListener{
	
	Button email, donate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aboutus);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		email = (Button) findViewById(R.id.bEmail);
		email.setOnClickListener(this);
		donate = (Button) findViewById(R.id.bDonate);
		donate.setOnClickListener(this);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
		
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bEmail:
			Class ourclass = null;
			try {
				ourclass = Class.forName("com.dia.findiggy.Email");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Intent ourIntent = new Intent(AboutUs.this,ourclass);
			startActivity(ourIntent);
			break;
		case R.id.bDonate:
			try {
				Class support = Class.forName("com.dia.findiggy.Support");
				Intent supp = new Intent(AboutUs.this,support);
				startActivity(supp);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Toast.makeText(this, "Available in next version!", Toast.LENGTH_LONG).show();
			break;
		}
		
	}
	
	

}
