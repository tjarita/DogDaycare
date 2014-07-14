package com.tjarita.dogdaycare;

import java.util.HashMap;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddEmployees extends Activity {
	DBemployee dbtools = new DBemployee(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dbtools_add);

	}

	@Override
	protected void onResume() {
		super.onResume();

		final EditText first = (EditText) findViewById(R.id.addEmp_first);
		final EditText last = (EditText) findViewById(R.id.addEmp_last);
		final EditText email = (EditText) findViewById(R.id.addEmp_email);
		final EditText phone = (EditText) findViewById(R.id.addEmp_phone);
		final EditText address = (EditText) findViewById(R.id.addEmp_address);
		final CheckBox admin = (CheckBox) findViewById(R.id.addEmp_manager);
		final TextView employeeID = (TextView) findViewById(R.id.addEmp_ID);
		final TextView employeeText = (TextView) findViewById(R.id.addEmp_text_ID);

		// ----Create temp ID----
		int temp = randomInt(1000, 9999);
		employeeID.setText(Integer.toString(temp));

		// ----Update Employee----
		final Intent intent = getIntent();
		HashMap<String, String> update = (HashMap<String, String>) intent
				.getSerializableExtra("info");

		if (intent.hasExtra("info")) {
			temp = Integer.parseInt(update.get("ID"));
			employeeID.setText(update.get("ID"));
			if (update.get("admin").contains("1")) {
				admin.setChecked(true);
			}
			last.setText(update.get("lastName"));
			first.setText(update.get("firstName"));
			email.setText(update.get("email"));
			phone.setText(update.get("phoneNumber"));
			address.setText(update.get("home"));
		}
		if (intent.hasExtra("first"))
			admin.setChecked((true));
		else {
			admin.setVisibility(View.GONE);
		}

		final int id = temp;
		Button save = (Button) findViewById(R.id.addEmp_save);
		save.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				HashMap<String, String> info = new HashMap<String, String>();
				if (admin.isChecked()) {
					info.put("admin", "1");
					info.put("employee", "1");
					info.put("customer", "0");
					info.put("animal", "0");
				} else if (intent.hasExtra("customer")) {
					info.put("admin", "0");
					info.put("employee", "0");
					info.put("customer", "1");
					info.put("animal", "0");
				} else if (intent.hasExtra("animal")) {
					info.put("admin", "0");
					info.put("employee", "0");
					info.put("customer", "0");
					info.put("animal", "1");
				} else {
					info.put("admin", "0");
					info.put("employee", "1");
					info.put("customer", "0");
					info.put("animal", "0");
				}

				info.put("ID", Integer.toString(id));
				info.put("firstName", first.getText().toString());
				info.put("lastName", last.getText().toString());
				info.put("phoneNumber", phone.getText().toString());
				info.put("email", email.getText().toString());
				info.put("home", address.getText().toString());

				if (intent.getBooleanExtra("update", false))
					dbtools.updateEmployee(info);
				else
					dbtools.insertEmployee(info);

				// Toast.makeText(getApplicationContext(), "ID " +
				// info.get("employeeID").toString(),
				// Toast.LENGTH_SHORT).show();
				// Toast.makeText(getApplicationContext(), "name " +
				// info.get("firstName").toString() +
				// info.get("lastName").toString(),
				// Toast.LENGTH_SHORT).show();
				// Toast.makeText(getApplicationContext(), "phone " +
				// info.get("phoneNumber").toString(),
				// Toast.LENGTH_SHORT).show();
				// Toast.makeText(getApplicationContext(), "email " +
				// info.get("email").toString(),
				// Toast.LENGTH_SHORT).show();
				// Toast.makeText(getApplicationContext(), "home " +
				// info.get("home").toString(),
				// Toast.LENGTH_SHORT).show();
				finish();
			}
		});

		Button delete = (Button) findViewById(R.id.addEmp_delete);
		delete.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dbtools.deleteEmployee(Integer.toString(id));
				finish();
			}
		});

	}

	public int randomInt(int min, int max) {
		Random rng = new Random();
		int rn;
		do {
			rn = rng.nextInt((max - min) + 1) + min;
		} while (dbtools.checkPIN(rn)); // createPIN returns true if ID already
										// exists
		return rn;
	}

}
