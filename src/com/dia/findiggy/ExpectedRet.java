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

public class ExpectedRet extends Activity implements OnClickListener{
	
	EditText input;
	Button add, check, del, solve, info;
	TextView solution;
	List<Float> inputs = new ArrayList<Float>();
	int flag = 1;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expectret);
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
			if (flag == 1) {
				input.setHint("...and it's probability");
				flag = 0;
			}else if (flag == 0){
				input.setHint("Enter possible return");
				flag = 1;
			}
			inputs.add(Float.parseFloat(input.getText().toString()));
			input.setText("");
			break;
		 case R.id.bCheck:
			 if (inputs.size() %2 != 0) {
				 Toast.makeText(this, "Please enter the possibility", Toast.LENGTH_LONG).show();
				 break;
			 }else if(inputs.size() == 0){
				 Toast.makeText(this, "There are no entered inputs", Toast.LENGTH_LONG).show();
				 break;
			 }
			 String print = "";
			 int j = 0;
			 for (int i = 0; i<inputs.size()-1; i = i + 2) {
				 j++;
				 print = print + "Possible return in year " + j 
						 + " is " + inputs.get(i).toString()
						 + "% " + " with probability of "
						 + inputs.get(i+1).toString() + "%. ";	 
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
				 Toast.makeText(this, "Please enter the possibility", Toast.LENGTH_LONG);
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
				 Toast.makeText(this, "The average of a probability distribution of possible returns.",Toast.LENGTH_LONG).show();
			 break;
		}
		
	}

	private void calculate() {
		// TODO Auto-generated method stub
		float sum = 0.0f;
		double variance = 0.0f;
		double stdev, coefvar;
		for (int i = 0; i<inputs.size(); i = i + 2){
			sum = sum + (inputs.get(i)*inputs.get(i+1));
		}
		for (int i = 0; i<inputs.size(); i = i + 2){
			variance = variance + (inputs.get(i+1)/100.0)*Math.pow((inputs.get(i)/100.0 - sum/10000.0), 2.0f);
		}
		stdev = Math.sqrt(variance);
		coefvar = stdev/(sum/10000.0);
		solution.setText("Expected return is " + (sum/100) + 
				"%.\nVariance is " + variance + 
				".\nStandard deviation is " + (stdev*100) + 
				"%.\nCoefficient of variation is " + coefvar);
	}

}

