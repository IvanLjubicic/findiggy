package com.dia.findiggy;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Email extends Activity implements OnClickListener{
	
	EditText subject, message;
	String sub, mess;
	Button send;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.email);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		subject = (EditText) findViewById (R.id.etSubject);
		message = (EditText) findViewById (R.id.etMessage);
		send = (Button) findViewById (R.id.bSend);
		send.setOnClickListener(this);
		
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		sub = subject.getText().toString();
		mess = message.getText().toString();
		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"team.dia.soft@gmail.com"});
		emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, sub);
		emailIntent.setType("plain/text");
		emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, mess);
		try {
			startActivity(Intent.createChooser(emailIntent, "Send your email in:"));  
			finish();
		}catch (android.content.ActivityNotFoundException ex) {
			Toast.makeText(this, "There is no mail clients installed", Toast.LENGTH_LONG).show();
		}
	}

}
