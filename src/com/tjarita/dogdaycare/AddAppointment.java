package com.tjarita.dogdaycare;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.HashMap;

import com.tjarita.dogdaycare.AddAnimal.pickDate;

import customViews.CalendarViewScrollable;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class AddAppointment extends Activity {
	DBappointment dbtools = new DBappointment(this);
	Intent intent;

	Calendar c;
	CalendarViewScrollable start, end;
	EditText pet, owner, startTime, endTime, notes;
	Button save;

	HashMap<String, String> animalInfo, ownerInfo;
	int apptID, cYear, cMonth, cDay, tHour, tMinute, pick;
	static boolean TWELVE_HOURS = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dbtools_add_appointment);
		findViewById(R.id.dbtools_appt_startBox).requestFocus();
		initialize();
		initializeButtons();
		getInfo();
		setNames();
	}

	@Override
	protected void onResume() {
		super.onResume();

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	private void initialize() {

		apptID = dbtools.getCount();

		c = Calendar.getInstance();
		cYear = c.get(Calendar.YEAR);
		cMonth = c.get(Calendar.MONTH);
		cDay = c.get(Calendar.DAY_OF_MONTH);
		pick = 0;

		start = (CalendarViewScrollable) findViewById(R.id.dbtools_appt_start_date);
		end = (CalendarViewScrollable) findViewById(R.id.dbtools_appt_end_date);

		pet = (EditText) findViewById(R.id.dbtools_appt_pet);
		owner = (EditText) findViewById(R.id.dbtools_appt_owner);
		startTime = (EditText) findViewById(R.id.dbtools_appt_start_time);
		endTime = (EditText) findViewById(R.id.dbtools_appt_end_time);
		notes = (EditText) findViewById(R.id.dbtools_appt_notes);

		save = (Button) findViewById(R.id.dbtools_appt_save);

	}

	private void initializeButtons() {
		startTime.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				DialogFragment dialogFragment = new pickTime();
				dialogFragment.show(getFragmentManager(), "start_date_picker");
				pick = 1;
			}

		});

		endTime.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				DialogFragment dialogFragment = new pickTime();
				dialogFragment.show(getFragmentManager(), "start_date_picker");
				pick = 2;
			}

		});

		save.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				HashMap<String, String> newAppt = new HashMap<String, String>();
				newAppt.put("apptID", Integer.toString(apptID));
				newAppt.put("animalID", animalInfo.get("animalID"));
				newAppt.put("ownerID", ownerInfo.get("ID"));
				newAppt.put("startDate", Long.toString(start.getDate()));
				newAppt.put("startTime", startTime.getText().toString());
				newAppt.put("endDate", Long.toString(end.getDate()));
				newAppt.put("endTime", endTime.getText().toString());
				newAppt.put("notes", notes.getText().toString());

				dbtools.insertAppt(newAppt);

				Toast.makeText(getApplicationContext(), "Added Appointment",
						Toast.LENGTH_LONG).show();
				finish();
			}
		});

	}

	private void getInfo() {
		intent = getIntent();
		animalInfo = (HashMap<String, String>) intent
				.getSerializableExtra("animalInfo");
		ownerInfo = (HashMap<String, String>) intent
				.getSerializableExtra("ownerInfo");
	}

	private void setNames() {
		pet.setText(animalInfo.get("animalName"));
		owner.setText(ownerInfo.get("firstName") + " "
				+ ownerInfo.get("lastName"));
	}

	// ----Pick Date Class for Dialogue----

	class pickTime extends DialogFragment implements
			TimePickerDialog.OnTimeSetListener {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			TimePickerDialog show = new TimePickerDialog(AddAppointment.this,
					this, tHour, tMinute, TWELVE_HOURS);
			return show;
		}

		@Override
		public void onTimeSet(TimePicker view, int hour, int minute) {
			tHour = hour;
			tMinute = minute;

			// Saves date in SimpleDateFormat "yyyy-MM-dd"
			switch (pick) {
			case 1:
				startTime.setText( convertTime(tHour, tMinute)      );
				break;
			case 2:
				endTime.setText( convertTime(tHour, tMinute)		);
				break;
			}
		}

		private String convertTime(int hour, int minute){
			String cAMorPM, cHour, cMinute;
			
			// Check AM or PM
			if(hour >= 12)
				cAMorPM = "PM";
			else
				cAMorPM = "AM";
			
			// Check Midnight
			if(hour == 0)
				hour = 12;
			
			// Add 0 if under 10
			if(hour < 10)
				cHour = "0" + Integer.toString(hour);
			else
				cHour = Integer.toString(hour);
			
			if(minute < 10)
				cMinute = "0" + Integer.toString(minute);
			else
				cMinute = Integer.toString(minute);
			
			return cHour + " : " + cMinute + " " + cAMorPM;
		}
		
		
	}

}
