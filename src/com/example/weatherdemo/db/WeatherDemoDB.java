package com.example.weatherdemo.db;

import java.util.ArrayList;
import java.util.List;

import com.example.weatherdemo.model.City;
import com.example.weatherdemo.model.County;
import com.example.weatherdemo.model.Province;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class WeatherDemoDB {
	public static final String DB_NAME ="weather_demo";
	public static final int VERSION =1;
	private static WeatherDemoDB weatherDemoDB;
	private SQLiteDatabase db;
	
	//私有的构造器
	private WeatherDemoDB(Context context){
		WeatherDemoopenHelper dbhelp = new WeatherDemoopenHelper(context,DB_NAME,null,VERSION);
		db =dbhelp.getWritableDatabase();
		
	}
	
	public synchronized static WeatherDemoDB getInstance(Context context){
		if(weatherDemoDB==null){
			weatherDemoDB = new WeatherDemoDB(context);
		}
		return weatherDemoDB;
		
	}
	/*
	 * 存储province
	 */
	
	public void saveProvince(Province province){
		if(province !=null){
			ContentValues values = new ContentValues();
			values.put("provice_name", province.getProvinceName());
			values.put("provice_code",province.getProvinceCode());
			db.insert("Province", null, values);
		}
	}
	
	/*
	 * 从数据库中读取全国省份信息
	 */
	public List<Province>loadProvince(){
		List<Province>list = new ArrayList();
		Cursor cursor = db.query("Province", null, null, null, null, null, null);
		if(cursor.moveToFirst()){
			do{
				Province province = new Province();
				province.setId(cursor.getInt(cursor.getColumnIndex("id")));
				province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
				province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
				list.add(province);
			}while(cursor.moveToNext());
		}
		return list;
		
	}
	
	/*
	 * 存储city
	 */
	public void saveCity (City city){
		if(city!=null){
			ContentValues values = new ContentValues();
			values.put("city_name",city.getCityName() );
			values.put("city_code",city.getCityCode());
			values.put("province_id",city.getProvinceId());
			db.insert("City", null, values);
		}
	}
	
	/*
	 * 从数据库中读取某省下的所有城市
	 */
	public List<City>loadCity(int provinceId){
		List<City>list = new ArrayList();
		Cursor cursor = db.query("City", null, "province_id=?", new String[]{String.valueOf(provinceId)}, null, null, null);
		if(cursor.moveToFirst()){
			do{
				City city = new City();
				city.setId(cursor.getInt(cursor.getColumnIndex("id")));
				city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
				city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
				city.setProvinceId(cursor.getInt(provinceId));
				list.add(city);
			}while(cursor.moveToNext());
		}
		
		return list;
		
	}
	
	/*
	 * 存储county
	 */
	public void saveCounty(County county){
		if(county!=null){
			ContentValues values = new ContentValues();
			values.put("county_name", county.getCountyName());
			values.put("county_code", county.getCountyCode());
			values.put("city_id", county.getCityId());
			db.insert("County", null, values);
		}
	}
	/*
	 * 从数据库中读取某一城市下的地区数据
	 */
	public List<County>loadCounty(int cityId){
		List <County> list = new ArrayList();
		Cursor coursor = db.query("County", null, "city_id= ?", new String[]{String.valueOf(cityId)}, null, null, null);
		if(coursor.moveToFirst()){
			do{
				County county = new County();
				county.setId(coursor.getInt(coursor.getColumnIndex("id")));
				county.setCountyName(coursor.getString(coursor.getColumnIndex("county_name")));
				county.setCountyCode(coursor.getString(coursor.getColumnIndex("county_code")));
				county.setCityId(cityId);
				list.add(county);
			}while(coursor.moveToNext());
		}
		return list;
		
	}
}
