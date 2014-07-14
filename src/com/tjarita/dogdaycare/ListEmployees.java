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

	TextView employeeID;

	DBemployee dbTools = new DBemployee(this);

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dbtools);

		// ----Add Employee----
		Button add = (Button) findViewById(R.id.listEmp_Add);
		add.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent goAdd = new Intent("com.tjarita.dogdaycare.ADDEMPLOYEES");
				startActivity(goAdd);
			}
		});

	}

	@Override
	protected void onResume() {
		super.onResume();

		final ArrayList<HashMap<String, String>> employeeList = dbTools
				.getAllEmployees();

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

					// selected Employee
					final String empName = employeeList.get(position)
							.get("lastName").toString()
							+ ", "
							+ employeeList.get(position).get("firstName")
									.toString();

					// toast Name
					Toast.makeText(
							getApplicationContext(),
							employeeList.get(position).get("lastName")
									+ ", "
									+ employeeList.get(position)
											.get("firstName").toString(),
							Toast.LENGTH_SHORT).show();

					Intent i = new Intent(
							"com.tjarita.dogdaycare.ADDEMPLOYEES");
					i.putExtra("info", employeeList.get(position));
					i.putExtra("update", true);
					startActivity(i);

				}

			});
		}
	}

}
