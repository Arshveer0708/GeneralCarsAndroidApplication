package com.google.lumeworkcars;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseAdapter extends SQLiteOpenHelper {

	public static final String TABLE_NAME = "LUME_WORK";
	private SQLiteDatabase mSQLiteDatabase;

	static final String DB_NAME = "LUME_WORK_COMPANY";
	static final int DB_VERSION = 2;

	public DatabaseAdapter(@Nullable Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_TABLE = "create table " + TABLE_NAME + "(_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT NOT NULL, address TEXT NOT NULL, image BLOB NOT NULL);";
		db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}



	public boolean updateDetails(long _id,String name, String address, byte[] image) {
		ContentValues mContentValues = new ContentValues();
		mSQLiteDatabase = this.getWritableDatabase();
		mContentValues.put("_id",_id);
		mContentValues.put("name", name);
		mContentValues.put("address", address);
		mContentValues.put("image", image);
		mSQLiteDatabase.update(TABLE_NAME, mContentValues, "_id = " + _id, null);
		return true;
	}


	public ArrayList<CompanyModel> fetchDetails() {
		ArrayList<CompanyModel> arrayList = new ArrayList<>(1);
		String select_query = "SELECT *FROM " + TABLE_NAME;
		mSQLiteDatabase = this.getReadableDatabase();
		Cursor cursor = mSQLiteDatabase.rawQuery(select_query, null);

		if (cursor != null && cursor.getCount() > 0) {
			if (cursor.moveToFirst()) {
				do {
					CompanyModel mCompanyModel = new CompanyModel();
					mCompanyModel.setName(cursor.getString(0));
					mCompanyModel.setAddress(cursor.getString(1));
					mCompanyModel.setImage(cursor.getBlob(2));

					arrayList.add(mCompanyModel);
				} while (cursor.moveToNext());
			}
		}
		return arrayList;
	}

	public void addCompanyDetails(String name, String address, byte[] image) {
		ContentValues mContentValues = new ContentValues();
		mSQLiteDatabase = this.getWritableDatabase();

		mContentValues.put("name", name);
		mContentValues.put("address", address);
		mContentValues.put("image", image);

		mSQLiteDatabase.insert(TABLE_NAME, null, mContentValues);
		mSQLiteDatabase.close();
	}


}
