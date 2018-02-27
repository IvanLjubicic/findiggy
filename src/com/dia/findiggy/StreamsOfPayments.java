package com.dia.findiggy;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class StreamsOfPayments extends Activity implements OnClickListener, OnCheckedChangeListener, OnSeekBarChangeListener{
	
	RadioGroup selectionList;
	EditText amount, interest, years;
	TextView tvAmount, solution;
	SeekBar sbInterest;
	Button solve, info;
	float intrate;
	
	boolean flag = true;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.streamsofpayments);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		selectionList = (RadioGroup) findViewById(R.id.radioGroup1);
		amount = (EditText) findViewById(R.id.etAAmount);
		interest = (EditText) findViewById(R.id.etInterestRate);
		interest.setEnabled(false);
		years = (EditText) findViewById(R.id.etYears);
		tvAmount = (TextView) findViewById(R.id.tvAAmount);
		sbInterest = (SeekBar) findViewById(R.id.sbInterest);
		solve = (Button) findViewById(R.id.bSolve);
		solution = (TextView) findViewById(R.id.tvSolution);
		selectionList.setOnCheckedChangeListener(this);
		solve.setOnClickListener(this);
		info = (Button) findViewById(R.id.bInfo);
		info.setOnClickListener(this);
		sbInterest.setMax(1100);
		sbInterest.setProgress(550);
		sbInterest.setOnSeekBarChangeListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bSolve:
		if (amount.getText().toString().matches("") || 
			interest.getText().toString().matches("") ||
			years.getText().toString().matches("")) {
				Toast.makeText(this, "Please enter value(s) that is/are missing!", Toast.LENGTH_LONG).show();
				solution.setText("Click 'Solve' for solution");
				return;
			}
		if (flag)
			calculateFuture();
		else
			calculatePresent();
			break;
		case R.id.bInfo:
			for (int i = 0; i < 2; i++)
				 Toast.makeText(this, "Check on http://en.wikipedia.org/wiki/Time_value_of_money",Toast.LENGTH_LONG).show();
			break;
		}
	}

	private void calculateFuture() {
		// TODO Auto-generated method stub
		float a, t;
		double solut;
		t = Float.parseFloat(years.getText().toString());
		a = Float.parseFloat(amount.getText().toString());
		solut = a * (1+intrate/100) * (Math.pow(1+intrate/100, t) - 1)/(intrate/100);
		solution.setText("Final value of streams of payments is " + solut + " of a currency you used.");
	}

	private void calculatePresent() {
		// TODO Auto-generated method stub
		float a, t;
		double solut;
		t = Float.parseFloat(years.getText().toString());
		a = Float.parseFloat(amount.getText().toString());
		solut = a / Math.pow(1 + intrate/100, t) * (Math.pow(1+intrate/100, t) - 1) /(intrate/100);
		solution.setText("Present value of streams of payments is " + solut + " of a currency you used.");	
	}

	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		switch (checkedId) {
		case R.id.rtFutureValue:
			flag = true;
			break;
		case R.id.rtPresentValue:
			flag = false;
			break;
		}
		
	}

	public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
		// TODO Auto-generated method stub
		intrate = (float) (progress/10.0);
		interest.setText(intrate + " %");
	}

	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

}

