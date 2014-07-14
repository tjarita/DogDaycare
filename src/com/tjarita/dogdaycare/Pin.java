package com.tjarita.dogdaycare;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Pin extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pin);
	}

	@Override
	protected void onResume() {
		super.onResume();
		final DBemployee dbtools = new DBemployee(this);
		final EditText password = (EditText) findViewById(R.id.pin_password);
		password.setText("");
		
		
		if(dbtools.getAllEmployees().size() == 0){
			Intent initial = new Intent("com.tjarita.dogdaycare.ADDEMPLOYEES");
			initial.putExtra("first", true);
			startActivity(initial);
		}
		
		Button enter = (Button) findViewById(R.id.pin_enter);
		enter.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(dbtools.checkPIN( Integer.parseInt(password.getText().toString()) )){
					Intent okay = new Intent("com.tjarita.dogdaycare.ENTRY");
					HashMap<String,String> employee = dbtools.getEmployeeInfo(password.getText().toString());
					
					okay.putExtra("admin", employee.get("admin"));
					okay.putExtra("PIN", Integer.parseInt(password.getText().toString()));
					startActivity(okay);
				}
				else
					Toast.makeText(getApplicationContext(), "Bad PIN", Toast.LENGTH_SHORT).show();
			}
		});
		
	}

	
	
}
