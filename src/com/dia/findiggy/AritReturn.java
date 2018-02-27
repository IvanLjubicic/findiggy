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
import android.widget.TextView;
import android.widget.Toast;


public class AritReturn extends Activity implements OnClickListener{
	
	EditText input;
	Button add, check, del, solve, info;
	TextView solution;
	List<Float> inputs = new ArrayList<Float>();
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aritreturn);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		input = (EditText) findViewById(R.id.etInput);
		add = (Button) findViewById(R.id.bAdd);
		add.setOnClickListener(this);
		check = (Button) findViewById(R.id.bCheck);
		check.setOnClickListener(this);
		del = (Button) findViewById(R.id.bDel);
		del.setOnClickListener(this);
		solve = (Button) findViewById(R.id.bSolve);
		solve.setOnClickListener(this);
		info = (Button) findViewById(R.id.bInfo);
		info.setOnClickListener(this);
		solution = (TextView) findViewById(R.id.tvSolution);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) {
		 case R.id.bAdd:
			 if (input.getText().toString().matches("")) {
				Toast.makeText(this, "Please enter the value", Toast.LENGTH_LONG).show();
			 	break;
			}
			inputs.add(Float.parseFloat(input.getText().toString()));
			input.setText("");
			break;
		 case R.id.bCheck:
			 if (inputs.size() %2 != 0) {
				 Toast.makeText(this, "Please enter the final value of investment", Toast.LENGTH_LONG).show();
				 break;
			 }else if(inputs.size() == 0){
				 Toast.makeText(this, "There are no entered inputs", Toast.LENGTH_LONG).show();
				 break;
			 }
			 String print = "";
			 int j = 0;
			 for (int i = 0; i<inputs.size()-1; i = i + 2) {
				 j++;
				 print = print + "Initial value in year " + j 
						 + " is " + inputs.get(i).toString()
						 + ", final value in year " + j + " is "
						 + inputs.get(i+1).toString() + ". ";	 
			}
	
			for (int k = 0; k<2;k++)
				Toast.makeText(this, print, Toast.LENGTH_LONG).show();
			break;
		 case R.id.bDel:
			 if (inputs.size()==0) {
				Toast.makeText(this, "There are no entered inputs", Toast.LENGTH_LONG).show();
				break;
			}
			inputs.remove(inputs.size()-1);
			break; 
		 case R.id.bSolve:
			 if (inputs.size()%2 != 0) {
				 Toast.makeText(this, "Please enter the final value of investment", Toast.LENGTH_LONG);
				 break;
			 }
			 if (inputs.size() == 0) {
				 Toast.makeText(this, "You did not enter any values", Toast.LENGTH_LONG).show();
				 break;
			 }
			 calculate();
			 break;
		 case R.id.bInfo:
			 for (int i = 0; i < 2; i++)
				 Toast.makeText(this, "The rate of return on an investment that is calculated by taking the total cash inflow over the life of the investment and dividing it by the number of years in the life of the investment. The average rate of return does not guarantee that the cash inflows are the same in a given year; it simply guarantees that the return averages out to the average rate.",Toast.LENGTH_LONG).show();
			 break;
		}
		
	}

	private void calculate() {
		// TODO Auto-generated method stub
		float aritret = 0.0f;
		double geomret = 1.0f;
		for (int i = 0; i<inputs.size(); i = i + 2) {
				aritret = aritret + (inputs.get(i+1)/inputs.get(i)-1);
		}
		aritret = aritret/(inputs.size()/2);
		aritret = aritret*100;
		for (int i = 0; i<inputs.size(); i = i + 2) {
			geomret = geomret * (1 +(inputs.get(i+1)/inputs.get(i)-1));
		
		}
		geomret = (Math.pow(geomret, 1.0/(inputs.size()/2))-1)*100;
		solution.setText("Arithmetic average rate of return is " 
						+ aritret +"%.\nGeometric average rate of return is " 
						+ geomret + "%.");
	}

}

