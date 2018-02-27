package com.dia.findiggy;

import java.util.ArrayList;
import java.util.List;

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

public class Npv extends Activity implements OnClickListener,
		OnCheckedChangeListener, OnSeekBarChangeListener {

	EditText inflows, discount, outflows;
	TextView solution, outf;
	SeekBar sbDiscount;
	Button solve, addI, checkI, delI, addO, delO, checkO, info;
	float discrate;
	List<Float> infs = new ArrayList<Float>();
	List<Float> outfs = new ArrayList<Float>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.npv);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub

		inflows = (EditText) findViewById(R.id.etInflows);
		addI = (Button) findViewById(R.id.bAddI);
		addI.setOnClickListener(this);
		checkI = (Button) findViewById(R.id.bCheckI);
		checkI.setOnClickListener(this);
		delI = (Button) findViewById(R.id.bDelI);
		delI.setOnClickListener(this);
		outflows = (EditText) findViewById(R.id.etOutflows);
		addO = (Button) findViewById(R.id.bAddO);
		addO.setOnClickListener(this);
		checkO = (Button) findViewById(R.id.bCheckO);
		checkO.setOnClickListener(this);
		delO = (Button) findViewById(R.id.bDelO);
		delO.setOnClickListener(this);
		sbDiscount = (SeekBar) findViewById(R.id.sbDiscount);
		discount = (EditText) findViewById(R.id.etDiscount);
		discount.setEnabled(false);
		outflows = (EditText) findViewById(R.id.etOutflows);
		solve = (Button) findViewById(R.id.bSolve);
		solution = (TextView) findViewById(R.id.tvSolution);
		solve.setOnClickListener(this);
		info = (Button) findViewById(R.id.bInfo);
		info.setOnClickListener(this);
		sbDiscount.setMax(1100);
		sbDiscount.setProgress(550);
		sbDiscount.setOnSeekBarChangeListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bAddI:
			if (inflows.getText().toString().matches(""))
				Toast.makeText(this, "Please enter the value",
						Toast.LENGTH_LONG).show();
			else {
				infs.add(Float.parseFloat(inflows.getText().toString()));
				inflows.setText("");
			}
			break;
		case R.id.bCheckI:
			String print1 = "Incomes:";
			for (int i = 0; i < infs.size(); i++) {
				if (i != infs.size() - 1)
					print1 = print1 + " " + infs.get(i).toString()
							+ " in year " + i + ",";
				else
					print1 = print1 + " " + infs.get(i).toString()
							+ " in year " + i + ".";
			}
			for (int i = 0; i < 2; i++)
				Toast.makeText(this, print1, Toast.LENGTH_LONG).show();
			break;
		case R.id.bDelI:
			if (infs.size() == 0) {
				Toast.makeText(this, "There are no entered incomes",
						Toast.LENGTH_LONG).show();
				break;
			}
			infs.remove(infs.size() - 1);
			break;
		case R.id.bAddO:
			if (outflows.getText().toString().matches(""))
				Toast.makeText(this, "Please enter the value",
						Toast.LENGTH_LONG).show();
			else {
				outfs.add(Float.parseFloat(outflows.getText().toString()));
				outflows.setText("");
			}
			break;
		case R.id.bCheckO:
			String print2 = "Outcomes:";
			for (int i = 0; i < outfs.size(); i++) {
				if (i != outfs.size() - 1)
					print2 = print2 + " " + outfs.get(i).toString()
							+ " in year " + i + ",";
				else
					print2 = print2 + " " + outfs.get(i).toString()
							+ " in year " + i + ".";
			}
			for (int i = 0; i < 2; i++)
				Toast.makeText(this, print2, Toast.LENGTH_LONG).show();
			break;
		case R.id.bDelO:
			if (outfs.size() == 0) {
				Toast.makeText(this, "There are no entered outflows",
						Toast.LENGTH_LONG).show();
				break;
			}
			outfs.remove(outfs.size() - 1);
			break;
		case R.id.bSolve:
			calculate();
			break;
		case R.id.bInfo:
			for (int i = 0; i < 2; i++)
				Toast.makeText(
						this,
						"The difference between the present value of cash inflows and the present value of cash outflows. NPV is used in capital budgeting to analyze the profitability of an investment or project.",
						Toast.LENGTH_LONG).show();
			break;
		}
	}

	private void calculate() {
		// TODO Auto-generated method stub
		if (discount.getText().toString().matches(""))
			Toast.makeText(this, "Please enter the discount", Toast.LENGTH_LONG)
					.show();
		int t = 0;
		float sumP = 0.0f;
		float sumN = 0.0f;
		float sum = 0.0f;
		if (infs.size() == 0) {
			sumP = 0;
		} else {
			for (Float f : infs) {
				sumP += f / (Math.pow(1 + discrate / 100, t));
				t++;
			}
		}
		t = 0;
		if (outfs.size() == 0) {
			sumN = 0;
		} else {
			for (Float f : outfs) {
				sumN += f / (Math.pow(1 + discrate / 100, t));
				t++;
			}
		}
		sum = sumP - sumN;
		solution.setText("Net Present Value is " + sum);
	}

	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub

	}

	public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
		// TODO Auto-generated method stub
		discrate = (float) (progress / 10.0);
		discount.setText(discrate + " %");

	}

	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

}
