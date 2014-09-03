package com.tjarita.dogdaycare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Entry extends Activity {

	DBanimal dbTools = new DBanimal(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// ----Hide Management Settings----
		Button settings = (Button) findViewById(R.id.entry_admin);
		settings.setVisibility(View.GONE);
		Intent intent = getIntent();

		// ----Show Management Settings----
		if (intent.hasExtra("admin")
				&& intent.getStringExtra("admin").contains("1")) {
			settings.setVisibility(View.VISIBLE);

			settings.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent s = new Intent(
							"com.tjarita.dogdaycare.LISTEMPLOYEES");
					startActivity(s);

				}
			});
		}
		// ----Add Customer----
		Button customer = (Button) findViewById(R.id.entry_newCustomer);
		customer.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent ac = new Intent("com.tjarita.dogdaycare.ADDEMPLOYEES");
				ac.putExtra("customer", true);
				ac.putExtra("add", true);
				startActivity(ac);
			}
		});

		// ----View Customer----
		Button vcustomer = (Button) findViewById(R.id.entry_viewCustomers);
		vcustomer.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent vc = new Intent("com.tjarita.dogdaycare.LISTEMPLOYEES");
				vc.putExtra("customer", true);
				startActivity(vc);
			}
		});

		// ----View Animal----
		Button animal = (Button) findViewById(R.id.entry_viewAnimals);
		animal.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent va = new Intent("com.tjarita.dogdaycare.LISTANIMALS");
				va.putExtra("view", true);
				startActivity(va);
			}
		});

		// ----Add Appointment----
		Button appointment = (Button) findViewById(R.id.entry_newAppointment);
		appointment.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent aap = new Intent("com.tjarita.dogdaycare.ADDAPPOINTMENT");
				aap.putExtra("customer", true);
				startActivity(aap);
			}
		});

		// ----Drop Table----
		Button drop = (Button) findViewById(R.id.entry_drop);
		drop.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dbTools.onUpgrade(null, 0, 0);
				dbTools.close();
			}
		});

	}

}
