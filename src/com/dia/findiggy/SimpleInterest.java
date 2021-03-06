package com.dia.findiggy;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

public class SimpleInterest extends Activity implements OnClickListener, OnCheckedChangeListener, OnSeekBarChangeListener{
	
	RadioGroup selectionList;
	EditText principal, interest, time;
	TextView tvPrincipal, solution;
	SeekBar sbInterest;
	Button solve, info;
	float intrate;
	
	boolean flag = true;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simpleinterest);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		selectionList = (RadioGroup) findViewById(R.id.radioGroup1);
		principal = (EditText) findViewById(R.id.etPrincipal);
		interest = (EditText) findViewById(R.id.etInterestRate);
		interest.setEnabled(false);
		time = (EditText) findViewById(R.id.etTime);
		tvPrincipal = (TextView) findViewById(R.id.tvPrincipal);
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
			if (principal.getText().toString().matches("") || 
				interest.getText().toString().matches("") ||
				time.getText().toString().matches("")) {
				Toast.makeText(this, "Please enter value(s) that is/are missing!", Toast.LENGTH_LONG).show();
				solution.setText("Click 'Solve' for solution");
				return;
			}
			if (flag) {
			calculateFuture();
			}else {
			calculatePresent();
			}
			break;
		case R.id.bInfo:
			for (int i = 0; i < 2; i++)
				 Toast.makeText(this, "A quick method of calculating the interest charge on a loan. Simple interest is determined by multiplying the interest rate by the principal by the number of periods.",Toast.LENGTH_LONG).show();
			break;
		}
	}

	private void calculateFuture() {
		// TODO Auto-generated method stub
		float t;
		float p, solut;
		t = Float.parseFloat(time.getText().toString());
		p = Float.parseFloat(principal.getText().toString());
		solut = (1 + t*intrate/100)*p;
		solution.setText("Value of investment at time t is " + solut + " of a currency you used.");
	}

	private void calculatePresent() {
		// TODO Auto-generated method stub
		float t;
		float vt, solut;
		t = Float.parseFloat(time.getText().toString());
		vt = Float.parseFloat(principal.getText().toString());
		solut = vt / (1 + intrate/100*t);
		solution.setText("Present or discounted value of V(t) is " + solut + " of a currency you used.");
		
	}

	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		switch (checkedId) {
		case R.id.rtFutureValue:
			tvPrincipal.setText("Principal");
			flag = true;
			break;
		case R.id.rtPresentValue:
			tvPrincipal.setText("Value of investment at time (t)");
			flag = false;
			break;
		}
		
	}

	public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
		// TODO Auto-generated method stub
		interest.setEnabled(false);
		intrate = (float) (progress/10.0);
		interest.setText(intrate + " %");
		interest.setEnabled(true);
	}

	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

}
