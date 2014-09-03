package com.tjarita.dogdaycare;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddAnimal extends Activity {

	DBanimal dbTools = new DBanimal(this);
	Calendar c = Calendar.getInstance();
	InputMethodManager im;

	static int THREE_YEARS = 36;
	static int SIX_MONTHS = 6;

	int cYear = c.get(Calendar.YEAR);
	int cMonth = c.get(Calendar.MONTH);
	int cDay = c.get(Calendar.DAY_OF_MONTH);
	int pick = 0;
	int aID;
	String aName, oName, oID;
	HashMap<String, String> ownerInfo, animalInfo;

	Intent intent;

	TextView rabies, distemper, adenovirus, parainfluenza, parvovirus,
			bordetella;
	TextView animalID, ownerID, animalName, ownerName, currentTitle;
	CheckBox checkRabies, checkDistemper, checkAdenovirus, checkParainfluenza,
			checkParvovirus, checkBordetella;
	Button add;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dbtools_add_animal);
		initialize();
		initializeButtons();
		if (intent.hasExtra("add")) {
			hideChecks();
			// Set new animal ID
			aID = dbTools.getCount() + 1;
			animalID.setText(Integer.toString(aID));
		} else {
			// TODO check vaccination dates vs today
			animalInfo = (HashMap<String, String>) intent
					.getSerializableExtra("animalInfo");
			updateAnimalText();
			showChecks();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		dbTools.close();
		finish();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	private void initialize() {
		rabies = (TextView) findViewById(R.id.dbtools_animal_rabies);
		distemper = (TextView) findViewById(R.id.dbtools_animal_distemper);
		adenovirus = (TextView) findViewById(R.id.dbtools_animal_adenovirus);
		parainfluenza = (TextView) findViewById(R.id.dbtools_animal_parainfluenza);
		parvovirus = (TextView) findViewById(R.id.dbtools_animal_parvovirus);
		bordetella = (TextView) findViewById(R.id.dbtools_animal_bordetella);

		animalID = (TextView) findViewById(R.id.dbtools_animalID);
		ownerID = (TextView) findViewById(R.id.dbtools_animalOwnerID);
		animalName = (TextView) findViewById(R.id.dbtools_animal_name);
		ownerName = (TextView) findViewById(R.id.dbtools_animal_owner);
		currentTitle = (TextView) findViewById(R.id.dbtools_animalCurrent);

		checkRabies = (CheckBox) findViewById(R.id.dbtools_animal_rabiesOK);
		checkDistemper = (CheckBox) findViewById(R.id.dbtools_animal_distemperOK);
		checkAdenovirus = (CheckBox) findViewById(R.id.dbtools_animal_adenovirusOK);
		checkParainfluenza = (CheckBox) findViewById(R.id.dbtools_animal_parainfluenzaOK);
		checkParvovirus = (CheckBox) findViewById(R.id.dbtools_animal_parvovirusOK);
		checkBordetella = (CheckBox) findViewById(R.id.dbtools_animal_bordetellaOK);

		add = (Button) findViewById(R.id.dbtools_animal_add);

		// Get selected owner + animal info
		intent = getIntent();
		ownerInfo = (HashMap<String, String>) intent
				.getSerializableExtra("ownerInfo");

		// Set owner ID from intent
		oID = ownerInfo.get("ID");
		ownerID.setText(oID);

		ownerName.setText(ownerInfo.get("lastName") + " , "
				+ ownerInfo.get("firstName"));

		im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

		// Toast.makeText(getApplicationContext(), oID,
		// Toast.LENGTH_SHORT).show();
	}

	private void initializeButtons() {

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

		// Fill map and save
		add.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				HashMap<String, String> newAnimal = new HashMap<String, String>();
				newAnimal.put("animalID", animalID.getText().toString());
				newAnimal.put("animalName", animalName.getText().toString());
				newAnimal.put("ownerID", ownerID.getText().toString());
				newAnimal.put("rabies", rabies.getText().toString());
				newAnimal.put("cdv", distemper.getText().toString());
				newAnimal.put("cav2", adenovirus.getText().toString());
				newAnimal.put("cpiv", parainfluenza.getText().toString());
				newAnimal.put("cpv2", parvovirus.getText().toString());
				newAnimal.put("bb", bordetella.getText().toString());

				dbTools.insertAnimal(newAnimal);
				dbTools.close();
				finish();
			}
		});

		animalName.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				im.showSoftInput(animalName, InputMethodManager.SHOW_IMPLICIT);
			}
		});

	}

	private void hideChecks() {
		currentTitle.setText("");
		checkRabies.setVisibility(View.GONE);
		checkDistemper.setVisibility(View.GONE);
		checkAdenovirus.setVisibility(View.GONE);
		checkParainfluenza.setVisibility(View.GONE);
		checkParvovirus.setVisibility(View.GONE);
		checkBordetella.setVisibility(View.GONE);
	}

	private void updateAnimalText() {
		animalID.setText(animalInfo.get("animalID"));
		animalName.setText(animalInfo.get("animalName"));

		rabies.setText(animalInfo.get("rabies"));
		distemper.setText(animalInfo.get("cdv"));
		adenovirus.setText(animalInfo.get("cav2"));
		parainfluenza.setText(animalInfo.get("cpiv"));
		parvovirus.setText(animalInfo.get("cpv2"));
		bordetella.setText(animalInfo.get("bb"));

		/*
		 * 3 year Vaccines Rabies CDV = (D)istemper , CAV2 = (A)denovirus , CPiV
		 * = (P)arainfluenza / Kennel Cough , CPV2 = (P)arvovirus , 6-12 month
		 * Vaccines , Bb = Bordetella,
		 */

	}

	// Show text date vs number. (August vs 8)
	private String convertMonth(int month) {
		return new DateFormatSymbols().getMonths()[month];
	}

	private void showChecks() {
		SimpleDateFormat sdf = new SimpleDateFormat("MMMM d , yyyy",
				Locale.ENGLISH);

		Date rabiesDate, distemperDate, adenovirusDate, parainfluenzaDate, parvovirusDate, bordetellaDate;

		try {

			rabiesDate = sdf.parse(animalInfo.get("rabies"));
			distemperDate = sdf.parse(animalInfo.get("cdv"));
			adenovirusDate = sdf.parse(animalInfo.get("cav2"));
			parainfluenzaDate = sdf.parse(animalInfo.get("cpiv"));
			parvovirusDate = sdf.parse(animalInfo.get("cpv2"));
			bordetellaDate = sdf.parse(animalInfo.get("bb"));

			if (checkDate(rabiesDate) < THREE_YEARS)
				checkRabies.setChecked(true);
			if (checkDate(distemperDate) < THREE_YEARS)
				checkDistemper.setChecked(true);
			if (checkDate(adenovirusDate) < THREE_YEARS)
				checkAdenovirus.setChecked(true);
			if (checkDate(parainfluenzaDate) < THREE_YEARS)
				checkParainfluenza.setChecked(true);
			if (checkDate(parvovirusDate) < THREE_YEARS)
				checkParvovirus.setChecked(true);
			if (checkDate(bordetellaDate) < SIX_MONTHS)
				checkBordetella.setChecked(true);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	private int checkDate(Date date) {
		Calendar temp = Calendar.getInstance();
		temp.setTime(date);

		int diffYear = c.get(Calendar.YEAR) - temp.get(Calendar.YEAR);
		int diffMonth = diffYear * 12 + c.get(Calendar.MONTH)
				- temp.get(Calendar.MONTH);

		return diffMonth;
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

			// Saves date in SimpleDateFormat "yyyy-MM-dd"
			switch (pick) {
			case 1:
				rabies.setText(new StringBuilder().append(convertMonth(cMonth))
						.append(" ").append(cDay).append(" , ").append(cYear));
				break;
			case 2:
				distemper.setText(new StringBuilder()
						.append(convertMonth(cMonth)).append(" ").append(cDay)
						.append(" , ").append(cYear));
				break;

			case 3:
				adenovirus.setText(new StringBuilder()
						.append(convertMonth(cMonth)).append(" ").append(cDay)
						.append(" , ").append(cYear));
				break;

			case 4:
				parainfluenza.setText(new StringBuilder()
						.append(convertMonth(cMonth)).append(" ").append(cDay)
						.append(" , ").append(cYear));
				break;

			case 5:
				parvovirus.setText(new StringBuilder()
						.append(convertMonth(cMonth)).append(" ").append(cDay)
						.append(" , ").append(cYear));
				break;

			case 6:
				bordetella.setText(new StringBuilder()
						.append(convertMonth(cMonth)).append(" ").append(cDay)
						.append(" , ").append(cYear));
				break;

			}
		}
	}

}
