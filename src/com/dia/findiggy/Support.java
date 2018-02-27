package com.dia.findiggy;



import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class Support extends Activity{
	
	WebView ourBrow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.support);
		
		ourBrow = (WebView) findViewById(R.id.wvBrowser);
		ourBrow.getSettings().setJavaScriptEnabled(true);
		ourBrow.getSettings().setLoadWithOverviewMode(true);
		ourBrow.getSettings().setUseWideViewPort(true);
		ourBrow.setWebViewClient(new OurViewClient());
		
		try{
			ourBrow.loadUrl("https://www.paypal.com");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
