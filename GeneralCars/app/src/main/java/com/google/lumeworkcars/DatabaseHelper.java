package com.google.lumeworkcars;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

	public static final String TABLE_NAME = "LUME_WORK_MANAGEMENT";
	private SQLiteDatabase mSQLiteDatabase;

	static final String DB_NAME = "LUME_WORK_DATA";
	static final int DB_VERSION = 2;

	public DatabaseHelper(@Nullable Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_TABLE = "create table " + TABLE_NAME + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, makers TEXT NOT NULL, model_of_vehicle TEXT NOT NULL, condition_of_vehicle TEXT NOT NULL, engine_cylinders TEXT NOT NULL, year TEXT NOT NULL, number_of_doors TEXT NOT NULL, price TEXT NOT NULL, color_of_vehicle TEXT NOT NULL, image BLOB NOT NULL, date_sold TEXT);";
		db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}


	public void addVehicles(String makers, String model_of_vehicle, String condition_of_vehicle, String engine_cylinder,
	                        String year, String number_of_doors, String price, String color_of_vehicle,
	                        byte[] image, String date_sold) {
		ContentValues mContentValues = new ContentValues();
		mSQLiteDatabase = this.getWritableDatabase();

		mContentValues.put("makers", makers);
		mContentValues.put("model_of_vehicle", model_of_vehicle);
		mContentValues.put("condition_of_vehicle", condition_of_vehicle);
		mContentValues.put("engine_cylinders", engine_cylinder);
		mContentValues.put("year", year);
		mContentValues.put("number_of_doors", number_of_doors);
		mContentValues.put("price", price);
		mContentValues.put("color_of_vehicle", color_of_vehicle);
		mContentValues.put("image", image);
		mContentValues.put("date_sold", date_sold);

		mSQLiteDatabase.insert(TABLE_NAME, null, mContentValues);
		mSQLiteDatabase.close();
	}


	public boolean updateDetails(long _id, String makers, String model_of_vehicle, String condition_of_vehicle, String engine_cylinder,
	                             String year, String number_of_doors, String price, String color_of_vehicle,
	                             String date_sold) {
		ContentValues mContentValues = new ContentValues();
		mSQLiteDatabase = this.getWritableDatabase();
		mContentValues.put("_id", _id);
		mContentValues.put("makers", makers);
		mContentValues.put("model_of_vehicle", model_of_vehicle);
		mContentValues.put("condition_of_vehicle", condition_of_vehicle);
		mContentValues.put("engine_cylinders", engine_cylinder);
		mContentValues.put("year", year);
		mContentValues.put("number_of_doors", number_of_doors);
		mContentValues.put("price", price);
		mContentValues.put("color_of_vehicle", color_of_vehicle);
		mContentValues.put("date_sold", date_sold);
		mSQLiteDatabase.update(TABLE_NAME, mContentValues, "_id = " + _id, null);
		return true;
	}


	public ArrayList<Vehicles> fetchVehicles() {
		ArrayList<Vehicles> arrayList = new ArrayList<>();
		String select_query = "SELECT *FROM " + TABLE_NAME;
		mSQLiteDatabase = this.getReadableDatabase();
		Cursor cursor = mSQLiteDatabase.rawQuery(select_query, null);

		if (cursor != null && cursor.getCount() > 0) {
			if (cursor.moveToFirst()) {
				do {
					Vehicles mVehicles = new Vehicles();
					mVehicles.setId(cursor.getLong(0));
					mVehicles.setMakers(cursor.getString(1));
					mVehicles.setModel_of_vehicle(cursor.getString(2));
					mVehicles.setColor_of_vehicle(cursor.getString(3));
					mVehicles.setEngine_cylinders_of_vehicle(cursor.getString(4));
					mVehicles.setYear_of_manufacturing(cursor.getString(5));
					mVehicles.setNumber_of_doors(cursor.getString(6));
					mVehicles.setPrice_of_vehicle(cursor.getString(7));
					mVehicles.setColor_of_vehicle(cursor.getString(8));
					mVehicles.setImage(cursor.getBlob(9));
					mVehicles.setDate_sold(cursor.getString(10));
					arrayList.add(mVehicles);
				} while (cursor.moveToNext());
			}
		}
		return arrayList;
	}


}
