package com.dia.findiggy;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class CouponBonds extends Activity implements OnClickListener,
		OnSeekBarChangeListener {

	EditText fvalue, cvalue, mdate, interest, cPA;
	TextView solution;
	SeekBar sbInterest;
	Button solve, info;
	float intrate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.couponbonds);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		fvalue = (EditText) findViewById(R.id.etFaceValue);
		cvalue = (EditText) findViewById(R.id.etCouponValue);
		sbInterest = (SeekBar) findViewById(R.id.sbInterest);
		sbInterest.setFocusable(false);
		interest = (EditText) findViewById(R.id.etInterestRate);
		interest.setEnabled(false);
		mdate = (EditText) findViewById(R.id.etMDate);
		cPA = (EditText) findViewById(R.id.etCouponsPA);
		solve = (Button) findViewById(R.id.bSolve);
		solution = (TextView) findViewById(R.id.tvSolution);
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
			if (fvalue.getText().toString().matches("")
					|| cvalue.getText().toString().matches("")
					|| mdate.getText().toString().matches("")
					|| interest.getText().toString().matches("")
					|| cPA.getText().toString().matches("")) {
				Toast.makeText(this,
						"Please enter value(s) that is/are missing!",
						Toast.LENGTH_LONG).show();
				solution.setText("Click 'Solve' for solution");
				return;
			}
			calculate();
			break;
		case R.id.bInfo:
			for (int i = 0; i < 2; i++)
				 Toast.makeText(this, "A debt obligation with coupons attached that represent semiannual interest payments. Also known as a bearer bond.",Toast.LENGTH_LONG).show();
			break;
		}
	}

	private void calculate() {
		// TODO Auto-generated method stub
		int cpa;
		float f, cv, t, solut;
		solut = 0;
		t = Float.parseFloat(mdate.getText().toString());
		f = Float.parseFloat(fvalue.getText().toString());
		cpa = Integer.parseInt(cPA.getText().toString());
		cv = Float.parseFloat(cvalue.getText().toString());
		for (int i = 1; i <= t; i++) {
			if (i == t) {
				solut += f / (Math.pow(Math.E, i * cpa * intrate / 100));
			}
			solut += cv / (Math.pow(Math.E, i * cpa * intrate / 100));
		}

		solution.setText("Bond value is " + solut
				+ " of a currency you used.");
	}

	public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
		// TODO Auto-generated method stub
		intrate = (float) (progress / 10.0);
		interest.setText(intrate + " %");
	}

	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

}
