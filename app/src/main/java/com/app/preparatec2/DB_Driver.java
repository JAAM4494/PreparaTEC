package com.app.preparatec2;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DB_Driver extends SQLiteOpenHelper{

	private static String DB_PATH = "";

	private static String DB_NAME = "preparaTEC_DB.db";
	private static final String TABLE_PREG = "ItemInfo";
	private static final String TABLEItem = "Items";


	private SQLiteDatabase myDataBase;

	private final Context myContext;


	public DB_Driver(Context context) {

		super(context, DB_NAME, null, 1);
		DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";

		/*if(android.os.Build.VERSION.SDK_INT >= 4.2){
			DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
		} else {
			DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
		}*/


		this.myContext = context;
	}


	public void createDataBase() throws IOException{

		boolean dbExist = checkDataBase();

		if(dbExist){
		}else{


			this.getReadableDatabase();

			try {

				copyDataBase();

			} catch (IOException e) {

				throw new Error("Error copying database");

			}
		}

	}


	private boolean checkDataBase(){

		SQLiteDatabase checkDB = null;

		try{
			String myPath = DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

		}catch(SQLiteException e){


		}

		if(checkDB != null){

			checkDB.close();

		}

		return checkDB != null ? true : false;
	}


	private void copyDataBase() throws IOException{

		InputStream myInput = myContext.getAssets().open(DB_NAME);

		String outFileName = DB_PATH + DB_NAME;

		OutputStream myOutput = new FileOutputStream(outFileName);

		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer))>0){
			myOutput.write(buffer, 0, length);
		}

		//Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();

	}

	public void openDataBase() throws SQLException{

		String myPath = DB_PATH + DB_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

	}

	@Override
	public synchronized void close() {

		if(myDataBase != null)
			myDataBase.close();

		super.close();

	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	public List<Question_Template> getItemInfo() {

		List<Question_Template> quesList = new ArrayList<Question_Template>();
		String selectQuery = "SELECT  * FROM " + TABLE_PREG;
		myDataBase=this.getReadableDatabase();
		Cursor cursor = myDataBase.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				//Log.d("Cursor pregunta",cursor.getString(1));
				//Log.d("Cursor answer",cursor.getString(2));
				//Log.d("Cursor opcA",cursor.getString(3));
				//Log.d("Cursor opcB",cursor.getString(4));
				//Log.d("Cursor opcC",cursor.getString(5));
				//Log.d("Cursor opcD",cursor.getString(6));
				//Log.d("Cursor opcE",cursor.getString(7));
				Question_Template quest = new Question_Template();
				//quest.setID(cursor.getInt(0));
				//quest.setQUESTION(cursor.getString(1));
				quest.setOpcA(cursor.getString(0));
				quest.setOpcB(cursor.getString(1));
				quest.setOpcC(cursor.getString(2));
				quest.setOpcD(cursor.getString(3));
				quest.setOpcE(cursor.getString(4));
				quest.setANSWER(cursor.getString(5));
				quesList.add(quest);
			} while (cursor.moveToNext());
		}
		return quesList;
	}

	public List<Question_Template> getItems() {

		List<Question_Template> quesList = new ArrayList<Question_Template>();
		String selectQuery = "SELECT  * FROM " + TABLEItem;
		myDataBase=this.getReadableDatabase();
		Cursor cursor = myDataBase.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				//Log.d("Cursor pregunta",cursor.getString(1));
				//Log.d("Cursor answer",cursor.getString(2));
				//Log.d("Cursor opcA",cursor.getString(3));
				//Log.d("Cursor opcB",cursor.getString(4));
				//Log.d("Cursor opcC",cursor.getString(5));
				//Log.d("Cursor opcD",cursor.getString(6));
				//Log.d("Cursor opcE",cursor.getString(7));
				Question_Template quest = new Question_Template();
				//quest.setID(cursor.getInt(0));
				//quest.setQUESTION(cursor.getString(1));
				quest.setID(cursor.getInt(0));
				quest.setQUESTION(cursor.getString(1));
				quesList.add(quest);
			} while (cursor.moveToNext());
		}
		return quesList;
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}



}