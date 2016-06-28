package gotouch.app.preparatec;
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

import gotouch.app.preparatec.Question_Template;

/**
 * Clase que permite la comunicación con la base de datos de la app.
 */
public class DB_Driver extends SQLiteOpenHelper{

	private static final String DB_NAME = "preparaTEC_DB.db";
	private static final String TABLE_INFO = "ItemInfo";
	private static final String TABLE_ITEMS = "Items";
	private static String DB_PATH;
	private SQLiteDatabase mainDataBase;
	private final Context mainContext;

	/**
	 * Constructor de la clase DB_Driver.
	 * @param context contexto en el que se emplea la base de datos
	 */
	public DB_Driver(Context context) {
		super(context, DB_NAME, null, 1);
		DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";

		this.mainContext = context;
	}


	/**
	 * Metodo encargado de crear la base de datos para la conexion.
	 * @throws IOException
	 */
	public void createDataBase() throws IOException {
		boolean dbExist = checkDataBase();
		if(dbExist){
			// si existe la base de datos
		} else {
			this.getReadableDatabase();
			try {
				copyDataBase();
			} catch (IOException e) {
				throw new Error("Error copying database");
			}
		}
	}


	/**
	 * Metodo encargado de comprobar la base de datos.
	 * @return
	 */
	private boolean checkDataBase(){
		SQLiteDatabase checkDB = null;

		try{
			String path = DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
		}catch(SQLiteException e){
			// excepcion sql
		}
		if(checkDB != null){
			checkDB.close();
		}
		return checkDB != null ? true : false;
	}


	/**
	 * Metodo encargado de copiar (establecer conexion con la base de datos).
	 * @throws IOException
	 */
	private void copyDataBase() throws IOException{
		InputStream myInput = mainContext.getAssets().open(DB_NAME);
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

	/**
	 * Metodo que abre la base de datos indicada en el path.
	 * @throws SQLException
	 */
	public void openDataBase() throws SQLException{
		String path = DB_PATH + DB_NAME;
		mainDataBase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
	}

	/**
	 * Metodo encargado de cerrar la conexión con la base de datos.
	 */
	@Override
	public synchronized void close() {
		if(mainDataBase != null)
			mainDataBase.close();
		super.close();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	/**
	 * Metodod encargado de obtener de la base de datos la información de cada de las preguntas.
	 * @return devuelve una lista con todas las preguntas existentes en la base de datos.
	 */
	public List<Question_Template> getItemInfo() {

		List<Question_Template> quesList = new ArrayList<Question_Template>();
		String selectQuery = "SELECT * FROM " + TABLE_INFO;

		mainDataBase =this.getReadableDatabase();
		Cursor cursor = mainDataBase.rawQuery(selectQuery, null); // resultados de la base de datos

		if (cursor.moveToFirst()) {
			do {
				Question_Template tempQuest = new Question_Template();
				tempQuest.setFirstOpt(cursor.getString(0));
				tempQuest.setSecondOpt(cursor.getString(1));
				tempQuest.setThirdOpt(cursor.getString(2));
				tempQuest.setFourthOpt(cursor.getString(3));
				tempQuest.setFifthOpt(cursor.getString(4));
				tempQuest.setAnswer(cursor.getString(5));
				quesList.add(tempQuest);
			} while (cursor.moveToNext());
		}
		return quesList;
	}

	/**
	 * Metodo encargado de obtener los encabezados de las preguntas en la base de datos.
	 * @return devuelve una lista con las preguntas de la base de datos.
	 */
	public List<Question_Template> getItems() {

		List<Question_Template> quesList = new ArrayList<Question_Template>();
		String selectQuery = "SELECT * FROM " + TABLE_ITEMS;

		mainDataBase =this.getReadableDatabase();
		Cursor cursor = mainDataBase.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				Question_Template quest = new Question_Template();
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