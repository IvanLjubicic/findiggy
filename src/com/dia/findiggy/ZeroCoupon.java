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

public class ZeroCoupon extends Activity implements OnClickListener,
		OnCheckedChangeListener, OnSeekBarChangeListener {

	RadioGroup selectionList;
	EditText fvalue, interest, pvalue;
	TextView tvPrincipal, solution;
	SeekBar sbInterest;
	Button solve, info;
	float intrate;

	int flag = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zerocoupon);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		selectionList = (RadioGroup) findViewById(R.id.radioGroup1);
		fvalue = (EditText) findViewById(R.id.etFaceValue);
		sbInterest = (SeekBar) findViewById(R.id.sbInterest);
		interest = (EditText) findViewById(R.id.etInterestRate);
		interest.setEnabled(false);
		pvalue = (EditText) findViewById(R.id.etPValue);
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
			if (flag == 0) {
				if (fvalue.getText().toString().matches("")
						|| pvalue.getText().toString().matches("")) {
					Toast.makeText(this,
							"Please enter value(s) that is/are missing!",
							Toast.LENGTH_LONG).show();
					solution.setText("Click 'Solve' for solution");
					return;
				}
				calculateI();
			} else if (flag == 1) {
				if (fvalue.getText().toString().matches("")
						|| interest.getText().toString().matches("")) {
					Toast.makeText(this,
							"Please enter value(s) that is/are missing!",
							Toast.LENGTH_LONG).show();
					solution.setText("Click 'Solve' for solution");
					return;
				}
				calculateP();
			} else if (flag == 2) {
				if (interest.getText().toString().matches("")
						|| pvalue.getText().toString().matches("")) {
					Toast.makeText(this,
							"Please enter value(s) that is/are missing!",
							Toast.LENGTH_LONG).show();
					solution.setText("Click 'Solve' for solution");
					return;
				}
				calculateF();
			}
			break;
		case R.id.bInfo:
			for (int i = 0; i < 2; i++)
				 Toast.makeText(this, "Check on http://en.wikipedia.org/wiki/Zero-coupon_bond",Toast.LENGTH_LONG).show();
			break;
		}
	}

	private void calculateI() {
		// TODO Auto-generated method stub
		interest.setText("");
		float f, p, solut;
		f = Float.parseFloat(fvalue.getText().toString());
		p = Float.parseFloat(pvalue.getText().toString());
		solut = (f / p - 1) / 100;
		solution.setText("Interest rate of the bond is " + solut + " %");
	}

	private void calculateF() {
		// TODO Auto-generated method stub
		fvalue.setText("");
		float p, solut;
		p = Float.parseFloat(pvalue.getText().toString());
		solut = p * (1 + intrate / 100);
		solution.setText("Face value of the bond is " + solut);
	}

	private void calculateP() {
		// TODO Auto-generated method stub
		pvalue.setText("");
		float f, solut;
		f = Float.parseFloat(fvalue.getText().toString());
		solut = f / (1 + intrate / 100);
		solution.setText("Present value of the bond is " + solut);

	}

	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		switch (checkedId) {
		case R.id.rtFindR:
			flag = 0;
			break;
		case R.id.rtFindP:
			flag = 1;
			break;
		case R.id.rtFindF:
			flag = 2;
			break;
		}

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
