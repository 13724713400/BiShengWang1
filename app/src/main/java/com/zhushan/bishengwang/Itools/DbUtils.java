/*

package com.zhushan.bishengwang.Itools;

import java.io.File;
import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.zhushan.bishengwang.Constance.ConstanceTag;
import com.zhushan.bishengwang.Iaplication.TBaplication;

public class DbUtils {
	private static DbUtils dbUtils;
	private DbUtils(){}
	public static DbUtils getInstance()
	{
		if (dbUtils==null)
		{
			synchronized (DbUtils.class)
			{
				if (dbUtils==null)
				{
					dbUtils = new DbUtils();

				}

			}

		}

		return dbUtils;

	}
	public  void LoginInsert(String imgurl)
	{
		SQLiteDatabase database = TBaplication.getWritableDatabase;
		database.beginTransaction();
		ContentValues  contentValues = new ContentValues();
		contentValues.put("imgurl", imgurl);
		database.insert(ConstanceTag.SQLTB, null, contentValues);
		database.setTransactionSuccessful();
		database.endTransaction();

	}

	
	public void LoginDelete()
	{	
		SQLiteDatabase database = TBaplication.getWritableDatabase;
		database.beginTransaction();

		database.delete(ConstanceTag.SQLTB, null, null);
		database.setTransactionSuccessful();
		database.endTransaction();
	}

	*/
/*public void LoginUpdate(String phone,String state)
	{
		SQLiteDatabase database = TBaplication.dbHelpUtils.getWritableDatabase();
		database.beginTransaction();	
		ContentValues contentValues = new ContentValues();
		contentValues.put(DbConstance.STATE, state);
		database.update(DbConstance.SQLITEDATABASETABLENAME, contentValues, DbConstance.PHONE+"=?", new String[]{phone});
		database.setTransactionSuccessful();
		database.endTransaction();
		
	}*//*


	
	
	public ArrayList<String> query()
	{
		ArrayList<String> imgList = new ArrayList<String>();
		SQLiteDatabase database = TBaplication.getReadableDatabase;
		Cursor cursor = database.query(ConstanceTag.SQLTB, null, null, null, null, null, null);
		if(cursor!=null)
		{
			while (cursor.moveToNext()) {
				int id = cursor.getInt(0);
				String imgeUrl = cursor.getString(1);
				Log.i("www","本地图片地址"+imgeUrl);
				imgList.add(imgeUrl);
			}
			return imgList;
		}		
		database.close();
		return null;
	}

    public void deleteItem(String imgUrl)
	{
		SQLiteDatabase database = TBaplication.getReadableDatabase;
		database.beginTransaction();
		String[] dellet = new String[]{imgUrl};
		int deleteid = database.delete(ConstanceTag.SQLTB,"imgurl=?",dellet);
		database.setTransactionSuccessful();
		database.endTransaction();
		Log.i("www","deleteid"+deleteid);
	}
	public String queryLast()
	{

		SQLiteDatabase database = TBaplication.getReadableDatabase;

		Cursor cursor = database.query(ConstanceTag.SQLTB, null, null, null, null, null, null);
		if(cursor!=null)
		{
			if(cursor.moveToFirst()) {
				int id = cursor.getInt(0);
				String imgeUrl = cursor.getString(1);
				Log.i("www", "本地图片地址" + imgeUrl+"id"+id);
				return imgeUrl;
			}

		}
		database.close();
		return null;
	}
	
	*/
/*public String queryPhone()
	{
		SQLiteDatabase database = Tbaplication.login.getReadableDatabase();
		Cursor cursor = database.query(DbConstance.SQLITEDATABASETABLENAME, null, null, null, null, null, null);
		if(cursor!=null)
		{
			while (cursor.moveToNext()) {

				int id = cursor.getInt(0);

				String phone = cursor.getString(1);
				
				String state = cursor.getString(2);
                
				if(state!=null&&state.equals("1"))
				{
					return phone;
				}					
			}
		}		
		database.close();
		return null;
	}*//*


}

*/
