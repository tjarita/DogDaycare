/*
 * 	Adds Employees + Customers
 */

package com.tjarita.dogdaycare;

import java.util.HashMap;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddEmployees extends Activity {

	DBemployee dbTools = new DBemployee(this);
	int id;
	HashMap<String, String> update;
	Intent intent;

	EditText first, last, email, phone, address;
	TextView employeeID, employeeText;
	CheckBox admin;
	Button save, remove, animal;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dbtools_add_employee);
		initialize();

		id = randomInt(1000, 1999);

		// ----Initial setup----
		if (intent.hasExtra("first"))
			admin.setChecked((true));
		if (intent.hasExtra("customer"))
			admin.setVisibility(View.GONE);
		if (intent.hasExtra("customer")) {
			id = randomInt(10000, 99999);
			admin.setVisibility(View.GONE);
		}
		if (intent.hasExtra("add")) {
			animal.setVisibility(View.GONE);
		}

	}

	@Override
	protected void onResume() {
		super.onResume();

		// ----Update Info----
		if (intent.hasExtra("info")) {
			id = Integer.parseInt(update.get("ID"));
			if (update.get("admin").contains("1")) {
				admin.setChecked(true);
			}
			last.setText(update.get("lastName"));
			first.setText(update.get("firstName"));
			email.setText(update.get("email"));
			phone.setText(update.get("phoneNumber"));
			address.setText(update.get("home"));
		}

		employeeID.setText(Integer.toString(id));

		save.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				HashMap<String, String> info = new HashMap<String, String>();
				if (admin.isChecked()) {
					info.put("admin", "1");
					info.put("employee", "1");
					info.put("customer", "0");

				} else if (intent.hasExtra("customer")) {
					info.put("admin", "0");
					info.put("employee", "0");
					info.put("customer", "1");
				} else {
					info.put("admin", "0");
					info.put("employee", "1");
					info.put("customer", "0");
				}

				info.put("ID", Integer.toString(id));
				info.put("firstName", first.getText().toString());
				info.put("lastName", last.getText().toString());
				info.put("phoneNumber", phone.getText().toString());
				info.put("email", email.getText().toString());
				info.put("home", address.getText().toString());

				if (intent.getBooleanExtra("update", false))
					dbTools.updateEmployee(info);
				else
					dbTools.insertEmployee(info);

				finish();
			}
		});

		remove.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dbTools.deleteEmployee(Integer.toString(id));
				finish();
			}
		});

		animal.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent listAnimal = new Intent(
						"com.tjarita.dogdaycare.LISTANIMALS");

				listAnimal.putExtra("info", update); // Sending employees
															// table

				// Toast.makeText(getApplicationContext(),
				// update.get("ID"),
				// Toast.LENGTH_SHORT).show();
				startActivity(listAnimal);
			}
		});

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		dbTools.close();
	}

	private void initialize() {
		first = (EditText) findViewById(R.id.addEmp_first);
		last = (EditText) findViewById(R.id.addEmp_last);
		email = (EditText) findViewById(R.id.addEmp_email);
		phone = (EditText) findViewById(R.id.addEmp_phone);
		address = (EditText) findViewById(R.id.addEmp_address);
		admin = (CheckBox) findViewById(R.id.addEmp_manager);
		employeeID = (TextView) findViewById(R.id.addEmp_ID);
		employeeText = (TextView) findViewById(R.id.addEmp_text_ID);
		remove = (Button) findViewById(R.id.addEmp_delete);
		save = (Button) findViewById(R.id.addEmp_save);
		animal = (Button) findViewById(R.id.addEmp_animal);

		// ----Get selected info from ListEmployee----
		intent = getIntent();
		update = (HashMap<String, String>) intent.getSerializableExtra("info");

		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

	}

	private int randomInt(int min, int max) {
		Random rng = new Random();
		int rn;
		do {
			rn = rng.nextInt((max - min) + 1) + min;
		} while (dbTools.checkPIN(rn)); // createPIN returns true if ID already
										// exists
		return rn;
	}

}
