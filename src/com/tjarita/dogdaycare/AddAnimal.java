package com.tjarita.dogdaycare;

<<<<<<< HEAD
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.HashMap;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddAnimal extends Activity {

	DBanimal dbTools = new DBanimal(this);
	Calendar c = Calendar.getInstance();

	int cYear = c.get(Calendar.YEAR);
	int cMonth = c.get(Calendar.MONTH);
	int cDay = c.get(Calendar.DAY_OF_MONTH);
	int pick = 0;
	int aID;
	String aName, oName, oID;
	HashMap<String, String> ownerInfo;

	EditText rabies, distemper, adenovirus, parainfluenza, parvovirus,
			bordetella;
	TextView animalID, ownerID, animalName, ownerName;
	Button add;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dbtools_add_animal);
		initialize();

	}

	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}

	private void initialize() {
		rabies = (EditText) findViewById(R.id.dbtools_animal_rabies);
		distemper = (EditText) findViewById(R.id.dbtools_animal_distemper);
		adenovirus = (EditText) findViewById(R.id.dbtools_animal_adenovirus);
		parainfluenza = (EditText) findViewById(R.id.dbtools_animal_parainfluenza);
		parvovirus = (EditText) findViewById(R.id.dbtools_animal_parvovirus);
		bordetella = (EditText) findViewById(R.id.dbtools_animal_bordetella);
		animalID = (TextView) findViewById(R.id.dbtools_animalID);
		ownerID = (TextView) findViewById(R.id.dbtools_animalOwnerID);
		animalName = (TextView) findViewById(R.id.dbtools_animal_name);
		ownerName = (TextView) findViewById(R.id.dbtools_animal_owner);
		add = (Button) findViewById(R.id.dbtools_animal_add);

		Intent intent = getIntent();

		ownerInfo = (HashMap<String, String>) intent
				.getSerializableExtra("ownerInfo");

		aID = dbTools.getCount() + 1;
		animalID.setText(Integer.toString(aID));

		oID = ownerInfo.get("ID");
		ownerID.setText(oID);

		Toast.makeText(getApplicationContext(), oID, Toast.LENGTH_SHORT).show();
	}

	private String convertMonth(int month) {
		return new DateFormatSymbols().getMonths()[month];
=======
import android.app.Activity;
import android.os.Bundle;

public class AddAnimal extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		
>>>>>>> origin/master
	}

	@Override
	protected void onResume() {
<<<<<<< HEAD
		super.onResume();

		// ----Date popups----
		rabies.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				DialogFragment dialogFragment = new pickDate();
				dialogFragment.show(getFragmentManager(), "start_date_picker");
				pick = 1;
			}

		});

		distemper.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				DialogFragment dialogFragment = new pickDate();
				dialogFragment.show(getFragmentManager(), "start_date_picker");
				pick = 2;
			}

		});
		adenovirus.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				DialogFragment dialogFragment = new pickDate();
				dialogFragment.show(getFragmentManager(), "start_date_picker");
				pick = 3;
			}

		});
		parainfluenza.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				DialogFragment dialogFragment = new pickDate();
				dialogFragment.show(getFragmentManager(), "start_date_picker");
				pick = 4;
			}

		});
		parvovirus.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				DialogFragment dialogFragment = new pickDate();
				dialogFragment.show(getFragmentManager(), "start_date_picker");
				pick = 5;
			}

		});
		bordetella.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				DialogFragment dialogFragment = new pickDate();
				dialogFragment.show(getFragmentManager(), "start_date_picker");
				pick = 6;
			}

		});

		add.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				HashMap<String, String> dates = new HashMap<String, String>();
			}
		});

	}

	// ----Pick Date Class for Dialogue----

	class pickDate extends DialogFragment implements
			DatePickerDialog.OnDateSetListener {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			DatePickerDialog show = new DatePickerDialog(AddAnimal.this, this,
					cYear, cMonth, cDay);
			return show;

		}

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			cYear = year;
			cMonth = monthOfYear;
			cDay = dayOfMonth;

			switch (pick) {
			case 1:
				rabies.setText(new StringBuilder().append(convertMonth(cMonth))
						.append(" , ").append(cYear));
				break;
			case 2:
				distemper.setText(new StringBuilder()
						.append(convertMonth(cMonth)).append(" , ")
						.append(cYear));
				break;

			case 3:
				adenovirus.setText(new StringBuilder()
						.append(convertMonth(cMonth)).append(" , ")
						.append(cYear));
				break;

			case 4:
				parainfluenza.setText(new StringBuilder()
						.append(convertMonth(cMonth)).append(" , ")
						.append(cYear));
				break;

			case 5:
				parvovirus.setText(new StringBuilder()
						.append(convertMonth(cMonth)).append(" , ")
						.append(cYear));
				break;

			case 6:
				bordetella.setText(new StringBuilder()
						.append(convertMonth(cMonth)).append(" , ")
						.append(cYear));
				break;

			}
		}
	}

=======
		// TODO Auto-generated method stub
		super.onResume();
	}

	
	
>>>>>>> origin/master
}
