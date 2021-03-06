package com.example.weatherdemo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class WeatherDemoopenHelper extends SQLiteOpenHelper {
   	/*
   	 * province建表
   	 */
	public static final String CREATE_PROVINCE ="create table Province("
			+"id integer primary key autoincrement,"
			+"province_name text,"
			+"province_code text)";
	/*
	 * city建表
	 */
	public static final String CREATE_CITY = "create table City("
			+"id integer primary key autoincrment"
			+"city_name text"
			+"city_code text"
			+"province_id integer)";
	/*
	 * county建表
	 */
	public static final String CREATE_county = "create table County("
			+"id integer primary key autoincrment"
			+"county_name text"
			+"county_code text"
			+"city_id integer)";
	
	public WeatherDemoopenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_PROVINCE);
		db.execSQL(CREATE_CITY);
		db.execSQL(CREATE_county);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
