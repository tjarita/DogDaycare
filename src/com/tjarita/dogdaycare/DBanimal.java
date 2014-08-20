package com.tjarita.dogdaycare;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBanimal extends SQLiteOpenHelper{
	public DBanimal(Context applicationContext) {
		super(applicationContext, "animals.db", null, 1);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String query = 
				"CREATE TABLE animals(" +
				"animalID INTEGER PRIMARY KEY," +
				"animalName TEXT," +
				"ownerID TEXT," +
				"rabies," +
				"cdv," +
				"cpv2," +
				"cav2,"+
				"cpiv,"+
				"bb," +
				"lyme," +
				"FOREIGN KEY(animalID) REFERENCES owner(ownerID)";
		db.execSQL(query);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String query;
		query = "DROP TABLE IF EXISTS animals";
		db.execSQL(query);
		onCreate(db);
	}

	public void insertAnimal(HashMap<String, String> queryValues) {
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put("animalID", queryValues.get("animalID"));
		values.put("animalName", queryValues.get("animalName"));
		values.put("ownerID", queryValues.get("ownerID"));
		values.put("rabies", queryValues.get("rabies"));
		values.put("cdv", queryValues.get("cdv"));
		values.put("cpv2", queryValues.get("cpv2"));
		values.put("cav2", queryValues.get("cav2"));
		values.put("cpiv", queryValues.get("cpiv"));
		values.put("bb", queryValues.get("bb"));
		values.put("lyme", queryValues.get("lyme"));


		database.insert("animals", null, values);
		database.close();
	}

	public int updateAnimal(HashMap<String, String> queryValues) {
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put("animalID", queryValues.get("animalID"));
		values.put("animalName", queryValues.get("animalName"));
		values.put("ownerID", queryValues.get("ownerID"));
		values.put("rabies", queryValues.get("rabies"));
		values.put("cdv", queryValues.get("cdv"));
		values.put("cpv2", queryValues.get("cpv2"));
		values.put("cav2", queryValues.get("cav2"));
		values.put("cpiv", queryValues.get("cpiv"));
		values.put("bb", queryValues.get("bb"));
		values.put("lyme", queryValues.get("lyme"));

		return database.update("animals", values, "animalID" + " = ?",
				new String[] { queryValues.get("animalID") });
	}

	public void deleteAnimal(String id) {
		SQLiteDatabase database = this.getWritableDatabase();

		String deleteQuery = "DELETE FROM animals WHERE animalID='" + id
				+ "'";
		database.execSQL(deleteQuery);
	}

	public ArrayList<HashMap<String, String>> getAllanimals() {
		ArrayList<HashMap<String, String>> animalArrayList = new ArrayList<HashMap<String, String>>();
		SQLiteDatabase database = this.getWritableDatabase();
		String selectQuery = "SELECT  * FROM animals ORDER BY animalName";
		// ----Extract Data----
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("animalID", cursor.getString(0));
				map.put("animalName", cursor.getString(1));
				map.put("ownerID", cursor.getString(2));
				map.put("rabies", cursor.getString(3));
				map.put("cdv",cursor.getString(4));
				map.put("cpv2", cursor.getString(5));
				map.put("cav2", cursor.getString(6));
				map.put("cpiv",cursor.getString(7));
				map.put("bb", cursor.getString(8));
				map.put("lyme", cursor.getString(9));

				animalArrayList.add(map);

			} while (cursor.moveToNext());

		}
		// return contact list
		return animalArrayList;
	}

	public HashMap<String, String> getAnimalInfo(String id) {
		HashMap<String, String> map = new HashMap<String, String>();
		SQLiteDatabase database = this.getReadableDatabase();
		String selectQuery = "SELECT * FROM animals WHERE animalID='" + id
				+ "'";
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				map.put("animalID", cursor.getString(0));
				map.put("animalName", cursor.getString(1));
				map.put("ownerID", cursor.getString(2));
				map.put("rabies", cursor.getString(3));
				map.put("cdv",cursor.getString(4));
				map.put("cpv2", cursor.getString(5));
				map.put("cav2", cursor.getString(6));
				map.put("cpiv",cursor.getString(7));
				map.put("bb", cursor.getString(8));
				map.put("lyme", cursor.getString(9));
			} while (cursor.moveToNext());
		}
		return map;
	}

}
