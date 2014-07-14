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
//		Button customer = (Button) findViewById(R.id.entry_registerOwner);
		settings.setVisibility(View.GONE);
		
		Intent intent = getIntent();
		
		//----Manager Settings----
		if(intent.getStringExtra("admin").contains("1"))
			settings.setVisibility(View.VISIBLE);
		settings.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent s = new Intent("com.tjarita.dogdaycare.LISTEMPLOYEES");
				startActivity(s);
				
			}
		});
		
		//----Add Customer----
		Button customer = (Button) findViewById(R.id.sales_newCustomer);
		customer.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent o = new Intent("com.tjarita.dogdaycare.ADDEMPLOYEES");
				o.putExtra("customer", true);
				startActivity(o);
			}
		});
		
		
		//----Add Animal----
		Button animal = (Button) findViewById(R.id.sales_newAnimal);
		animal.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent o = new Intent("com.tjarita.dogdaycare.ADDEMPLOYEES");
				o.putExtra("animal", true);
				startActivity(o);
			}
		});
		
		//----Add Appointment----
		Button appointment = (Button) findViewById(R.id.sales_newAppointment);
		appointment.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent o = new Intent("com.tjarita.dogdaycare.ADDAPPOINTMENT");
//				o.putExtra("customer", true);
				startActivity(o);
			}
		});

	}
	
	
	
	
	
	
	
	
	
	
	
}
