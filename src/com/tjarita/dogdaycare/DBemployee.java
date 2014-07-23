package com.tjarita.dogdaycare;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBemployee extends SQLiteOpenHelper {

	public DBemployee(Context applicationContext) {
		super(applicationContext, "employee.db", null, 1);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String query = "CREATE TABLE employees ( ID INTEGER PRIMARY KEY, admin TEXT, employee TEXT, customer TEXT,  firstName TEXT, lastName TEXT, phoneNumber TEXT, email TEXT, home TEXT)";
		db.execSQL(query);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String query;
		query = "DROP TABLE IF EXISTS employees";
		db.execSQL(query);
		onCreate(db);
	}

	public void insertEmployee(HashMap<String, String> queryValues) {
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put("ID", queryValues.get("ID"));
		values.put("admin", queryValues.get("admin"));
		values.put("employee", queryValues.get("employee"));
		values.put("customer", queryValues.get("customer"));
		values.put("firstName", queryValues.get("firstName"));
		values.put("lastName", queryValues.get("lastName"));
		values.put("phoneNumber", queryValues.get("phoneNumber"));
		values.put("email", queryValues.get("email"));
		values.put("home", queryValues.get("home"));

		database.insert("employees", null, values);
		database.close();
	}

	public int updateEmployee(HashMap<String, String> queryValues) {
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put("ID", queryValues.get("ID"));
		values.put("admin", queryValues.get("admin"));
		values.put("employee", queryValues.get("employee"));
		values.put("customer", queryValues.get("customer"));
		values.put("firstName", queryValues.get("firstName"));
		values.put("lastName", queryValues.get("lastName"));
		values.put("phoneNumber", queryValues.get("phoneNumber"));
		values.put("email", queryValues.get("email"));
		values.put("home", queryValues.get("home"));
		
		return database.update("employees", values, "ID" + " = ?",
				new String[] { queryValues.get("ID") });
	}

	public void deleteEmployee(String id) {
		SQLiteDatabase database = this.getWritableDatabase();

		String deleteQuery = "DELETE FROM employees WHERE ID='" + id
				+ "'";
		database.execSQL(deleteQuery);
	}

	public ArrayList<HashMap<String, String>> getAllEmployees() {
		ArrayList<HashMap<String, String>> employeeArrayList = new ArrayList<HashMap<String, String>>();
		SQLiteDatabase database = this.getWritableDatabase();
		String selectQuery = "SELECT  * FROM employees WHERE employee='1' ORDER BY lastName ";
		// ----Extract Data----
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("ID", cursor.getString(0));
				map.put("admin", cursor.getString(1));
				map.put("employee", cursor.getString(2));
				map.put("customer", cursor.getString(3));
				map.put("firstName", cursor.getString(4));
				map.put("lastName", cursor.getString(5));
				map.put("phoneNumber", cursor.getString(6));
				map.put("email", cursor.getString(7));
				map.put("home", cursor.getString(8));

				employeeArrayList.add(map);

			} while (cursor.moveToNext());

		}
		// return contact list
		return employeeArrayList;
	}
	
	public ArrayList<HashMap<String, String>> getAllCustomers() {
		ArrayList<HashMap<String, String>> employeeArrayList = new ArrayList<HashMap<String, String>>();
		SQLiteDatabase database = this.getWritableDatabase();
		String selectQuery = "SELECT  * FROM employees WHERE customer='1' ORDER BY lastName ";
		// ----Extract Data----
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("ID", cursor.getString(0));
				map.put("admin", cursor.getString(1));
				map.put("employee", cursor.getString(2));
				map.put("customer", cursor.getString(3));
				map.put("firstName", cursor.getString(4));
				map.put("lastName", cursor.getString(5));
				map.put("phoneNumber", cursor.getString(6));
				map.put("email", cursor.getString(7));
				map.put("home", cursor.getString(8));

				employeeArrayList.add(map);

			} while (cursor.moveToNext());

		}
		// return contact list
		return employeeArrayList;
	}

	public HashMap<String, String> getEmployeeInfo(String id) {
		HashMap<String, String> employee = new HashMap<String, String>();
		SQLiteDatabase database = this.getReadableDatabase();
		String selectQuery = "SELECT * FROM employees WHERE ID='" + id
				+ "'";
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				employee.put("ID", cursor.getString(0));
				employee.put("admin", cursor.getString(1));
				employee.put("employee", cursor.getString(2));
				employee.put("customer", cursor.getString(3));
				employee.put("firstName", cursor.getString(4));
				employee.put("lastName", cursor.getString(5));
				employee.put("phoneNumber", cursor.getString(6));
				employee.put("email", cursor.getString(7));
				employee.put("home", cursor.getString(8));

			} while (cursor.moveToNext());
		}
		return employee;
	}

	public boolean checkPIN(int id) {
		SQLiteDatabase database = this.getReadableDatabase();
		String checkQuery = "SELECT * FROM employees WHERE ID='" + id
				+ "' and employee='1'";
		Cursor cursor = database.rawQuery(checkQuery, null);
		if (cursor.getCount()>= 1) // ID already exists
			return true;
		return false;

	}

}


