package com.tjarita.dogdaycare;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ViewSingleEmployee extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.single_employee_info);

		TextView id = (TextView) findViewById(R.id.single_emp_text_ID);
		TextView last = (TextView) findViewById(R.id.single_emp_text_last);
		TextView first = (TextView) findViewById(R.id.single_emp_text_first);
		TextView email = (TextView) findViewById(R.id.single_emp_text_email);
		TextView phone = (TextView) findViewById(R.id.single_emp_text_phone);
		TextView address = (TextView) findViewById(R.id.single_emp_text_home);

		Intent i = getIntent();
		HashMap<String, String> info = (HashMap<String, String>) i
				.getSerializableExtra("info");

		id.setText(info.get("employeeID"));
		last.setText(info.get("lastName"));
		first.setText(info.get("firstName"));
		email.setText(info.get("email"));
		phone.setText(info.get("phoneNumber"));
		address.setText(info.get("home"));

		Toast.makeText(getApplicationContext(),
				info.get("email").toString(), Toast.LENGTH_SHORT).show();

	}

	@Override
	protected void onResume() {
		super.onResume();

	}
}