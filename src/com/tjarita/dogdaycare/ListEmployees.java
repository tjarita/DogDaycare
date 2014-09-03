package com.tjarita.dogdaycare;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListEmployees extends ListActivity {

	TextView employeeID, title;
	Button add;
	DBemployee dbTools = new DBemployee(this);
	Intent i = new Intent("com.tjarita.dogdaycare.ADDEMPLOYEES");

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dbtools);
		initialize();
	}

	@Override
	protected void onResume() {
		super.onResume();
		Intent intent = getIntent();

		// ----Add Employee----
		add.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent goAdd = new Intent("com.tjarita.dogdaycare.ADDEMPLOYEES");
				startActivity(goAdd);
			}
		});

		// ---- Retrive Employee or Customer List----
		final ArrayList<HashMap<String, String>> employeeList;
		if (intent.hasExtra("customer")) {
			employeeList = dbTools.getAllCustomers();
			add.setVisibility(View.GONE);
			title.setText("Customer List");
			i.putExtra("customer", true);
		} else {
			employeeList = dbTools.getAllEmployees();
			title.setText("Employee List");
		}

		// ----Populate List----
		if (employeeList.size() != 0) {
			ArrayList<String> names = new ArrayList<String>();

			// ----Get Names---- Note: Could use a custom adapter to avoid
			// copying array
			for (int i = 0; i < employeeList.size(); i++) {
				names.add(employeeList.get(i).get("lastName").toString() + ", "
						+ employeeList.get(i).get("firstName").toString());
			}

			this.setListAdapter(new ArrayAdapter<String>(this,
					R.layout.dbtools_entry, R.id.employeeName, names));

			ListView lv = getListView();
			lv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {

					// toast selected employee name
					// Toast.makeText(
					// getApplicationContext(),
					// employeeList.get(position).get("lastName")
					// + ", "
					// + employeeList.get(position)
					// .get("firstName").toString(),
					// Toast.LENGTH_SHORT).show();

					i.putExtra("info", employeeList.get(position)); // Sending
																	// employees
																	// table
					i.putExtra("update", true);
					startActivity(i);

				}

			});
		}
		dbTools.close();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	private void initialize() {
		title = (TextView) findViewById(R.id.dbtools_title);
		add = (Button) findViewById(R.id.listEmp_Add);
	}

}
