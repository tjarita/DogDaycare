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

public class ListAnimals extends ListActivity {

	DBanimal dbTools = new DBanimal(this);
	DBemployee dbToolsEmp = new DBemployee(this);
	Intent goAdd = new Intent("com.tjarita.dogdaycare.ADDANIMAL");
	Intent intent;

	ArrayList<HashMap<String, String>> animalList;
	HashMap<String, String> info;

	TextView title;
	Button add;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dbtools);
		initialize();

	}

	@Override
	protected void onResume() {
		super.onResume();
		ArrayList<String> names = new ArrayList<String>();

		// ----Check Owner Info----
		intent = getIntent();
		if (intent.hasExtra("info")) {
			info = (HashMap<String, String>) intent
					.getSerializableExtra("info");
			goAdd.putExtra("ownerInfo", info);

			Toast.makeText(getApplicationContext(), info.get("ID"),
					Toast.LENGTH_SHORT).show();
		}
		if (intent.hasExtra("view"))
			add.setVisibility(View.GONE);

		// ----Add Animal Button----
		add.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				goAdd.putExtra("add", true);
				startActivity(goAdd);
			}
		});

		if (dbTools.getAllanimals().size() != 0) {

			if (intent.hasExtra("info")) { // Owner Info
				animalList = dbTools.getOwnerAnimals(info.get("ID"));
			} else {
				animalList = dbTools.getAllanimals(); // From Entry
			}

			for (int i = 0; i < animalList.size(); i++) {
				names.add(animalList.get(i).get("animalName"));
			}

			this.setListAdapter(new ArrayAdapter<String>(this,
					R.layout.dbtools_entry, R.id.employeeName, names));

			ListView lv = getListView();
			lv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {

					Intent i = new Intent("com.tjarita.dogdaycare.ADDANIMAL");
					i.putExtra("animalInfo", animalList.get(position)); // Sends
																		// animals
																		// table
					if (intent.hasExtra("view")) {
						HashMap<String, String> ownerInfo = dbToolsEmp
								.getEmployeeInfo((animalList.get(position)
										.get("ownerID")));
						i.putExtra("ownerInfo", ownerInfo);
					} else
						i.putExtra("ownerInfo", info);

					i.putExtra("update", true);

					Toast.makeText(getApplicationContext(),
							animalList.get(position).get("animalName"),
							Toast.LENGTH_SHORT).show();

					startActivity(i);

				}

			});
		} // if

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		dbTools.close();
		// finish();
	}

	private void initialize() {
		title = (TextView) findViewById(R.id.dbtools_title);
		add = (Button) findViewById(R.id.listEmp_Add);
		title.setText("Animal List");

	}

}
