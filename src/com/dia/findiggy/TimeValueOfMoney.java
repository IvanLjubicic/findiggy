package com.dia.findiggy;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TimeValueOfMoney extends ListActivity {
	
	String classes[] = {"SimpleInterest", "PeriodicCompounding", "ContinuousCompounding", 
						"StreamsOfPayments"};
	String menulist[] = {"Simple Interest", "Periodic Compounding", "Continuous Compounding", 
						"Streams Of Payments"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
					WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setListAdapter(new ArrayAdapter<String>(TimeValueOfMoney.this, android.R.layout.simple_list_item_1, menulist));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		String cheese = classes[position];
		try {
			Class ourclass = Class.forName("com.dia.findiggy." + cheese);
			Intent ourIntent = new Intent(TimeValueOfMoney.this,ourclass);
			startActivity(ourIntent);
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	



}
