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

public class MoneyMarket extends ListActivity {
	
	String classes[] = {"ZeroCoupon", "CouponBonds"};
	String menulist[] = {"Zero-Coupon Bonds", "Coupon Bonds"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
					WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setListAdapter(new ArrayAdapter<String>(MoneyMarket.this, android.R.layout.simple_list_item_1, menulist));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		String cheese = classes[position];
		try {
			Class ourclass = Class.forName("com.dia.findiggy." + cheese);
			Intent ourIntent = new Intent(MoneyMarket.this,ourclass);
			startActivity(ourIntent);
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	



}