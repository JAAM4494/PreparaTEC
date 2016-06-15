package com.app.preparatec2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import android.database.SQLException;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.*;
import android.os.*;

/**
 * Clase encargada del modo reto.
 */
public class Dare_Screen extends Activity implements View.OnClickListener,DialogInterface.OnClickListener {

	private List<Question_Template> questList;
	private List<Question_Template> questInfo;

	private DB_Driver DBDriver;
	private Question_Template currentQuestion;

	private CountDownTimer cronom;
	private int time = 100000;
	private int questionID, score, counterQuest;
	private String userResponse;
	private TextView questionTV, cronomTV;
	private Button firstOptBtn, secondOptBtn, thirdOptBtn, fourthOptBtn, fifthOptBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_retate);

		DBDriver = new DB_Driver(this);
		score = 0;
		counterQuest = 0;

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

		questList = DBDriver.getItemInfo();
		questInfo = DBDriver.getItems();
		questList = prepareQuestion(questInfo, questList);

		// genera un aleatorio en el orden de las preguntas --------------- REVISAR
		long seed = System.nanoTime();
		Collections.shuffle(questList, new Random(seed));

		currentQuestion = questList.get(questionID);

		questionTV = (TextView)findViewById(R.id.textView1);
		cronomTV = (TextView)findViewById(R.id.cronomTextV);

		firstOptBtn		= (Button)findViewById(R.id.button1);
		secondOptBtn 	= (Button)findViewById(R.id.button2);
		thirdOptBtn 	= (Button)findViewById(R.id.button3);
		fourthOptBtn 	= (Button)findViewById(R.id.button4);
		fifthOptBtn 	= (Button)findViewById(R.id.button5);

		setQuestionView(); // se muestra la pregunta actual.

		cronom = new CountDownTimer(time, 1000) {
			public void onTick(long millisUntilFinished) {
				String tmp = String.valueOf(millisUntilFinished/1000);
				
				if(millisUntilFinished/1000<=10) {
					cronomTV.setTextColor(Color.parseColor("#FF0000"));
				}
				else {
				cronomTV.setTextColor(Color.parseColor("#414042"));
				}
				cronomTV.setText(tmp);
			}

			public void onFinish() {
				this.start();
				try{
					currentQuestion = questList.get(questionID);
					setQuestionView();
				}
				catch(Exception e){
					this.cancel();
					/*Intent intent = new Intent(Reto.this, ResultActivity.class);
					Bundle b = new Bundle();
					b.putInt("score", score);
					intent.putExtras(b); 
					startActivity(intent);
					finish();
					 */
					//Toast.makeText(getApplicationContext(),Integer.toString(score) , Toast.LENGTH_SHORT).show();
				}
			}
		};
		cronom.start();

		firstOptBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				userResponse = "1";
				checkUserAnswer(cronom);

			}
		});

		secondOptBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				userResponse = "2";
				checkUserAnswer(cronom);


			}
		});

		thirdOptBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				userResponse = "3";
				checkUserAnswer(cronom);
			}
		});

		fourthOptBtn.setOnClickListener(new View.OnClickListener() {
			@Override

			public void onClick(View v) {
				userResponse = "4";
				checkUserAnswer(cronom);

			}
		});

		fifthOptBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				userResponse = "5";
				checkUserAnswer(cronom);

			}
		});

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.normal, menu);
		return true;
	}

	private void setQuestionView() {
		questionTV.setTextColor(Color.parseColor("#414042"));

		questionTV.setText(currentQuestion.getQUESTION());
		firstOptBtn.setText(currentQuestion.getFirstOpt());
		firstOptBtn.setTextColor(Color.parseColor("#414042"));

		secondOptBtn.setText(currentQuestion.getSecondOpt());
		secondOptBtn.setTextColor(Color.parseColor("#414042"));
		thirdOptBtn.setText(currentQuestion.getThirdOpt());
		thirdOptBtn.setTextColor(Color.parseColor("#414042"));
		fourthOptBtn.setText(currentQuestion.getFourthOpt());
		fourthOptBtn.setTextColor(Color.parseColor("#414042"));
		fifthOptBtn.setText(currentQuestion.getFifthOpt());
		fifthOptBtn.setTextColor(Color.parseColor("#414042"));
		questionID++;
		counterQuest++;
	}


	/**
	 * Metodo encargado de verificar la opcioón seleecionada por el usuario.
	 * @param pCronom cronometro del modo reto.
	 */
	public void checkUserAnswer(CountDownTimer pCronom){
		
		if(counterQuest == 20) {
			int finScore = (score*100)/20;
			pCronom.cancel();

			AlertDialog ad = new AlertDialog.Builder(this)
			.setMessage("Su calificación es: " + Integer.toString(finScore))
			.setIcon(R.drawable.ic_launcher)
			.setTitle("Fin de la Práctica")
			.setPositiveButton("Menú Principal", this)
			//.setNeutralButton("Cancel", this)
			.setCancelable(false)
			.create();
			ad.show();
		}
		else{
			//pCronom.cancel();
			//pCronom.start();
			//currentQuestion=questList.get(qid);
			//setQuestionView();
			
			if(currentQuestion.getAnswer().equals(userResponse) && counterQuest!=20)
			{
				score++;
				Toast.makeText(getApplicationContext(),"Respuesta Correcta" , Toast.LENGTH_SHORT).show();
				//pCronom.cancel();
				//pCronom.start();
				//currentQuestion=questList.get(qid);
				//setQuestionView();
			}
			else {
				Toast.makeText(getApplicationContext(),"Respuesta Incorrecta" , Toast.LENGTH_SHORT).show();
				//currentQuestion=questList.get(qid);
				//setQuestionView();
			}
			pCronom.cancel();
			pCronom.start();
			currentQuestion = questList.get(questionID);
			setQuestionView();
		}
	}

	/**
	 * Metodo encargado de preparar la pregunta a mostrar.
	 * @param pItems lista con las preguntas.
	 * @param pItemInfo lista con las opciones de cada pregunta.
	 * @return devuelve la pregunta final a mostrar.
	 */
	private List<Question_Template> prepareQuestion(List<Question_Template> pItems,List<Question_Template> pItemInfo ) {

		List<Question_Template> result = new ArrayList<Question_Template>();
		for ( int i = 0; i < pItems.size(); i ++ ) {
			Question_Template tmp = new Question_Template();
			tmp.setID(pItems.get(i).getID());
			tmp.setQUESTION(pItems.get(i).getQUESTION());
			tmp.setFirstOpt(pItemInfo.get(i).getFirstOpt());
			tmp.setSecondOpt(pItemInfo.get(i).getSecondOpt());
			tmp.setThirdOpt(pItemInfo.get(i).getThirdOpt());
			tmp.setFourthOpt(pItemInfo.get(i).getFourthOpt());
			tmp.setFifthOpt(pItemInfo.get(i).getFifthOpt());
			tmp.setAnswer(pItemInfo.get(i).getAnswer());
			result.add(tmp);
		}

		return result;

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
			// nada
			break;
		}
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}


}
