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
	DBemployee dbTools = new DBemployee(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pin);
		
		if (dbTools.getAllEmployees().size() == 0) {
			Intent initial = new Intent("com.tjarita.dogdaycare.ADDEMPLOYEES");
			initial.putExtra("first", true);
			startActivity(initial);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		final EditText password = (EditText) findViewById(R.id.pin_password);
		password.setText("");
		
		
	
		
		Button enter = (Button) findViewById(R.id.pin_enter);
		enter.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (password.getText().length() != 0
						&& dbTools.checkPIN(Integer.parseInt(password.getText()
						.toString()))) {
					Intent okay = new Intent("com.tjarita.dogdaycare.ENTRY");
					HashMap<String, String> employee = dbTools
							.getEmployeeInfo(password.getText().toString());
					
					dbTools.close();
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
