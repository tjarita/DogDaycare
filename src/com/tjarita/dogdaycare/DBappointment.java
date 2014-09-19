package com.tjarita.dogdaycare;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBappointment extends SQLiteOpenHelper {
	public DBappointment(Context applicationContext) {
		super(applicationContext, "appointments.db", null, 1);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String query = "CREATE TABLE appointments("
				+ "apptID INTEGER PRIMARY KEY, animalID INTEGER, ownerID INTEGER, startDate TEXT, endDate TEXT, startTime TEXT, endTime TEXT, notes TEXT,"
				+ "FOREIGN KEY(animalID) REFERENCES animals(animalID),"
				+ "FOREIGN KEY(ownerID) REFERENCES owner(ID))";

		db.execSQL(query);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String query;
		query = "DROP TABLE IF EXISTS appointments";
		SQLiteDatabase database = this.getWritableDatabase();
		database.rawQuery(query, null);
		// db.execSQL(query);
		// onCreate(db);
	}

	public void insertAppt(HashMap<String, String> queryValues) {
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put("apptID", queryValues.get("apptID"));
		values.put("animalID", queryValues.get("animalID"));
		values.put("ownerID", queryValues.get("ownerID"));
		values.put("startDate", queryValues.get("startDate"));
		values.put("endDate", queryValues.get("endDate"));
		values.put("startTime", queryValues.get("startTime"));
		values.put("endTime", queryValues.get("endTime"));
		values.put("notes", queryValues.get("notes"));


		database.insert("appointments", null, values);
		database.close();
	}

	public int updateAppt(HashMap<String, String> queryValues) {
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put("apptID", queryValues.get("apptID"));
		values.put("animalID", queryValues.get("animalID"));
		values.put("ownerID", queryValues.get("ownerID"));
		values.put("rabies", queryValues.get("rabies"));
		values.put("startDate", queryValues.get("startDate"));
		values.put("endDate", queryValues.get("endDate"));
		values.put("startTime", queryValues.get("startTime"));
		values.put("endTime", queryValues.get("endTime"));
		values.put("notes", queryValues.get("notes"));


		return database.update("appointments", values, "apptID" + " = ?",
				new String[] { queryValues.get("apptID") });
	}

	public void deleteAnimal(String id) {
		SQLiteDatabase database = this.getWritableDatabase();

		String deleteQuery = "DELETE FROM appointments WHERE apptID='" + id
				+ "'";
		database.execSQL(deleteQuery);
	}

	public ArrayList<HashMap<String, String>> getAllappts() {
		ArrayList<HashMap<String, String>> apptArrayList = new ArrayList<HashMap<String, String>>();
		SQLiteDatabase database = this.getWritableDatabase();
		String selectQuery = "SELECT  * FROM appointments ORDER BY apptID";
		// ----Extract Data----
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("apptID", cursor.getString(0));
				map.put("animalID", cursor.getString(1));
				map.put("ownerID", cursor.getString(2));
				map.put("startDate", cursor.getString(3));
				map.put("startTime", cursor.getString(4));
				map.put("endDate", cursor.getString(5));
				map.put("endTime", cursor.getString(6));
				map.put("notes", cursor.getString(7));

				apptArrayList.add(map);

			} while (cursor.moveToNext());

		}
		// return contact list
		return apptArrayList;

	}

	public HashMap<String, String> getAnimalInfo(String id) {
		HashMap<String, String> map = new HashMap<String, String>();
		SQLiteDatabase database = this.getReadableDatabase();
		String selectQuery = "SELECT * FROM appointments WHERE apptID='" + id
				+ "'";
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				map.put("apptID", cursor.getString(0));
				map.put("animalID", cursor.getString(1));
				map.put("ownerID", cursor.getString(2));
				map.put("startDate", cursor.getString(3));
				map.put("startTime", cursor.getString(4));
				map.put("endDate", cursor.getString(5));
				map.put("endTime", cursor.getString(6));
				map.put("notes", cursor.getString(7));

			} while (cursor.moveToNext());
		}
		return map;
	}

	public int getCount() {
		SQLiteDatabase database = this.getReadableDatabase();
		String selectQuery = "SELECT count(*) FROM appointments GROUP BY apptID";
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst())
			return cursor.getInt(0);
		else
			return 0;
	}

}
