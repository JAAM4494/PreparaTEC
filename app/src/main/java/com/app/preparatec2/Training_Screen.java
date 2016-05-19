package com.app.preparatec2;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Collections;
import java.util.List;
import java.util.*;

import java.util.Random;
import java.io.IOException;

import android.content.DialogInterface;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.database.SQLException;


public class Training_Screen extends Activity implements  DialogInterface.OnClickListener {

	private Button firstOptBtn, secondOptBtn, thirdOptBtn, fourthOptBtn, fifthOptBtn;
	private int questionID, userScore, questionCounter;
	private String userResponse;
	private Question_Template currentQuestion;
	private List<Question_Template> questionList, questionInfo;
	private DB_Driver DBDriver;

	private TextView questionTV;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_normal);

		DBDriver = new DB_Driver(this);

		questionID = 0;
		userScore = 0;
		questionCounter = 0;

		try {
			DBDriver.createDataBase();
		} catch (IOException ioe) {
			throw new Error("Unable to create database");
		}

		try {
			DBDriver.openDataBase();
		}catch(SQLException sqle){
			throw sqle;
		}

		questionList = DBDriver.getItemInfo();
		questionInfo = DBDriver.getItems();
		questionList = prepareQuestion(questionInfo, questionList);

		long seed = System.nanoTime();
		Collections.shuffle(questionList, new Random(seed));

		currentQuestion = questionList.get(questionID);

		questionTV =(TextView)findViewById(R.id.textView1);

		// Pintado de la pantalla de entrenamiento
		firstOptBtn = (Button)findViewById(R.id.button1);
		secondOptBtn = (Button)findViewById(R.id.button2);
		thirdOptBtn = (Button)findViewById(R.id.button3);
		fourthOptBtn = (Button)findViewById(R.id.button4);
		fifthOptBtn = (Button)findViewById(R.id.button5);
		setQuestionView();

		firstOptBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				userResponse = "1";
				checkUserAnswer();
			}
		});

		secondOptBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				userResponse = "2";
				checkUserAnswer();
			}
		});

		thirdOptBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				userResponse = "3";
				checkUserAnswer();
			}
		});

		fourthOptBtn.setOnClickListener(new View.OnClickListener() {
			@Override

			public void onClick(View v) {
				userResponse = "4";
				checkUserAnswer();
			}
		});

		fifthOptBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				userResponse = "5";
				checkUserAnswer();
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.normal, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * Metodo encargado de mostrar en pantalla la pregunta actual, tanto en el espacio de la
	 * pregunta como en las opciones de los botones.
	 */
	private void setQuestionView()
	{
		questionTV.setTextColor(Color.parseColor("#414042"));
		questionTV.setText(currentQuestion.getQUESTION());

		firstOptBtn.setText(currentQuestion.getOpcA());
		firstOptBtn.setTextColor(Color.parseColor("#414042"));

		secondOptBtn.setText(currentQuestion.getOpcB());
		secondOptBtn.setTextColor(Color.parseColor("#414042"));

		thirdOptBtn.setText(currentQuestion.getOpcC());
		thirdOptBtn.setTextColor(Color.parseColor("#414042"));

		fourthOptBtn.setText(currentQuestion.getOpcD());
		fourthOptBtn.setTextColor(Color.parseColor("#414042"));

		fifthOptBtn.setText(currentQuestion.getOpcE());
		fifthOptBtn.setTextColor(Color.parseColor("#414042"));
		
		questionID++;
		questionCounter++;
	}


	/**
	 * Metodo encargado de verificar que la opcion seleccionada por el usuario sea la correcta.
	 */
	public void checkUserAnswer() {
		
		if(currentQuestion.getANSWER().equals(userResponse) && (questionCounter != 20))
		{
			userScore++;
			Toast.makeText(getApplicationContext(),"Respuesta Correcta" , Toast.LENGTH_SHORT).show();
			currentQuestion = questionList.get(questionID);
			setQuestionView();
		} else {
			Toast.makeText(getApplicationContext(),"Respuesta Incorrecta" , Toast.LENGTH_SHORT).show();
			currentQuestion = questionList.get(questionID);
			setQuestionView();
		}
		
		if(questionCounter == 20){
			int finScore = (userScore *100)/20;
			AlertDialog ad = new AlertDialog.Builder(this)
			.setMessage("Su calificación es: " + Integer.toString(finScore))
			.setIcon(R.drawable.ic_launcher)
			.setTitle("Fin de la Práctica")
			//.setNegativeButton("Salir", this)
			.setPositiveButton("Menú Principal", this)
			//.setNeutralButton("Cancel", this)
			.setCancelable(false)
			.create();
			ad.show();
		} else {
			currentQuestion = questionList.get(questionID);
			setQuestionView();
		}
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		switch(which){
		case DialogInterface.BUTTON_POSITIVE: 
			//Devuelve Home_Screen Principal
			finish();
			System.exit(0);
			break;
		default:
			break;
		}
	}

	/**
	 * Metodo encargado de preparar
	 * @param pItems
	 * @param pItemInfo
	 * @return
	 */
	private List<Question_Template> prepareQuestion(List<Question_Template> pItems,List<Question_Template> pItemInfo ) {

		List<Question_Template> result = new ArrayList<Question_Template>();
		for ( int i = 0; i < pItems.size(); i ++ ) {
			Question_Template tmp = new Question_Template();
			tmp.setID(pItems.get(i).getID());
			tmp.setQUESTION(pItems.get(i).getQUESTION());
			tmp.setOpcA(pItemInfo.get(i).getOpcA());
			tmp.setOpcB(pItemInfo.get(i).getOpcB());
			tmp.setOpcC(pItemInfo.get(i).getOpcC());
			tmp.setOpcD(pItemInfo.get(i).getOpcD());
			tmp.setOpcE(pItemInfo.get(i).getOpcE());
			tmp.setANSWER(pItemInfo.get(i).getANSWER());

			result.add(tmp);
		}

		return result;

	}
	
}
