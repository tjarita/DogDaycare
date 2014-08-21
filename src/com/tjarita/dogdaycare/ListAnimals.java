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
	Intent goAdd = new Intent("com.tjarita.dogdaycare.ADDANIMAL");

	TextView title;
	Button add;

	ArrayList<HashMap<String, String>> animalList;
	HashMap<String, String> info;


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

		Intent intent = getIntent();

		if (intent.hasExtra("info"))
			info = (HashMap<String, String>) intent
					.getSerializableExtra("info");

		// Toast.makeText(getApplicationContext(), info.get("ownerID"),
		// Toast.LENGTH_SHORT).show();

		// ----Add Animal Button----
		add.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				goAdd.putExtra("ownerInfo", info);
				startActivity(goAdd);
			}
		});

		if (dbTools.getAllanimals().size() != 0) {

			if (intent.hasExtra("info")) { // Customer's animals
				info = (HashMap<String, String>) intent // Assign only when info
														// exists
						.getSerializableExtra("info");
				animalList = dbTools.getOwnerAnimals(info.get("ownerID"));
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
					i.putExtra("info", animalList.get(position));
					i.putExtra("update", true);
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
		finish();
	}

	private void initialize() {
		title = (TextView) findViewById(R.id.dbtools_title);
		add = (Button) findViewById(R.id.listEmp_Add);
		title.setText("Animal List");

	}

}
