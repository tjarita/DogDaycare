package com.tjarita.dogdaycare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Entry extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button settings = (Button) findViewById(R.id.entry_admin);
		// Button customer = (Button) findViewById(R.id.entry_registerOwner);
		settings.setVisibility(View.GONE);

		Intent intent = getIntent();

		// ----Manager Settings----
		if (intent.getStringExtra("admin").contains("1"))
			settings.setVisibility(View.VISIBLE);
		settings.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent s = new Intent("com.tjarita.dogdaycare.LISTEMPLOYEES");
				startActivity(s);

			}
		});

		// ----Add Customer----
		Button customer = (Button) findViewById(R.id.entry_newCustomer);
		customer.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent ac = new Intent("com.tjarita.dogdaycare.ADDEMPLOYEES");
				ac.putExtra("customer", true);
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

		// ----Add Animal----
		Button animal = (Button) findViewById(R.id.entry_newAnimal);
		animal.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent aa = new Intent("com.tjarita.dogdaycare.ADDEMPLOYEES");
				aa.putExtra("animal", true);
				startActivity(aa);
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

	}

}
